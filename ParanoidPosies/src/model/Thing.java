package model;

import java.awt.Image;
import java.awt.Point;

public interface Thing {
	public void setLocation(Point loc);
	public Point getLocation();
	public void setImage(Image image);
	public Image getImage();
	public void setHP(int hp);
	public int getHP();
	public void die();
	public void setLayer(int layer);
	public int getLayer();
	public void spawn(Point loc);
}
