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

import model.Thing;

public class GamePanel extends JPanel {

	/** Distance from border that triggers scrolling. */
	public int BORDER_MARGIN = 50;
	/** Max distance from a mob a click can be to access it. */
	public static int SELECT_MARGIN = 25;
	/** Pixels per tic */
	public static int SCROLL_SPEED = 10;

	private GameInterface game;
	private Point view = new Point(2500, 2500);
	private PopupPanel popup;
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
		switch (direction) {
		case DOWN:
			view.y += SCROLL_SPEED;
			break;
		case UP:
			view.y -= SCROLL_SPEED;
			break;
		case RIGHT:
			view.x += SCROLL_SPEED;
			break;
		case LEFT:
			view.x -= SCROLL_SPEED;
			break;
		default:
			break;
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
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getPoint().x - PPGUI.WINDOW_WIDTH / 2 + view.x;
			int y = e.getPoint().y - PPGUI.WINDOW_HEIGHT / 2 + view.y;

			List<Thing> atPoint = game.getThingsBetween(x - SELECT_MARGIN, y - SELECT_MARGIN, x
					+ SELECT_MARGIN, y + SELECT_MARGIN);
			if (popup != null) {
				remove(popup);
			}
			if (atPoint.size() > 0) {
				popup = new PopupPanel(atPoint.get(0));
				popup.setLocation(e.getPoint());
				add(popup);
			}
		}
	}

	private class DragBoxListener extends MouseAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println("Drag");
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
}