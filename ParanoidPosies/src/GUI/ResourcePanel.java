package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameBoard;
import model.Hive;

public class ResourcePanel extends JPanel {

	private Hive hive;
	private JLabel honeyLabel;
	private JLabel nectarLabel;
	private JLabel seedLabel;
	private JLabel beeLabel;
	private JLabel catLabel;
	private JLabel flowerLabel;
	private GameBoard game;

	private int PANEL_WIDTH = 300;
	private int PANEL_HEIGHT = 200;

	public ResourcePanel(GameBoard game) {
		this.hive = game.getHive();
		this.game = game;

		setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setLayout(null);

		honeyLabel = new JLabel();
		nectarLabel = new JLabel();
		seedLabel = new JLabel();
		beeLabel = new JLabel();
		catLabel = new JLabel();
		flowerLabel = new JLabel();
		setLabels();

		JPanel labelWrapper = new JPanel();
		labelWrapper.setSize(PANEL_WIDTH / 3, PANEL_HEIGHT);
		labelWrapper.setLocation(0, 0);
		labelWrapper.add(honeyLabel);
		labelWrapper.add(nectarLabel);
		labelWrapper.add(seedLabel);
		labelWrapper.add(beeLabel);
		labelWrapper.add(catLabel);
		labelWrapper.add(flowerLabel);
		add(labelWrapper);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		g.setColor(Color.BLACK);
		setLabels();
	}

	private void setLabels() {
		honeyLabel.setText("Honey        " + hive.getHoney());
		nectarLabel.setText("Nectar       " + hive.getNector());
		seedLabel.setText("Seeds        " + hive.getSeeds());
		beeLabel.setText("Bees         " + game.getNumberOfBees());
		catLabel.setText("Caterpillars " + game.getNumberOfCats());
		flowerLabel.setText("Flowers      " + game.getNumberOfFlowers());
	}
}
