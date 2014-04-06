package GUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.GameBoard;

public class DenialPopup extends JPanel implements ActionListener {

	private int WIDTH = 150; // default
	private int HEIGHT = 60; // default
	private int X_OFFSET = 5;
	private int Y_OFFSET = 10;
	private int TIME_UNTIL_AUTO_REMOVE = 2000;
	private String TITLE = "NOPE";

	public DenialPopup(GameBoard game) {
		setSize(WIDTH, HEIGHT);
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLoweredSoftBevelBorder());
		setLayout(null);

		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border titleBorder = BorderFactory.createTitledBorder(etchedBorder);
		((TitledBorder) titleBorder).setTitle(TITLE);
		((TitledBorder) titleBorder).setTitleColor(Color.YELLOW);
		setBorder(titleBorder);

		String msg = "<html>Not enough ";
		if (game.getHive().getSeeds() < GameBoard.POSIE_COST_IN_SEEDS) {
			msg += "seeds: have " + game.getHive().getSeeds() + " / "
					+ GameBoard.POSIE_COST_IN_SEEDS + "!</html>";
		} else {
			msg += "honey: have " + game.getHive().getHoney() + " / "
					+ GameBoard.POSIE_COST_IN_HONEY + "!</html>";
		}
		msg += "!</html>";

		JLabel q = new JLabel(msg);
		q.setLocation(new Point(X_OFFSET, Y_OFFSET));
		add(q);
		q.setSize(WIDTH, HEIGHT / 2);

		Timer timer = new Timer(TIME_UNTIL_AUTO_REMOVE, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		setEnabled(false);
		if (((GamePanel) getParent()).popup == DenialPopup.this) {
			((GamePanel) getParent()).popup = null;
		}
	}
}
