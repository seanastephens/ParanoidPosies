package model;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Bug implements Thing {

	private static final Point DEFAULT_LOCATION = new Point(0, 0);
	private static final int DEFAULT_LAYER = 1;
	private static final int DEFAULT_HP = 0;
	private static final int DEFAULT_MAX_HP = 0;
	private static final int DEFAULT_pixelsPerMove = 1;
	private static final String DEFAULT_NAME = "ABSTRACT CLASS BUG DEFAULT NAME";

	private static final int RAND_RANGE = 100;
	private static final int PERCENT_CHANCE_RANDOM_MOVE = 30;

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

	public Bug(GameBoard gameboard) {
		this.hp = DEFAULT_HP;
		this.maxHP = DEFAULT_MAX_HP;
		this.location = DEFAULT_LOCATION;
		this.layer = DEFAULT_LAYER;
		this.pixelsPerMove = DEFAULT_pixelsPerMove;
		this.currentStrategy = null;
		this.objectivePoint = null;
		this.objectiveThing = null;
		this.image = null;
		this.gameboard = gameboard;
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

	public GameBoard getGameBoard() {
		return gameboard;
	}

	@Override
	public void setImage(Image image) {
		this.image = image;
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

	public String getCriticalInfo() {
		String result = "";
		result += "HP=" + this.getHP() + " ";
		return result;
	}

	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public String getType() {
		return getClass().getSimpleName();
	}

	public void move(Point endLocation) {
		Random rand = new Random();

		Point currentPoint = this.getLocation();
		int currentX = currentPoint.x;
		int currentY = currentPoint.y;
		Point newPoint = currentPoint;

		if (rand.nextInt(RAND_RANGE) < PERCENT_CHANCE_RANDOM_MOVE) {
			int randX = rand.nextInt(2 * pixelsPerMove + 1) - pixelsPerMove;
			int randY = rand.nextInt(2 * pixelsPerMove + 1) - pixelsPerMove;
			newPoint = new Point(currentX + randX, currentY + randY);
		} else {
			if (!currentPoint.equals(endLocation)) {
				int newX = currentX;
				int newY = currentY;
				newX += signature(endLocation.x - currentX) * pixelsPerMove;
				newY += signature(endLocation.y - currentY) * pixelsPerMove;
				newPoint = new Point(newX, newY);
			}
		}
		this.setLocation(newPoint);
	}

	private int signature(int x) {
		if (x > 0) {
			return 1;
		}
		if (x < 0) {
			return -1;
		}
		return 0;
	}

	public Thing getClosestPosie() {
		List<Thing> things;
		int multipleOf100 = 1;
		int maxMultipleOf100 = 20;
		int hundred = 100;
		while (multipleOf100 < maxMultipleOf100) {
			things = this.getGameBoard().getThingsBetween(
					this.getLocation().x - hundred * multipleOf100,
					this.getLocation().y - hundred * multipleOf100,
					this.getLocation().x + hundred * multipleOf100,
					this.getLocation().y + hundred * multipleOf100);
			for (Thing aThing : things) {
				if (aThing instanceof Posie && this instanceof Bee) {
					return (Posie) aThing;
				}
				if (aThing instanceof Posie && this instanceof Caterpillar) {
					if (!aThing.isDead()) {
						return aThing;
					}
				}
			}
			multipleOf100++;
		}
		return gameboard.getHive();
	}

	public Thing getRandomPosie() {
		List<Thing> things;
		int distance = 1000;
		things = this.getGameBoard().getThingsBetween(this.getLocation().x - distance,
				this.getLocation().y - distance, this.getLocation().x + distance,
				this.getLocation().y + distance);
		List<Thing> posies = new ArrayList<Thing>();
		for (Thing aThing : things) {
			if (aThing instanceof Posie) {
				posies.add((Posie) aThing);
			}
		}
		if (posies == null || posies.size() == 0) {
			return gameboard.getHive();
		}
		Collections.shuffle(posies);
		Posie aPosie = (Posie) posies.get(0);
		if (this instanceof Bee) {
			return posies.get(0);
		}
		if (this instanceof Caterpillar) {
			for (Thing posie : posies) {
				if (!posie.isDead()) {
					return posies.get(0);
				}
			}
		}
		return gameboard.getHive();
	}

	public Thing getClosestCaterpillar() {
		List<Thing> things;
		int multipleOf100 = 1;
		int maxMultipleOf100 = 10;
		int hundred = 100;
		while (multipleOf100 < maxMultipleOf100) {
			things = this.getGameBoard().getThingsBetween(
					this.getLocation().x - hundred * multipleOf100,
					this.getLocation().y - hundred * multipleOf100,
					this.getLocation().x + hundred * multipleOf100,
					this.getLocation().y + hundred * multipleOf100);
			for (Thing aThing : things) {
				if (aThing instanceof Caterpillar && !(aThing instanceof SegmentedCaterpillarBody)) {
					return (Caterpillar) aThing;
				}
			}
			multipleOf100++;
		}
		return gameboard.getHive();
	}

	public abstract void attack(Thing thingBeingAttacked);
}
