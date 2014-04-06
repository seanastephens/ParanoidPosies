package GUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameBoard;
import model.Posie;

public class PlantMenu extends JPanel {

	private int WIDTH = 150; // default
	private int HEIGHT = 80; // default

	private GameBoard game;
	private Point where;

	public PlantMenu(GameBoard game, Point where) {
		this.game = game;
		this.where = where;
		setSize(WIDTH, HEIGHT);
		setBackground(Color.GRAY);
		setLayout(null);

		JButton yes = new JButton("Yes!");
		yes.addActionListener(new YesListener());
		add(yes);
		yes.setLocation(new Point(0, HEIGHT / 2));
		yes.setSize(WIDTH / 2, HEIGHT / 2);

		JButton no = new JButton("no.");
		no.addActionListener(new NoListener());
		add(no);
		no.setLocation(WIDTH / 2, HEIGHT / 2);
		no.setSize(WIDTH / 2, HEIGHT / 2);

		JLabel q = new JLabel("<html>Put a plant here?</html>");
		add(q);
		q.setLocation(0, 0);
		q.setSize(WIDTH, HEIGHT / 2);
	}

	private class YesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.getAllThingsOnBoard().add(new Posie(where));
			System.out.println(game.getAllThingsOnBoard());
			setVisible(false);
			setEnabled(false);
			((GamePanel) getParent()).popup = null;
			getParent().repaint();
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
