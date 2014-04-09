package GUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Thing;

public class PopupPanel extends JPanel {

	private int BORDER_SPACING = 12;

	private int PANEL_WIDTH = 200;

	private int TEXT_WIDTH = PANEL_WIDTH - 2 * BORDER_SPACING;

	private Thing thing;
	private JLabel text = new JLabel();
	private FontMetrics fontMetrics;

	public PopupPanel(Thing thing) {
		fontMetrics = text.getFontMetrics(text.getFont());
		this.thing = thing;

		setLayout(null);
		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border titleBorder = BorderFactory.createTitledBorder(etchedBorder);
		((TitledBorder) titleBorder).setTitle(thing.getClass().getSimpleName());
		((TitledBorder) titleBorder).setTitleColor(Color.YELLOW);
		setBorder(titleBorder);

		text.setLocation(BORDER_SPACING, BORDER_SPACING);
		text.setText("<html>" + thing.getHTMLDescription() + "</html>");
		resizeTextPanel();
		setSize(PANEL_WIDTH, 2 * BORDER_SPACING + text.getHeight());

		add(text);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		text.setForeground(Color.WHITE);
		text.setText("<html>" + thing.getHTMLDescription() + "</html>");
	}

	private void resizeTextPanel() {
		int widthInPixels = fontMetrics.stringWidth(text.getText()) + 10;
		int heightInLines = (widthInPixels) / TEXT_WIDTH + 1;
		int heightInPixels = heightInLines * fontMetrics.getHeight();
		text.setSize(TEXT_WIDTH, heightInPixels);
	}
}
