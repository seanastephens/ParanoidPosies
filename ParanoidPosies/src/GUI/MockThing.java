package GUI;

import java.awt.Image;
import java.awt.Point;

import model.Thing;

public class MockThing implements Thing {
	Point location;
	Image picture;

	public MockThing(Point loc) {
		location = loc;
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

	@Override
	public void die() {
		// TODO Auto-generated method stub

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

}
