package model;

import java.awt.Point;
import java.util.Random;

public class GuardStrategy implements BugStrategy {
	private static final int RANDOM_CONST = 20;
	private Bug bug;
	private GameBoard board;
	private Point rally;

	public GuardStrategy(Bug bug) {
		this.bug = bug;
		this.board = GameBoard.getBoard();
	}

	@Override
	public void doNextAction() {

		if (bug.getObjectivePoint() == null && bug.getObjectiveThing() != null) {
			if (bug instanceof Bee && bug.getObjectiveThing() instanceof Hive) {
				bug.setObjectiveToNull();
				bug.setObjectivePoint(rally);
			} else if (!bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
				bug.move(bug.getObjectiveThing().getLocation());
			} else if (bug.getLocation().equals(bug.getObjectiveThing().getLocation())
					&& bug.getObjectiveThing() instanceof Caterpillar
					&& !bug.getObjectiveThing().isDead()) {
				if (bug instanceof Bee) {
					bug.attack(bug.getObjectiveThing());
				}
			}

			else if (bug.getLocation().equals(bug.getObjectiveThing().getLocation())
					&& bug.getObjectiveThing().isDead()) {
				bug.setObjectiveToNull();
				bug.setObjectiveThing(board.getClosest(Caterpillar.class, bug.getLocation()));
				if (bug.getObjectiveThing() == null) {
					bug.setObjectivePoint(rally);
				} else if (bug.getObjectiveThing() instanceof Hive) {
					bug.setObjectiveToNull();
					bug.setObjectivePoint(rally);
				}
			}
		} else if (bug.getObjectiveThing() == null && bug.getObjectivePoint() != null) {
			Random r = new Random();
			int x = bug.getObjectivePoint().x + r.nextInt(RANDOM_CONST) - RANDOM_CONST / 2;
			int y = bug.getObjectivePoint().y + r.nextInt(RANDOM_CONST) - RANDOM_CONST / 2;
			rally = new Point(x, y);
			if (!bug.getLocation().equals(bug.getObjectivePoint())) {
				bug.move(bug.getObjectivePoint());
			} else if (bug.getLocation().equals(bug.getObjectivePoint()) && bug instanceof Bee) {
				bug.setObjectiveToNull();
				bug.setObjectiveThing(board.getClosest(Caterpillar.class, bug.getLocation()));
				if (bug.getObjectiveThing() == null) {
					bug.setObjectivePoint(rally);
				} else if (bug.getObjectiveThing() instanceof Hive) {
					bug.setObjectiveToNull();
					bug.setObjectivePoint(rally);
				}
			}
		}
	}
}
