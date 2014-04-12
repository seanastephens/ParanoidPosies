package model;

import java.awt.Point;

public abstract class Plant extends Thing {
	private GrowthState currentState;
	private String name;
	private int maxHP;
	private boolean shouldBeCleanedUp;

	public Plant(Point initialLocation) {
		setLayer(2);
		setLocation(initialLocation);
		currentState = GrowthState.JustPlanted;
		shouldBeCleanedUp = false;
	}

	public String getHTMLDescription() {
		return getName() + ", a " + getClass().getSimpleName();
	}

	public GrowthState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GrowthState newState) {
		currentState = newState;
	}

	@Override
	public abstract void update();

	public abstract void grow();

	public boolean shouldBeCleanedUp() {
		return isDead();
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
	public String getName() {
		return name;
	}
}
