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
	private Timer timer;
	
	private final int layer = 2;
	private final int starting_nectar = 0;
	
	public Plant(Image image){
		
		currentNectar = starting_nectar;
		timer = new Timer();
		/*
		 * Specific plant class will schedule grow and death events with this timer
		 * using its particular lifespan.
		 */
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
	public abstract void spawn(Point loc);
	
	

}
