package GUI;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import model.GameBoard;
import model.Hive;

public class ParanoidPosieGUI extends JFrame implements Runnable {

	public static void main(String[] args) {
		new ParanoidPosieGUI().setVisible(true);
	}

	public static final int UPDATES_PER_SEC = 20;
	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 720;

	private Hive hive;
	private GameInterface game;
	private GamePanel gamePanel;

	public ParanoidPosieGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		game = new GameBoard();
		hive = game.getHive();

		setLayout(null);

		gamePanel = new GamePanel(game);
		add(gamePanel);
		gamePanel.setLocation(new Point(0, 0));

		addKeyListener(gamePanel);
		addMouseMotionListener(gamePanel);
		addMouseListener(gamePanel);

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
			gamePanel.shiftViewPoint();
			game.update();
			repaint();
		}
	}
}
