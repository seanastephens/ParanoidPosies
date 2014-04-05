package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;

import model.Thing;

public class GamePanel extends JPanel implements KeyListener {

	public static int TILE_WIDTH = 30;

	private GameInterface g;
	private Point viewCenter = new Point(50, 50);

	public GamePanel(GameInterface g) {
		this.g = g;
		setSize(new Dimension(ParanoidPosieGUI.WINDOW_WIDTH,
				ParanoidPosieGUI.WINDOW_HEIGHT));

		setLayout(null);
		JPanel resourcePanel = new ResourcePanel(g.getHive());
		add(resourcePanel);
		resourcePanel.setLocation(new Point(0, ParanoidPosieGUI.WINDOW_HEIGHT
				- resourcePanel.getHeight()));
	}

	public void paintComponent(Graphics graphics) {
		List<Thing> things = g.getAllThingsOnBoard();
		for (Thing t : things) {
			int x = (t.getLocation().x - viewCenter.x) * TILE_WIDTH
					+ ParanoidPosieGUI.WINDOW_WIDTH / 2;
			int y = (t.getLocation().y - viewCenter.y) * TILE_WIDTH
					+ ParanoidPosieGUI.WINDOW_HEIGHT / 2;

			graphics.drawImage(t.getImage(), x, y, null);
		}
	}

	@Override
	public void keyPressed(KeyEvent k) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent k) {
		System.out.println(k.getKeyChar());
		switch (k.getKeyChar()) {
		case 'w':
			viewCenter.y++;
			break;
		case 'a':
			viewCenter.x++;
			break;
		case 's':
			viewCenter.y--;
			break;
		case 'd':
			viewCenter.x--;
			break;
		default:
		}
	}

}
