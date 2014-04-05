package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResourcePanel extends JPanel {

	private MockHive hive;

	public ResourcePanel(MockHive hive) {
		this.hive = hive;

		setBackground(Color.GREEN);
		setSize(new Dimension(100, 100));

		setLayout(new FlowLayout());

		JLabel honeyLabel = new JLabel("Honey " + hive.getHoney());
		add(honeyLabel);
	}

}
