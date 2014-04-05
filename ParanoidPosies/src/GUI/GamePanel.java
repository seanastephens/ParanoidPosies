package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Thing;

public class GamePanel extends JPanel implements KeyListener,
		MouseMotionListener, MouseListener {

	private GameInterface game;
	private Point view = new Point(2500, 2500);
	private PopupPanel popup;
	private TileManager tileManager = new TileManager();

	public GamePanel(GameInterface g) {
		this.game = g;
		setSize(new Dimension(PPGUI.WINDOW_WIDTH, PPGUI.WINDOW_HEIGHT));

		setLayout(null);
		JPanel resourcePanel = new ResourcePanel(g.getHive());
		add(resourcePanel);
		resourcePanel.setLocation(new Point(0, PPGUI.WINDOW_HEIGHT
				- resourcePanel.getHeight()));

	}

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
			Point p = new Point(b.getLocation().x - view.x + PPGUI.WINDOW_WIDTH
					/ 2, b.getLocation().y - view.y + PPGUI.WINDOW_HEIGHT / 2);
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
	}

	private void drawThing(Graphics g, Thing t) {
		int x = t.getLocation().x - view.x + PPGUI.WINDOW_WIDTH / 2
				- t.getImage().getWidth(null) / 2;
		;
		int y = t.getLocation().y - view.y + PPGUI.WINDOW_HEIGHT / 2
				- t.getImage().getHeight(null) / 2;

		g.drawImage(t.getImage(), x, y, null);
	}

	@Override
	public void keyTyped(KeyEvent k) {
		if (k.getKeyChar() == ' ') {
			remove(popup);
		}
	}

	public int BORDER_MARGIN = 50;

	private enum Direction {
		RIGHT, LEFT, UP, DOWN, NONE
	}

	private Direction direction = Direction.NONE;

	@Override
	public void mouseMoved(MouseEvent m) {
		if (m.getX() < BORDER_MARGIN) {
			direction = Direction.LEFT;
		} else if (m.getX() > PPGUI.WINDOW_WIDTH - BORDER_MARGIN) {
			direction = Direction.RIGHT;

		} else if (m.getY() < BORDER_MARGIN) {
			direction = Direction.DOWN;

		} else if (m.getY() > PPGUI.WINDOW_HEIGHT - BORDER_MARGIN) {
			direction = Direction.UP;
		} else {
			direction = Direction.NONE;
		}
	}

	public static int SELECT_MARGIN = 25;

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getPoint().x - PPGUI.WINDOW_WIDTH / 2 + view.x;
		int y = e.getPoint().y - PPGUI.WINDOW_HEIGHT / 2 + view.y;

		Point p = new Point(x, y);

		List<Thing> atPoint = game.getThingsBetween(p.x - SELECT_MARGIN, p.y
				- SELECT_MARGIN, p.x + SELECT_MARGIN, p.y + SELECT_MARGIN);
		if (popup != null) {
			remove(popup);
		}

		if (atPoint.size() > 0) {
			popup = new PopupPanel(atPoint.get(0));
			popup.setLocation(e.getPoint());
			add(popup);
		}

	}

	public static int SCROLL_SPEED = 3;

	public void shiftViewPoint() {
		switch (direction) {
		case UP:
			view.y += SCROLL_SPEED;
			break;
		case DOWN:
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

	@Override
	public void keyPressed(KeyEvent k) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
