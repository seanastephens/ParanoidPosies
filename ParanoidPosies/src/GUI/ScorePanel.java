package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.GameBoard;

public class ScorePanel extends JPanel {

	private JLabel timeLabel;
	private JLabel levelLabel;
	private JLabel scoreLabel;
	private GameBoard game;

	private int PANEL_WIDTH = 200;
	private int PANEL_HEIGHT = 200;

	public ScorePanel(GameBoard game) {

		this.game = game;

		setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setLayout(new FlowLayout());
		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border titleBorder = BorderFactory.createTitledBorder(etchedBorder);
		((TitledBorder) titleBorder).setTitle("Menu");
		((TitledBorder) titleBorder).setTitleColor(Color.BLACK);
		setBorder(titleBorder);

		timeLabel = new JLabel();
		levelLabel = new JLabel();
		scoreLabel = new JLabel();

		JButton warriorButton = new JButton("WARRIOR! (" + game.hive.honeyCostToBuildAWarriorBee
				+ ")");
		warriorButton.addActionListener(new WarriorListener());

		add(timeLabel);
		add(levelLabel);
		add(scoreLabel);
		add(warriorButton);
	}

	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		timeLabel.setText(String.format("Time %.2f", game.getNumberOfTics()
				/ (1. * PPGUI.UPDATES_PER_SEC)));
		levelLabel.setText("Wave size: " + game.getWaveSize());
		scoreLabel.setText("SCORE FILLER");
	}

	private class WarriorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.hive.buildWarriorBee();
		}
	}
}
