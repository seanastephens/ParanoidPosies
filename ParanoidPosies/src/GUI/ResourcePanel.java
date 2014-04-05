package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResourcePanel extends JPanel {

	private MockHive hive;
	private JLabel honeyLabel;

	private int PANEL_WIDTH = 100;
	private int PANEL_HEIGHT = 100;

	public ResourcePanel(MockHive hive) {
		this.hive = hive;

		setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		setLayout(new FlowLayout());

		honeyLabel = new JLabel("Honey " + hive.getHoney());
		add(honeyLabel);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		g.setColor(Color.BLACK);
		honeyLabel.setText("Honey " + hive.getHoney());
	}

}
