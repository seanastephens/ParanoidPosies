package GUI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.GameBoard;

public class PPGUI extends JFrame implements Runnable {

	public static void main(String[] args) {
		new PPGUI().setVisible(true);
	}

	public static final int UPDATES_PER_SEC = 20;
	public static int WINDOW_WIDTH = 900;
	public static int WINDOW_HEIGHT = 720;

	public static boolean FULL_SCREEN = false;

	private GameInterface game;
	private GamePanel gamePanel;
	private Thread animator;
	private boolean PAUSED = false;

	public PPGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new ExitListener());

		if (FULL_SCREEN) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setResizable(true);
			WINDOW_WIDTH = (int) screenSize.getWidth();
			WINDOW_HEIGHT = (int) screenSize.getHeight();
			setUndecorated(true);
		} else {
			setResizable(false);
		}

		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		game = new GameBoard();

		setLayout(null);

		gamePanel = new GamePanel(game);
		add(gamePanel);
		gamePanel.setLocation(new Point(0, 0));

		animator = new Thread(this);
		animator.start();
	}

	public void run() {
		while (true) {
			while (PAUSED) {
				doSomeSleeping();
			}
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

	private void doSomeSleeping() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {

		}
	}

	private class ExitListener extends KeyAdapter {

		private String TITLE = "Exit?";
		private String MESSAGE = "Quit Paranoid Posies?";
		private String[] options = { "Yes", "Cancel" };

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				PAUSED = true;
				int exit = JOptionPane.showOptionDialog(null, MESSAGE, TITLE,
						JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, null, options,
						options[1]);
				if (exit == 0) {
					System.exit(0);
				}
				PAUSED = false;
			}
		}
	}
}
