package model;

import java.awt.Point;
import java.util.Random;

public class GatherStrategy implements BugStrategy {

	private Bug bug;

	public GatherStrategy(Bug aBug) {
		this.bug = aBug;
	}

	@Override
	public void doNextAction() {
		if (bug.getObjectiveThing() != null) {
			if (!bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
				bug.move(bug.getObjectiveThing().getLocation());
			} else if (bug.getLocation().equals(bug.getObjectiveThing().getLocation())
					&& bug.getObjectiveThing() instanceof Posie) {
				((Bee) bug).askFlowerForNectarOrSeeds();
				Point temp = new Point(GameBoard.getBoard().getHive().getLocation().x, GameBoard
						.getBoard().getHive().getLocation().y + 85);
				bug.setObjectiveToNull();
				bug.setObjectivePoint(temp);
			} else if (bug.getLocation().equals(bug.getObjectiveThing().getLocation())
					&& bug.getObjectiveThing() instanceof Hive) {
				Random random = new Random();
				int rLeftRight = random.nextInt(180) - 90;
				int rUpDown = random.nextInt(240) - 120 + 60; // +60 is to move
																// center of
																// random box
																// closer to bee
																// hive door
				Point temp = new Point(GameBoard.getBoard().getHive().getLocation().x + rLeftRight,
						GameBoard.getBoard().getHive().getLocation().y + rUpDown);
				bug.setStrategy(new SquareStrategy(bug), temp);
			}
		} else if (bug.getObjectivePoint() != null) {
			if (!bug.getLocation().equals(bug.getObjectivePoint())) {
				bug.move(bug.getObjectivePoint());
			} else if (bug.getLocation().equals(bug.getObjectivePoint())) {
				((Bee) bug).unloadNectarAndSeedsToHive();
				bug.setObjectiveThing(GameBoard.getBoard().getRandom(Posie.class));
				if (bug.getObjectiveThing() instanceof Hive) {
					// Random numbers used to create a random points within a
					// box around the Hive location
					// for bees to start their random square strategies.
					Random random = new Random();
					int rLeftRight = random.nextInt(180) - 90;
					int rUpDown = random.nextInt(240) - 120 + 60; // +60 is to
																	// move
																	// center of
																	// random
																	// box
																	// closer to
																	// bee hive
																	// door
					Point temp = new Point(GameBoard.getBoard().getHive().getLocation().x
							+ rLeftRight, GameBoard.getBoard().getHive().getLocation().y + rUpDown);
					bug.setStrategy(new SquareStrategy(bug), temp);
				}
			}
		}
	}
}
