package model;

import java.awt.Image;
import java.awt.Point;

public abstract class Plant extends Thing {
	private int hp;
	private Image image;
	private GrowthState currentState;
	private String name;
	private int maxHP;
	private int halfMaxImageDimension;

	private boolean shouldBeCleanedUp;
	private int layer;

	public Plant(Point initialLocation) {
		layer = 2;
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
	public void updateHP(int valueToAdjustBy) {
		hp += valueToAdjustBy;
	}

	@Override
	public void setImage(Image image) {
		this.image = image;
		int imageHalfWidth = image.getWidth(null) / 2;
		int imageHalfHeight = image.getHeight(null) / 2;
		halfMaxImageDimension = Math.max(imageHalfWidth, imageHalfHeight);
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

	public boolean shouldBeCleanedUp() {
		return hp <= 0;
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

	@Override
	public void setLayer(int newLayer) {
		layer = newLayer;
	}

	public boolean contains(Point point) {
		int xdiff = Math.abs(getLocation().x - point.x);
		int ydiff = Math.abs(getLocation().y - point.y);
		return Math.max(xdiff, ydiff) < halfMaxImageDimension;
	}

}
