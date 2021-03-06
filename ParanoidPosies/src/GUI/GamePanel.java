package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.Bee;
import model.Caterpillar;
import model.FightStrategy;
import model.GameBoard;
import model.GatherStrategy;
import model.GuardStrategy;
import model.Plant;
import model.Thing;

public class GamePanel extends JPanel {

	/** Distance from border that triggers scrolling. */
	public int BORDER_MARGIN = 50;
	/** Max distance from a mob a click can be to access it. */
	public static int SELECT_MARGIN = 256;
	/** Pixels per tic */
	public static int SCROLL_SPEED = 10;

	private GameInterface game;
	private Point view = new Point(0, 0);
	protected JPanel popup;
	private TileManager tileManager = new TileManager();

	private boolean userIsDrawingABox = false;
	private Point startPoint;
	private int boxWidth;
	private int boxHeight;
	private List<Thing> selected = new LinkedList<Thing>();

	public GamePanel(GameBoard g) {
		this.game = g;
		setSize(new Dimension(PPGUI.WINDOW_WIDTH, PPGUI.WINDOW_HEIGHT));

		setLayout(null);
		JPanel resourcePanel = new ResourcePanel(g);
		add(resourcePanel);
		resourcePanel.setLocation(new Point(0, PPGUI.WINDOW_HEIGHT - resourcePanel.getHeight()));

		JPanel timerPanel = new ScorePanel(g);
		add(timerPanel);
		timerPanel.setLocation(PPGUI.WINDOW_WIDTH - timerPanel.getWidth(), PPGUI.WINDOW_HEIGHT
				- timerPanel.getHeight());

		addMouseMotionListener(new ScrollMouseListener());
		addMouseListener(new ClickListener());
		addMouseListener(new DragBoxListener());
		addMouseMotionListener(new DragBoxListener());
	}

	// TODO refactor layers
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		List<Thing> things = game.getAllThingsOnBoard();
		List<Thing> three = new ArrayList<Thing>();
		List<Thing> two = new ArrayList<Thing>();
		List<Thing> one = new ArrayList<Thing>();
		for (Thing t : things) {
			if (t.getLayer() == 1) {
				one.add(t);
			} else if (t.getLayer() == 2) {
				two.add(t);
			} else if (t.getLayer() == 3) {
				three.add(t);
			}
		}

		for (BackgroundTile b : tileManager.getTiles(view)) {
			Point p = translateFromRealToScreen(b.getLocation());
			g2.drawImage(b.getImage(), p.x, p.y, null);
		}

		for (Thing t : three) {
			drawThing(g2, t);
		}
		for (Thing t : two) {
			drawThing(g2, t);
		}
		for (Thing t : one) {
			drawThing(g2, t);
		}

