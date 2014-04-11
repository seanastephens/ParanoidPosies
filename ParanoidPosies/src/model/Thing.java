package model;

import java.awt.Image;
import java.awt.Point;

//Everything is a thing
public abstract class Thing {

	private Point location;
	private Image image;
	private int halfMaxImageDimension;

	public void setLocation(Point loc) {
		location = loc;
	}

	public Point getLocation() {
		return location;
	}

	public void setImage(Image im) {
		image = im;
		int imageHalfWidth = image.getWidth(null) / 2;
		int imageHalfHeight = image.getHeight(null) / 2;
		halfMaxImageDimension = Math.max(imageHalfWidth, imageHalfHeight);
	}

	public Image getImage() {
		return image;
	}

	public abstract void setHP(int hp);

	public abstract void updateHP(int hp);

	public abstract int getHP();

	public abstract void setMaxHP(int newMaxHP);

	public abstract int getMaxHP();

	public abstract void updateMaxHP(int valueToAdjustHPBy);

	public abstract boolean isDead();

	public abstract void setLayer(int newLayer);

	public abstract int getLayer();

	public abstract void update();

	public abstract String getName();

	public abstract void setName(String newName);

	public abstract String getHTMLDescription();

	public abstract boolean shouldBeCleanedUp();

	public boolean contains(Point point) {
		int xdiff = Math.abs(getLocation().x - point.x);
		int ydiff = Math.abs(getLocation().y - point.y);
		return Math.max(xdiff, ydiff) < halfMaxImageDimension;
	};
}
