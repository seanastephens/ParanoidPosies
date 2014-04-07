package model;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Bug implements Thing{
	private Point location;
	private int hp;
	private int maxHP;
	private Image image;
	private int layer;
	private GameBoard gameboard;
	private int speed;
	private BugStrategy currentStrategy;
	private Thing objectiveThing;
	private Point objectivePoint;
	private String name;

	public Bug(GameBoard gameboard) {
		speed = 1; //Default speed for bugs
		layer = 1;
		this.gameboard = gameboard;
	}

	public Point getObjectivePoint() {
		return objectivePoint;
	}

	public void setObjectivePoint(Point newLoc) {
		objectivePoint = newLoc;
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

	public Thing getObjectiveThing() {
		return objectiveThing;
	}

	public void setObjectiveThing(Thing newObjective) {
		objectiveThing = newObjective;
	}

	public BugStrategy getStrategy() {
		return currentStrategy;
	}

	public boolean shouldBeCleanedUp() {
		return isDead();
	}

	public String getCriticalInfo() {
		String result = "";
		result += "HP=" + this.getHP() + " ";
		return result;
	}

	public String getType() {
		return this.getClass().getSimpleName();
	}

	public GameBoard getGameBoard() {
		return gameboard;
	}

	public void setGameBoard(GameBoard board) {
		gameboard = board;
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
		this.hp = hp;
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int newLayer){
		layer = newLayer;
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
	public abstract void update();

	@Override
	public void updateHP(int newHP) {
		hp += newHP;
	}

	/*
	 * Since things don't teleport, this is where decisions are made on which pixel to move to next.
	 * Because Random(int) goes from zero inclusive to some int exclusive.
	 * Before we had Random(int)+1 which would always return 1 defeating the purpose.
	 * The effect now is that for every 1 speed a Bug has the potential to move between 0 to 2 pixels without spazzing.
	 * When you set speed now think of it as an average speed for the bug.
	 */
	
	public void move(Point endLocation) {
		int moveConstant = 1;
		int randomConstant = 2; 
		Random rand = new Random();
		for(int i = 0; i < 2; i++){
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
				if(randNum == 0){
					place = this.getLocation();
				}
				this.setLocation(place);
			}
		}
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

	/*
	 * Takes the object of the thing being attacked so updates can be made to both objects.
	 */
	public abstract void attack(Thing thingBeingAttacked);

	public void setObjectiveToNull() {
		objectivePoint = null;
		objectiveThing = null;
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setName(String newName) {
		name = newName;
	}

	public abstract void giveNewRandomName();
	
	public String getName() {
		return name;
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
}
