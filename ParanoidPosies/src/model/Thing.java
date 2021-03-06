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
	private int layer;
	private String name;

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

	public void setLayer(int newLayer) {
		layer = newLayer;
	}

	public int getLayer() {
		return layer;
	}

	public boolean shouldBeCleanedUp() {
		return isDead();
	}

	public boolean contains(Point point) {
		int xdiff = Math.abs(getLocation().x - point.x);
		int ydiff = Math.abs(getLocation().y - point.y);
		return Math.max(xdiff, ydiff) < halfMaxImageDimension;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public abstract String getHTMLDescription();

	public abstract void update();

}
