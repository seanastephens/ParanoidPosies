package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.Bee;
import model.Caterpillar;
import model.FightStrategy;
import model.GameBoard;
import model.GatherStrategy;
import model.GuardStrategy;
import model.Hive;
import model.ImageReg;
import model.Plant;
import model.Posie;
import model.Thing;

public class GamePanel extends JPanel {

	/** Distance from border that triggers scrolling. */
	public int BORDER_MARGIN = 50;
	/** Max distance from a mob a click can be to access it. */
	public static int SELECT_MARGIN = 256;
	/** Pixels per tic */
	public static int SCROLL_SPEED = 10;

	private GameInterface game;
	private Point view = new Point(2500, 2500);
	protected JPanel popup;
	private TileManager tileManager = new TileManager();

	private boolean userIsDrawingABox = false;
	private Point startPoint;
	private int boxWidth;
	private int boxHeight;
	private List<Thing> selected = new LinkedList<Thing>();

	public GamePanel(GameInterface g) {
		this.game = g;
		setSize(new Dimension(PPGUI.WINDOW_WIDTH, PPGUI.WINDOW_HEIGHT));

		setLayout(null);
		JPanel resourcePanel = new ResourcePanel(g.getHive());
		add(resourcePanel);
		resourcePanel.setLocation(new Point(0, PPGUI.WINDOW_HEIGHT - resourcePanel.getHeight()));

		addMouseMotionListener(new ScrollMouseListener());
		addMouseListener(new ClickListener());
		addMouseListener(new DragBoxListener());
		addMouseMotionListener(new DragBoxListener());

		addKeyListener(new ScrollKeyListener());
	}

	// TODO refactor layers
	public void paintComponent(Graphics graphics) {
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
			Point p = new Point(b.getLocation().x - view.x + PPGUI.WINDOW_WIDTH / 2,
					b.getLocation().y - view.y + PPGUI.WINDOW_HEIGHT / 2);
			graphics.drawImage(b.getImage(), p.x, p.y, null);
		}

		for (Thing t : three) {
			drawThing(graphics, t);
		}
		for (Thing t : two) {
			drawThing(graphics, t);
		}
		for (Thing t : one) {
			drawThing(graphics, t);
		}

