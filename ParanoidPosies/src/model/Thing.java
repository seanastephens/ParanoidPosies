package model;

import java.awt.Image;
import java.awt.Point;

//Everything is a thing
public interface Thing {
	public void setLocation(Point loc);
	public Point getLocation();
	public void setImage(Image image);
	public Image getImage();
	public void setHP(int hp);
	public void updateHP(int hp);
	public int getHP();
	public void setMaxHP(int newMaxHP);
	public int getMaxHP();
	public void updateMaxHP(int valueToAdjustHPBy);
	public boolean isDead();
	public int getLayer();
	public void update();
	public String getName();
	public void setName(String newName);
	public String getType();
	public String getCriticalInfo();
	public boolean shouldBeCleanedUp();
}
