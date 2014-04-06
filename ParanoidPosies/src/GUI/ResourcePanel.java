package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Hive;

public class ResourcePanel extends JPanel {

	private Hive hive;
	private JLabel honeyLabel;
	private JLabel nectarLabel;
	private JLabel seedLabel;

	private int PANEL_WIDTH = 100;
	private int PANEL_HEIGHT = 100;

	public ResourcePanel(Hive hive) {
		this.hive = hive;

		setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		setLayout(new FlowLayout());

		honeyLabel = new JLabel("Honey " + hive.getHoney());
		add(honeyLabel);
		nectarLabel = new JLabel("Nectar " + hive.getNector());
		add(nectarLabel);
		seedLabel = new JLabel("Seeds " + hive.getNector());
		add(seedLabel);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		g.setColor(Color.BLACK);
		honeyLabel.setText("Honey " + hive.getHoney());
		nectarLabel.setText("Nectar " + hive.getNector());
		seedLabel.setText("Seeds " + hive.getNector());
	}

}