		if (userIsDrawingABox) {
			drawSelectionBox(g2);
		}
	}

	private void drawThing(Graphics g, Thing t) {
		Point point = translateFromRealToScreen(t.getLocation());
		int x = point.x - t.getImage().getWidth(null) / 2;
		int y = point.y - t.getImage().getHeight(null) / 2;
		g.drawImage(t.getImage(), x, y, null);
	}

	private void drawSelectionBox(Graphics graphics) {
		graphics.setColor(Color.RED);

		int minX = Math.min(startPoint.x, startPoint.x + boxWidth);
		int minY = Math.min(startPoint.y, startPoint.y + boxHeight);

		graphics.drawRect(minX, minY, Math.abs(boxWidth), Math.abs(boxHeight));
		graphics.drawRect(minX - 1, minY - 1, Math.abs(boxWidth) + 2, Math.abs(boxHeight) + 2);
	}

	private Direction direction = Direction.NONE;

	public void shiftViewPoint() {
		if (direction == Direction.DOWN) {
			view.y += SCROLL_SPEED;
		} else if (direction == Direction.UP) {
			view.y -= SCROLL_SPEED;
		} else if (direction == Direction.LEFT) {
			view.x -= SCROLL_SPEED;
		} else if (direction == Direction.RIGHT) {
			view.x += SCROLL_SPEED;
		}
	}

	private class ScrollMouseListener extends MouseAdapter {
		@Override
		public void mouseMoved(MouseEvent m) {
			if (m.getX() < BORDER_MARGIN) {
				direction = Direction.LEFT;
			} else if (m.getX() > PPGUI.WINDOW_WIDTH - BORDER_MARGIN) {
				direction = Direction.RIGHT;
			} else if (m.getY() < BORDER_MARGIN) {
				direction = Direction.UP;
			} else if (m.getY() > PPGUI.WINDOW_HEIGHT - BORDER_MARGIN) {
				direction = Direction.DOWN;
			} else {
				direction = Direction.NONE;
			}
		}
	}

	private class ClickListener extends MouseAdapter {
		private int lastSelection;

		@Override
		public void mouseClicked(MouseEvent e) {

			Point realSpacePoint = translateFromScreenToReal(e.getPoint());
			List<Thing> allAtPoint = game.getAllThingsOnBoard();
			List<Thing> atPoint = pruneListForPoint(allAtPoint, realSpacePoint);

			if (selected.size() == 0) {

				if (popup != null) {
					if (popup instanceof DenialPopup) {
						((DenialPopup) popup).timer.stop();
					}
					remove(popup);
				}
				if (atPoint.size() > 0) {
					popup = new PopupPanel(atPoint.get(lastSelection % atPoint.size()));
					popup.setLocation(e.getPoint());
					lastSelection++;
					add(popup);
				} else if (popup == null) {
					if (game.getHive().getSeeds() >= 0) {
						popup = new PlantMenu((GameBoard) game, realSpacePoint);
						popup.setLocation(e.getPoint());
						lastSelection++;
						add(popup);
					}
				} else {
					popup = null;
				}
			} else if (atPoint.size() > 0) {
				Thing target = atPoint.get(0);
				if (target instanceof Plant) {
					tellAllSelectedToGatherFrom(target);
				} else if (target instanceof Caterpillar) {
					tellAllSelectedToAttack(target);
				}
				for (Thing t : selected) {
					if (t instanceof Bee) {
						((Bee) t).setSelected(false);
					}
				}
				selected.clear();
			} else { // selected.size == 0
				tellAllSelectedToGaurd(realSpacePoint);
				for (Thing t : selected) {
					if (t instanceof Bee) {
						((Bee) t).setSelected(false);
					}
				}
				selected.clear();
			}
		}

		private List<Thing> pruneListForPoint(List<Thing> allAtPoint, Point clickedPoint) {

			List<Thing> atPoint = new ArrayList<Thing>();

			for (Thing t : allAtPoint) {
				if (t.contains(clickedPoint)) {
					atPoint.add(t);
				}
			}
			Collections.sort(atPoint, new ThingPriorityComparator());

			return atPoint;
		}
	}

	private void tellAllSelectedToAttack(Thing target) {
		for (Thing actor : selected) {
			if (actor instanceof Bee) {
				Bee bee = ((Bee) actor);
				bee.setStrategy(new FightStrategy(bee), target);
			}
		}
	}

	private void tellAllSelectedToGatherFrom(Thing target) {
		System.out.println("tell all to gather");
		for (Thing actor : selected) {
			if (actor instanceof Bee) {
				Bee bee = ((Bee) actor);
				bee.setStrategy(new GatherStrategy(bee), target);
			}
		}
	}

	private void tellAllSelectedToGaurd(Point point) {
		for (Thing actor : selected) {
			if (actor instanceof Bee) {
				Bee bee = ((Bee) actor);
				bee.setStrategy(new GuardStrategy(bee), point);
			}
		}
	}

	private class DragBoxListener extends MouseAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (!userIsDrawingABox) {
				userIsDrawingABox = true;
				startPoint = e.getPoint();
			}
			boxWidth = e.getPoint().x - startPoint.x;
			boxHeight = e.getPoint().y - startPoint.y;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (userIsDrawingABox) {
				int minX = Math.min(startPoint.x, startPoint.x + boxWidth) + view.x
						- PPGUI.WINDOW_WIDTH / 2;
				int minY = Math.min(startPoint.y, startPoint.y + boxHeight) + view.y
						- PPGUI.WINDOW_HEIGHT / 2;
				int maxX = Math.max(startPoint.x, startPoint.x + boxWidth) + view.x
						- PPGUI.WINDOW_WIDTH / 2;
				int maxY = Math.max(startPoint.y, startPoint.y + boxHeight) + view.y
						- PPGUI.WINDOW_HEIGHT / 2;

				selected = game.getThingsBetween(minX, minY, maxX, maxY);
				for (Thing t : selected) {
					if (t instanceof Bee) {
						((Bee) t).setSelected(true);
					}
				}
			}
			userIsDrawingABox = false;
		}
	}

	private Point translateFromScreenToReal(Point point) {
		return new Point(point.x + view.x - PPGUI.WINDOW_WIDTH / 2, point.y + view.y
				- PPGUI.WINDOW_HEIGHT / 2);
	}

	private Point translateFromRealToScreen(Point point) {
		return new Point(point.x - view.x + PPGUI.WINDOW_WIDTH / 2, point.y - view.y
				+ PPGUI.WINDOW_HEIGHT / 2);
	}

}