package model;

import java.awt.Image;
import java.awt.Point;
import java.util.List;
import java.util.Random;

public abstract class Bug implements Thing, UpgradeAttack, UpgradeSpeed, UpgradeTotalHP {
	private Point location;
	private int hp;
	private Image image;
	private final int layer = 1;
	private GameBoard gameboard;

	private BugStrategy currentStrategy;
	private Thing objectiveThing;
	private Point objectivePoint;
	
	private static final int RAND_RANGE = 100;
	private static int MOVE_PROBABILITY = (RAND_RANGE * 3)/10;

	public Bug(GameBoard gameboard) {
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

	public String getAction() {
		String result = "";
		result += "HP: " + this.getHP() + "\nLocation: " + this.getLocation().toString();
		return currentStrategy.getClass().getSimpleName();
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
	public void updateHP(int hp) {
		this.hp += hp;
	}

	// Since things don't teleport, this is where the animations take place to
	// move the Thing
	// from one place to another.
	public void move(Point endLocation) {
		int moveConstant = 1;
		Random rand = new Random();
		int randInt = rand.nextInt(RAND_RANGE);
		if(MOVE_PROBABILITY < randInt){
			if (!this.getLocation().equals(endLocation)) {
				if (this.getLocation().x < endLocation.x) {
					this.setLocation(new Point(this.getLocation().x + moveConstant,
							this.getLocation().y));
				}
				if (this.getLocation().x > endLocation.x) {
					this.setLocation(new Point(this.getLocation().x - moveConstant,
							this.getLocation().y));
				}
				if (this.getLocation().y < endLocation.y) {
					this.setLocation(new Point(this.getLocation().x, this.getLocation().y
							+ moveConstant));
				}
				if (this.getLocation().y > endLocation.y) {
					this.setLocation(new Point(this.getLocation().x, this.getLocation().y
							- moveConstant));
				}
			}
		}
		else{
			int randX = rand.nextInt(moveConstant) + 1;
			int randY = rand.nextInt(moveConstant) + 1;
			
			boolean subY = rand.nextBoolean();
			if(rand.nextBoolean()){
				randX = randX * -1;
			}
			if(rand.nextBoolean()){
				randY = randY * -1;
			}
			this.setLocation(new Point(this.getLocation().x + randX, this.getLocation().y + randY));
			
			
		}
	}
	

	// TODO handle null
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

	// TODO handle null
	public Thing getClosestCaterpillar() {
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
				if (aThing instanceof Caterpillar) {
					return (Caterpillar) aThing;
				}
			}
			multipleOf100++;
		}
		return gameboard.getHive();
	}

	// Takes the object of the thing being attacked so updates can be made to
	// both objects.
	public abstract void attack(Thing thingBeingAttacked);

	public void setObjectiveToNull() {
		objectivePoint = null;
		objectiveThing = null;
	}
}
