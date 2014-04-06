package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameBoard;

public class TimerPanel extends JPanel {

	private JLabel timeLabel;
	private JLabel levelLabel;
	private JLabel scoreLevel;
	private GameBoard game;

	private int PANEL_WIDTH = 300;
	private int PANEL_HEIGHT = 200;

	private Image[] images = new Image[9];
	int state = 0;

	public TimerPanel(GameBoard game) {

		this.game = game;

		setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setLayout(null);

		JPanel labelWrapper = new JPanel();
		labelWrapper.setSize(PANEL_WIDTH / 3, PANEL_HEIGHT);
		labelWrapper.setLocation(0, 0);
		add(labelWrapper);
	}

	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, PANEL_WIDTH / 3, PANEL_HEIGHT);
	}
}
