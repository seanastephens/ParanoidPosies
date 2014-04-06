package GUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.GameBoard;
import model.Posie;

public class PlantMenu extends JPanel {

	private int WIDTH = 150; // default
	private int HEIGHT = 80; // default
	private String TITLE = "Place a Seed!";
	private static int OFFSET = 8;
	private static int PADDING = 3;

	private GameBoard game;
	private Point where;

	public PlantMenu(GameBoard game, Point where) {
		this.game = game;
		this.where = where;
		setSize(WIDTH, HEIGHT);
		setBackground(Color.GRAY);
		setLayout(null);

		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border titleBorder = BorderFactory.createTitledBorder(etchedBorder);
		((TitledBorder) titleBorder).setTitle(TITLE);
		((TitledBorder) titleBorder).setTitleColor(Color.YELLOW);
		setBorder(titleBorder);

		JButton yes = new JButton("Yes");
		yes.addActionListener(new YesListener());
		add(yes);
		yes.setLocation(new Point(OFFSET, HEIGHT / 2));
		yes.setSize(WIDTH / 2 - OFFSET - PADDING, HEIGHT / 2 - OFFSET);

		JButton no = new JButton("no");
		no.addActionListener(new NoListener());
		add(no);
		no.setLocation(WIDTH / 2 + PADDING, HEIGHT / 2);
		no.setSize(WIDTH / 2 - OFFSET - PADDING, HEIGHT / 2 - OFFSET);

		JLabel q = new JLabel("<html>Put a plant here?</html>");
		add(q);
		q.setLocation(OFFSET, 0);
		q.setSize(WIDTH, HEIGHT / 2);
	}

	private class YesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			GamePanel gPanel = (GamePanel) getParent();
			int currentHoney = game.hive.getHoney();
			int currentSeeds = game.hive.getSeeds();
			if (currentHoney >= GameBoard.POSIE_COST_IN_HONEY
					&& currentSeeds >= GameBoard.POSIE_COST_IN_SEEDS) {
				game.hive.updateHoney(-1 * GameBoard.POSIE_COST_IN_HONEY);
				game.hive.updateSeeds(-1 * GameBoard.POSIE_COST_IN_SEEDS);
				game.getAllThingsOnBoard().add(new Posie(where));
				gPanel.popup = null;
				game.plantWasBuilt();
			} else {
				Point ourCoords = gPanel.popup.getLocation();
				JPanel denied = new DenialPopup(game);
				gPanel.add(denied);
				gPanel.popup = denied;
				gPanel.popup.setLocation(ourCoords);
			}
			setVisible(false);
			setEnabled(false);

		}
	}

	private class NoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			setEnabled(false);
			((GamePanel) getParent()).popup = null;
		}
	}
}