		if (userIsDrawingABox) {
			drawSelectionBox(graphics);
		}
	}

	private void drawThing(Graphics g, Thing t) {
		int x = t.getLocation().x - view.x + PPGUI.WINDOW_WIDTH / 2 - t.getImage().getWidth(null)
				/ 2;
		int y = t.getLocation().y - view.y + PPGUI.WINDOW_HEIGHT / 2 - t.getImage().getHeight(null)
				/ 2;

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

	private class ScrollKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent k) {
			switch (k.getKeyChar()) {
			case ' ':
				remove(popup);
				break;
			case 'w':
				direction = Direction.UP;
				break;
			case 'a':
				direction = Direction.LEFT;
				break;
			case 's':
				direction = Direction.DOWN;
				break;
			case 'd':
				direction = Direction.RIGHT;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			direction = Direction.NONE;
		}
	}

	private class ClickListener extends MouseAdapter {
		private int lastSelection;

		@Override
		public void mouseClicked(MouseEvent e) {

			int x = e.getPoint().x - PPGUI.WINDOW_WIDTH / 2 + view.x;
			int y = e.getPoint().y - PPGUI.WINDOW_HEIGHT / 2 + view.y;
			Point clickedPoint = new Point(x, y);
			List<Thing> allAtPoint = game.getThingsBetween(x - SELECT_MARGIN, y - SELECT_MARGIN, x
					+ SELECT_MARGIN, y + SELECT_MARGIN);

			List<Thing> atPoint = pruneListForPoint(allAtPoint, clickedPoint);

			if (selected.size() == 0) {

				if (popup != null) {
					remove(popup);
				}
				if (atPoint.size() > 0) {
					popup = new PopupPanel(atPoint.get(lastSelection % atPoint.size()));
					popup.setLocation(e.getPoint());
					lastSelection++;
					add(popup);
				} else if (popup == null) {
					if (game.getHive().getSeeds() >= 0) {
						popup = new PlantMenu((GameBoard) game, new Point(x, y));
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
				selected.clear();
			} else { // selected.size == 0
				Point point = e.getPoint();
				point = new Point(point.x + view.x - PPGUI.WINDOW_WIDTH / 2, point.y + view.y
						- PPGUI.WINDOW_HEIGHT / 2);
				tellAllSelectedToGaurd(point);
				selected.clear();
			}
		}

		private List<Thing> pruneListForPoint(List<Thing> allAtPoint, Point clickedPoint) {

			int BEE_MARGIN_W = ImageReg.getInstance().getImageFromStr("Bee").getWidth(null) / 2;
			int BEE_MARGIN_H = ImageReg.getInstance().getImageFromStr("Bee").getHeight(null) / 2;
			int BEE_MARGIN = Math.max(BEE_MARGIN_H, BEE_MARGIN_W);
			int POSIE_MARGIN_W = ImageReg.getInstance().getImageFromStr("TotallyAPosie")
					.getWidth(null) / 2;
			int POSIE_MARGIN_H = ImageReg.getInstance().getImageFromStr("TotallyAPosie")
					.getHeight(null) / 2;
			int POSIE_MARGIN = Math.max(POSIE_MARGIN_H, POSIE_MARGIN_W);
			int HIVE_MARGIN_W = ImageReg.getInstance().getImageFromStr("Hive").getWidth(null) / 2;
			int HIVE_MARGIN_H = ImageReg.getInstance().getImageFromStr("Hive").getHeight(null) / 2;
			int HIVE_MARGIN = Math.max(HIVE_MARGIN_H, HIVE_MARGIN_W);
			int CAT_MARGIN_W = ImageReg.getInstance().getImageFromStr("Caterpillar").getWidth(null) / 2;
			int CAT_MARGIN_H = ImageReg.getInstance().getImageFromStr("Caterpillar")
					.getHeight(null) / 2;
			int CAT_MARGIN = Math.max(CAT_MARGIN_H, CAT_MARGIN_W);

			List<Thing> atPoint = new ArrayList<Thing>();

			for (Thing t : allAtPoint) {
				if (t instanceof Bee) {
					if (t.getLocation().distance(clickedPoint) < BEE_MARGIN) {
						atPoint.add(t);
					}
				}
			}
			if (atPoint.size() > 0) {
				return atPoint;
			}
			for (Thing t : allAtPoint) {
				if (t instanceof Posie) {
					if (t.getLocation().distance(clickedPoint) < POSIE_MARGIN) {
						atPoint.add(t);
					}
				}
			}
			if (atPoint.size() > 0) {
				return atPoint;
			}
			for (Thing t : allAtPoint) {
				if (t instanceof Caterpillar) {
					if (t.getLocation().distance(clickedPoint) < CAT_MARGIN) {
						atPoint.add(t);
					}
				}
			}
			if (atPoint.size() > 0) {
				return atPoint;
			}
			for (Thing t : allAtPoint) {
				if (t instanceof Hive) {
					if (t.getLocation().distance(clickedPoint) < HIVE_MARGIN) {
						atPoint.add(t);
					}
				}
			}
			if (atPoint.size() > 0) {
				return atPoint;
			}

			return atPoint;
		}
	}

	private void tellAllSelectedToAttack(Thing target) {
		for (Thing actor : selected) {
			if (actor instanceof Bee) {
				Bee bee = ((Bee) actor);
				bee.setStrategy(new FightStrategy(bee, (GameBoard) game), target);
			}
		}
	}

	private void tellAllSelectedToGatherFrom(Thing target) {
		for (Thing actor : selected) {
			if (actor instanceof Bee) {
				Bee bee = ((Bee) actor);
				bee.setStrategy(new GatherStrategy(bee, (GameBoard) game), target);
			}
		}
	}

	private void tellAllSelectedToGaurd(Point point) {
		for (Thing actor : selected) {
			if (actor instanceof Bee) {
				Bee bee = ((Bee) actor);
				bee.setStrategy(new GuardStrategy(bee, (GameBoard) game), point);
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