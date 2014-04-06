package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.ImageReg;

public class EndGamePanel extends JPanel{
	
	public EndGamePanel(){
		
		setLayout(null);
		setSize(300,300);
		setLocation(400,250);
		String loseString = "<html><center>You lost! Feel free to enjoy the music :D</center></html>";
		JLabel loseLabel = new JLabel(loseString);
		loseLabel.setSize(250, 30);
		loseLabel.setLocation(25, 220);
		add(loseLabel);
		JButton loseButton = new JButton("<html><center>End the game already!</center></html>");
		loseButton.setSize(200, 30);
		loseButton.setLocation(50, 250);
		loseButton.setBackground(new Color(0xcc5500));
		loseButton.setBorderPainted(false);
		loseButton.setMargin(null);
		Font font = new Font(Font.SANS_SERIF, Font.BOLD , 12);
		loseButton.setOpaque(false);
		loseButton.setFont(font);
		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border titleBorder = BorderFactory.createTitledBorder(etchedBorder);
		((TitledBorder) titleBorder).setTitle(loseButton.getText());
		((TitledBorder) titleBorder).setTitleColor(Color.YELLOW);
		setBorder(titleBorder);
		add(loseButton);
		EndGameListener listener = new EndGameListener();
		loseButton.addActionListener(listener);
		setBackground(Color.BLACK);
		setVisible(true);
	}
	
	private class EndGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		Image image = ImageReg.getInstance().getImageFromStr("DeadBees");
		g.drawImage(image, 0, 0, null);
		paintChildren(g);
	}
}
