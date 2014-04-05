package GUI;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Thing;

public class MockThing implements Thing {
	Point location;
	Image picture;

	public MockThing(Point loc) {
		location = loc;
		try {
			picture = ImageIO.read(new File("images/bee.png"));
		} catch (IOException e) {
			System.out.println("Failed loading bee.");
		}
	}

	@Override
	public void setLocation(Point loc) {
		location = loc;

	}

	@Override
	public Point getLocation() {
		return location;
	}

	public void setImage() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return picture;
	}

	@Override
	public void setHP(int hp) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setLayer(int layer) {
		// TODO Auto-generated method stub

	}

	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setImage(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateHP(int hp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

}
