package model;

import java.awt.Image;
import java.awt.Point;
import java.util.Timer;

public abstract class Plant implements Thing {
	public int hitPoints;
	public int lifespan;
	private Point location;
	public int maxNectar;
	public int currentNectar;
	public boolean hasBloomed;
	private Image image;
	public int timer;
	public GrowthState currentState;
	
	private final int layer = 2;
	private final int starting_nectar = 0;
	
	public Plant(Image image, Point initialLocation){
		this.image = image;
		setLocation(initialLocation);
		currentNectar = starting_nectar;
		currentState = GrowthState.JustPlanted;
		timer = 0;//Start life at 0, will be incremented by one each time update is called
		hasBloomed = false;
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
	public  boolean isDead(){
		if(hitPoints <= 0){
			return true;
		}
		else return false;
	}

	

	@Override
	public int getLayer() {
		return layer;
	}
	
	@Override
	public abstract void update();
	
	public abstract void grow();
	
	public void takeNectar(int reduceBy){
		if(currentNectar >= reduceBy){
			currentNectar -= reduceBy;
		}
		else currentNectar = 0;
	}
	
	//return how much nectar this plant has
	public int getNectar(){
		return currentNectar;
	}
	
	public abstract void replenishNectar();
	

}
