package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Thing;

public class PopupPanel extends JPanel {

	private int WIDTH = 150;
	private int HEIGHT = 150;
	private int LABEL_HEIGHT = LABEL_WIDTH;

	private Thing thing;

	private JLabel type;
	private JLabel name;
	private JLabel hp;
	private JLabel action;

	public PopupPanel(Thing thing) {
		this.thing = thing;

		setSize(WIDTH, HEIGHT);

		setLayout(null);
		name = new JLabel(thing.getName());
		type = new JLabel(thing.getType());
		hp = new JLabel(thing.getHP() + "");
		action = new JLabel(thing.getAction());

		add(name);
		add(type);
		add(hp);
		add(action);

		name.setLocation(0, 0);
		name.setSize(WIDTH, LABEL_WIDTH);
		type.setLocation(0, LABEL_WIDTH);
		type.setSize(WIDTH, LABEL_WIDTH);
		hp.setLocation(0, 70);
		hp.setSize(WIDTH, LABEL_WIDTH);
		action.setLocation(0, 105);
		action.setSize(WIDTH, LABEL_WIDTH);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, 150);
		g.setColor(Color.BLACK);
		name.setText("<html>Name:<br>" + thing.getName() + "</html>");
		type.setText("<html>Type:<br>" + thing.getType() + "</html>");
		hp.setText("<html>Health:<br>" + thing.getHP() + "</html>");
		action.setText("<html>action:<br>" + thing.getAction() + "</html>");
	}
}
