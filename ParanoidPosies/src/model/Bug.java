package model;

import java.awt.Image;
import java.awt.Point;
import java.util.List;

public abstract class Bug implements Thing {
	private Point location;
	private int hp;
	private Image image;
	private final int layer = 1;
	private GameBoard gameboard;

	private BugStrategy currentStrategy;
	private Thing objectiveThing;
	private Point objectivePoint;

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

	public Thing getObjective() {
		return objectiveThing;
	}

	public void setObjective(Thing newObjective) {
		objectiveThing = newObjective;
	}

	public BugStrategy getStrategy() {
		return currentStrategy;
	}

	public boolean shouldBeCleanedUp() {
		return isDead();
	}

	public String getAction() {
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
		if (this.getLocation() != endLocation) {
			if (this.getLocation().x < endLocation.x) {
				this.setLocation(new Point(this.getLocation().x + 1, this.getLocation().y));
			}
			if (this.getLocation().x > endLocation.x) {
				this.setLocation(new Point(this.getLocation().x - 1, this.getLocation().y));
			}
			if (this.getLocation().y < endLocation.y) {
				this.setLocation(new Point(this.getLocation().x, this.getLocation().y + 1));
			}
			if (this.getLocation().y > endLocation.y) {
				this.setLocation(new Point(this.getLocation().x, this.getLocation().y - 1));
			}
		}
	}

	public Plant getClosestPosie() {
		List<Thing> things;
		int multipleOf100 = 1;
		int maxMultipleOf100 = 20;
		int hundred = 5;
		while (multipleOf100 < maxMultipleOf100) {
			things = this.getGameBoard().getThingsBetween(
					this.getObjective().getLocation().x - hundred * multipleOf100,
					this.getObjective().getLocation().y - hundred * multipleOf100,
					this.getObjective().getLocation().x + hundred * multipleOf100,
					this.getObjective().getLocation().y + hundred * multipleOf100);
			for (Thing aThing : things) {
				if (aThing instanceof Posie) {
					return (Posie) aThing;
				}
			}
			multipleOf100++;
		}
		// end of the game.
		return null;
	}

	// Takes the object of the thing being attacked so updates can be made to
	// both objects.
	public abstract void attack(Thing thingBeingAttacked);
}
