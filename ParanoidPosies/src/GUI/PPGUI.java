package GUI;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import model.GameBoard;

public class PPGUI extends JFrame implements Runnable {

	public static void main(String[] args) {
		new PPGUI().setVisible(true);
	}

	public static final int UPDATES_PER_SEC = 20;
	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 720;

	private GameInterface game;
	private GamePanel gamePanel;

	public PPGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		game = new GameBoard();

		setLayout(null);

		gamePanel = new GamePanel(game);
		add(gamePanel);
		gamePanel.setLocation(new Point(0, 0));

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
