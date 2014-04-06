package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameBoard;
import model.Hive;
import model.ImageReg;

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

	private Image[] images = new Image[9];
	int state = 0;

	public ResourcePanel(GameBoard game) {

		images[0] = ImageReg.getInstance().getImageFromStr("Hive0");
		images[1] = ImageReg.getInstance().getImageFromStr("Hive1");
		images[2] = ImageReg.getInstance().getImageFromStr("Hive2");
		images[3] = ImageReg.getInstance().getImageFromStr("Hive3");
		images[4] = ImageReg.getInstance().getImageFromStr("Hive4");
		images[5] = ImageReg.getInstance().getImageFromStr("Hive5");
		images[6] = ImageReg.getInstance().getImageFromStr("Hive6");
		images[7] = ImageReg.getInstance().getImageFromStr("Hive6A");
		images[8] = ImageReg.getInstance().getImageFromStr("Hive6B");

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
		g.fillRect(0, 0, PANEL_WIDTH / 3, PANEL_HEIGHT);
		if (hive.getHoney() < 18) {
			g.drawImage(images[hive.getHoney() % 3], PANEL_WIDTH / 3, 0, null);
		} else {
			state = (state + 1) % 3;
			g.drawImage(images[state + 6], PANEL_WIDTH / 3, 0, null);
		}
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
