package model;

import java.awt.Point;

public abstract class Plant extends Thing {
	private GrowthState currentState;

	public Plant(Point initialLocation) {
		setLayer(2);
		setLocation(initialLocation);
		currentState = GrowthState.JustPlanted;
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

	public abstract void update();

	public abstract void grow();

	public abstract void replenishNectar();
}
