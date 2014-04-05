package model;

import java.awt.Image;
import java.awt.Point;
import java.util.Random;
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
	public int seedsDropped;
	public int maxSeedsToDrop;
	
	public boolean shouldBeCleanedUp;
	
	public final String PLANT_ACTION = "Growing happily.";
	
	private final int layer = 2;
	private final int starting_nectar = 0;
	
	public Plant(Point initialLocation){
		this.image = image;
		seedsDropped = 0;
		setLocation(initialLocation);
		currentNectar = starting_nectar;
		currentState = GrowthState.JustPlanted;
		timer = 0;//Start life at 0, will be incremented by one each time update is called
		hasBloomed = false;
		shouldBeCleanedUp = false;
	}
	
	public String getAction(){
		return PLANT_ACTION;
	}
	
	public String getType(){
		return this.getClass().getSimpleName();
	}
	
	@Override
	public abstract void updateHP(int hp);

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
			currentNectar = 0;
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
	
	public int takeNectar(int reduceBy){
		if(currentNectar >= reduceBy){
			currentNectar -= reduceBy;
			return reduceBy;
		}
		else{
			int temp = currentNectar;
			currentNectar = 0;
			return temp;
		}
	}
	
	//return how much nectar this plant has
	public int getNectar(){
		return currentNectar;
	}
	
	//return how many seeds the plant has dropped
	public int getSeeds(){
		return seedsDropped;
	}
	
	public int takeSeeds(){
		int temp = seedsDropped;
		seedsDropped = 0;
		shouldBeCleanedUp = true;
		return temp;
	}
	
	public boolean shouldBeCleanedUp(){
		return shouldBeCleanedUp;
	}
	
	public abstract void replenishNectar();
	

}
