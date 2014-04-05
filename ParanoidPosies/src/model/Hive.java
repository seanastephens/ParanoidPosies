package model;

import java.awt.Image;
import java.awt.Point;

public class Hive implements Thing{

	private Point location;
	private int hp;
	private Image image;
	private final int layer = 3;
	
	@Override
	public void setLocation(Point loc) {
		location = loc;
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void setHP(int hp) {
		this.hp = hp;
	}

	@Override
	public void updateHP(int hp) {
		this.hp += hp;
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
