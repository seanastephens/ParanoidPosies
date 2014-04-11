package model;

import java.awt.Image;
import java.awt.Point;
import java.util.Random;

public abstract class Bug implements Thing {

	private static final Point DEFAULT_LOCATION = new Point(0, 0);
	private static final int DEFAULT_LAYER = 1;
	private static final int DEFAULT_HP = 0;
	private static final int DEFAULT_MAX_HP = 0;
	private static final int DEFAULT_pixelsPerMove = 1;
	private static final String DEFAULT_NAME = "ABSTRACT CLASS BUG DEFAULT NAME";

	private int hp;
	private int maxHP;
	private Point location;
	private int layer;
	private int pixelsPerMove;
	private BugStrategy currentStrategy;
	private Thing objectiveThing;
	private Point objectivePoint;
	private Image image;
	private GameBoard gameboard;
	private String name;

	private int halfMaxImageDimension;

	public Bug() {
		this.hp = DEFAULT_HP;
		this.maxHP = DEFAULT_MAX_HP;
		this.location = DEFAULT_LOCATION;
		this.layer = DEFAULT_LAYER;
		this.pixelsPerMove = DEFAULT_pixelsPerMove;
		this.currentStrategy = null;
		this.objectivePoint = null;
		this.objectiveThing = null;
		this.image = null;
		this.gameboard = GameBoard.getBoard();
		this.name = DEFAULT_NAME;
	}

	@Override
	public void setHP(int hp) {
		this.hp = hp;
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public void updateHP(int hp) {
		this.hp += hp;
	}

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
	public void setLocation(Point loc) {
		location = loc;
	}

	@Override
	public Point getLocation() {
		return location;
	}

	public void setSpeed(int newSpeed) {
		pixelsPerMove = newSpeed;
	}

	public int getSpeed() {
		return pixelsPerMove;
	}

	public void setStrategy(BugStrategy strat, Thing objective) {
		currentStrategy = strat;
		this.objectiveThing = objective;
		this.objectivePoint = null;
	}

	public void setStrategy(BugStrategy strat, Point newObjectivePoint) {
		currentStrategy = strat;
		this.objectivePoint = newObjectivePoint;
		this.objectiveThing = null;
	}

	public BugStrategy getStrategy() {
		return currentStrategy;
	}

	public Point getObjectivePoint() {
		return objectivePoint;
	}

	public void setObjectivePoint(Point newLoc) {
		objectivePoint = newLoc;
	}

	public Thing getObjectiveThing() {
		return objectiveThing;
	}

	public void setObjectiveThing(Thing newObjective) {
		objectiveThing = newObjective;
	}

	public void setObjectiveToNull() {
		objectivePoint = null;
		objectiveThing = null;
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

	public void setLayer(int newLayer) {
		layer = newLayer;
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}

	public boolean shouldBeCleanedUp() {
		return isDead();
	}

	@Override
	public boolean isDead() {
		if (hp <= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public String getHTMLDescription() {
		return getName() + ", a " + getClass().getSimpleName();
	}

	/*
	 * Since things don't teleport, this is where decisions are made on which
	 * pixel to move to next. Because Random(int) goes from zero inclusive to
	 * some int exclusive. Before we had Random(int)+1 which would always return
	 * 1 defeating the purpose. The effect now is that for every 1 speed a Bug
	 * has the potential to move between 0 to 2 pixels without spazzing. When
	 * you set speed now think of it as an average speed for the bug.
	 */
	public void move(Point endLocation) {
		int moveConstant = 1;
		int randomConstant = 2;
		Random rand = new Random();
		for (int i = 0; i < 3; i++) {
			Point place = new Point(this.getLocation().x, this.getLocation().y);
			if (!this.getLocation().equals(endLocation)) {
				if (this.getLocation().x < endLocation.x) {
					place.x += moveConstant;
				}
				if (this.getLocation().x > endLocation.x) {
					place.x -= moveConstant;
				}
				if (this.getLocation().y < endLocation.y) {
					place.y += moveConstant;
				}
				if (this.getLocation().y > endLocation.y) {
					place.y -= moveConstant;
				}
				int randNum = rand.nextInt(randomConstant);
				if (randNum == 0) {
					place = this.getLocation();
				}
				this.setLocation(place);
			}
		}
	}

	public abstract void attack(Thing thingBeingAttacked);

	public boolean contains(Point point) {
		int xdiff = Math.abs(getLocation().x - point.x);
		int ydiff = Math.abs(getLocation().y - point.y);
		return Math.max(xdiff, ydiff) < halfMaxImageDimension;
	}
}
