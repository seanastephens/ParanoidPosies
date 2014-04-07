package model;

import java.awt.Image;
import java.awt.Point;

public abstract class Plant implements Thing {
	private int hp;
	private Point location;
	private Image image;
	private GrowthState currentState;
	private String name;
	private int maxHP;

	private boolean shouldBeCleanedUp;
	private int layer;

	public Plant(Point initialLocation) {
		layer = 2;
		setLocation(initialLocation);
		currentState = GrowthState.JustPlanted;
		shouldBeCleanedUp = false;
	}

	public abstract String getCriticalInfo();

	public String getType() {
		return this.getClass().getSimpleName();
	}

	public GrowthState getCurrentState(){
		return currentState;
	}
	
	public void setCurrentState(GrowthState newState){
		currentState = newState;
	}
	
	@Override
	public void updateHP(int valueToAdjustBy){
		hp += valueToAdjustBy;
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
	public void setHP(int newHP) {
		hp = newHP;
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public boolean isDead() {
		if (hp <= 0) {
			return true;
		} else
			return false;
	}

	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public abstract void update();

	public abstract void grow();

	public void setShouldBeCleanedUp(boolean value){
		shouldBeCleanedUp = value;
	}
	
	public boolean shouldBeCleanedUp() {
		return shouldBeCleanedUp;
	}

	public abstract void replenishNectar();

	@Override
	public void setMaxHP(int newMaxHP) {
		maxHP = newMaxHP;
	}

	@Override
	public int getMaxHP() {
		return maxHP;
	}

	@Override
	public void updateMaxHP(int valueToAdjustHPBy) {
		maxHP += valueToAdjustHPBy;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public void setLayer(int newLayer){
		layer = newLayer;
	}
	
}
