package GUI;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ParanoidPosieGUI extends JFrame implements Runnable {

	public static void main(String[] args) {
		new ParanoidPosieGUI().setVisible(true);
	}

	public static final int UPDATES_PER_SEC = 20;
	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 720;

	private MockHive hive;
	private GameInterface game;

	public ParanoidPosieGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		game = new MockGame();
		hive = game.getHive();

		setLayout(null);
		JPanel resourcePanel = new ResourcePanel(hive);
		add(resourcePanel);
		resourcePanel.setLocation(new Point(0, WINDOW_HEIGHT
				- resourcePanel.getHeight()));

		Thread animator = new Thread(this);
		animator.start();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / UPDATES_PER_SEC);
			} catch (InterruptedException e) {
				System.out.println("Interrupted!");
			}
			System.out.println("GO");
			game.update();
			repaint();
		}
	}
}
