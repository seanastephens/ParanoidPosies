package model;

import java.awt.Image;
import java.awt.Point;
import java.util.Timer;

public abstract class Plant implements Thing {
	private int hitPoints;
	private int lifespan;
	private Point location;
	private int maxNectar;
	private int currentNectar;
	private boolean hasBloomed;
	private Image image;
	private int timer;
	
	private final int layer = 2;
	private final int starting_nectar = 0;
	
	public Plant(Image image){
		
		currentNectar = starting_nectar;
		timer = 0;//Start life at 0, will be incremented by one each time update is called
	}
	
	@Override
	public void updateHP(int hp){
		hitPoints += hp;
	}

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
		hitPoints = hp;
	}

	@Override
	public int getHP() {
		return hitPoints;
	}

	@Override
	public abstract void die();

	

	@Override
	public int getLayer() {
		return layer;
	}
	
	@Override
	public void update(){
		timer++;
	}
	
	public abstract void grow();
	
	

}
