package model;

import java.awt.Image;
import java.awt.Point;

//Everything is a thing
public abstract class Thing {
	public abstract void setLocation(Point loc);

	public abstract Point getLocation();

	public abstract void setImage(Image image);

	public abstract Image getImage();

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

	public abstract boolean contains(Point point);
}
