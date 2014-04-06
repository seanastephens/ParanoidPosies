package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.ImageReg;

public class EndGamePanel extends JPanel{
	
	public EndGamePanel(){
		setSize(250,250);
		setLocation(990,520);
		setLayout(null);
		
		String loseString = "You lost! Feel free to enjoy the music by not clicking :D";
		JLabel loseLabel = new JLabel(loseString);
		loseLabel.setSize(200, 30);
		loseLabel.setLocation(50, 200);
		add(loseLabel);
		System.exit(0);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Image image = ImageReg.getInstance().getImageFromStr("DeadBees");
		g.drawImage(image, 0, 0, null);
	}
}
