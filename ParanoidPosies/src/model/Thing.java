package model;

import java.awt.Image;
import java.awt.Point;

//Everything is a thing
public abstract class Thing {

	private Point location;
	private Image image;
	private int halfMaxImageDimension;
	private int hp;
	private int maxHP;

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

	public void setHP(int hp) {
		this.hp = hp;
	}

	public void updateHP(int hp) {
		this.hp += hp;
	}

	public int getHP() {
		return hp;
	}

	public void setMaxHP(int newMaxHP) {
		maxHP = newMaxHP;
		setHP(maxHP);
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void updateMaxHP(int valueToAdjustHPBy) {
		setMaxHP(maxHP + valueToAdjustHPBy);
	}

	public boolean isDead() {
		return hp <= 0;
	}

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
