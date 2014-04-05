package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ParanoidPosieGUI extends JFrame {

	public static void main(String[] args) {
		new ParanoidPosieGUI().setVisible(true);
	}

	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 720;

	private MockHive hive;

	public ParanoidPosieGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		GameInterface game = new MockGame();

		setLayout(null);
		JPanel resourcePanel = new ResourcePanel(hive);

	}

}
