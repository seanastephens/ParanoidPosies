package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Thing;

public class PopupPanel extends JPanel {

	private int WIDTH = 180; // default
	private int HEIGHT = 150; // default
	private int LINE_HEIGHT = 24; // depends on font.
	private int LINE_OFFSET = 12;
	private int MAX_CHARS_PER_LINE = 18; // should be about 1/8 of WIDTH
	private int TITLE_OFFSET = 20;

	private Thing thing;
	private JLabel name = new JLabel();
	private JLabel hp = new JLabel();
	private JLabel action = new JLabel();
	private int actionLineCount = 0; // DEFAULT, should be overriden with
										// formatters.
	private int nameLineCount = 0;
	private int hpLineCount = 1;
	private int maxNumCharsPerLine = 0;

	public PopupPanel(Thing thing) {
		this.thing = thing;

		setLayout(null);
		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border titleBorder = BorderFactory.createTitledBorder(etchedBorder);
		((TitledBorder) titleBorder).setTitle(thing.getType());
		((TitledBorder) titleBorder).setTitleColor(Color.YELLOW);
		setBorder(titleBorder);

		dynamicResize();

		add(name);
		add(hp);
		add(action);

		name.setForeground(Color.WHITE);
		hp.setForeground(Color.WHITE);
		action.setForeground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {

		dynamicResize();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		name.setText("<html>" + getModNameStr() + "</html>");
		hp.setText("<html>Health: " + thing.getHP() + "</html>");
		action.setText("<html>" + getModActionStr() + "</html>");
	}

	private String getModActionStr() {
		String[] words = thing.getCriticalInfo().split(" ");
		String ret = words[0];
		int numChars = ret.length();
		actionLineCount = 1;
		for (int i = 1; i < words.length; i++) {
			if (numChars + words[i].length() < MAX_CHARS_PER_LINE) {
				ret += " " + words[i];
				numChars += words[i].length();
				maxNumCharsPerLine = Math.max(numChars, maxNumCharsPerLine);
			} else {
				ret += "<br>" + words[i];
				actionLineCount++;
				numChars = words[i].length();
			}
		}

		return ret;
	}

	private String getModNameStr() {
		String[] words = (thing.getName() + ", a " + thing.getType()).split(" ");
		String ret = words[0];
		int numChars = ret.length();
		nameLineCount = 1;
		for (int i = 1; i < words.length; i++) {
			if (numChars + words[i].length() < MAX_CHARS_PER_LINE) {
				ret += " " + words[i];
				numChars += words[i].length();
				maxNumCharsPerLine = Math.max(numChars, maxNumCharsPerLine);
			} else {
				ret += "<br>" + words[i];
				nameLineCount++;
				numChars = words[i].length();
			}
		}

		return ret;
	}

	private void dynamicResize() {

		HEIGHT = (actionLineCount + hpLineCount + nameLineCount) * LINE_HEIGHT + 2 * TITLE_OFFSET;

		name.setSize(WIDTH, nameLineCount * LINE_HEIGHT + TITLE_OFFSET);
		hp.setSize(WIDTH, hpLineCount * LINE_HEIGHT + TITLE_OFFSET);
		action.setSize(WIDTH, actionLineCount * LINE_HEIGHT + TITLE_OFFSET);

		name.setLocation(LINE_OFFSET, 0);
		hp.setLocation(LINE_OFFSET, nameLineCount * LINE_HEIGHT + TITLE_OFFSET);
		action.setLocation(LINE_OFFSET, (hpLineCount + nameLineCount) * LINE_HEIGHT + TITLE_OFFSET);

		setSize(WIDTH, HEIGHT);
	}
}
