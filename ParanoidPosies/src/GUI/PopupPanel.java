package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Thing;

public class PopupPanel extends JPanel {

	private Thing thing;

	public PopupPanel(Thing thing) {
		this.thing = thing;

		setLayout(new FlowLayout());
		JLabel title = new JLabel(thing.getClass().toString());
		add(title);
		title.setLocation(new Point(0, 0));

		setSize(100, 150);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 150, 200);
	}

}
