package model;

import java.awt.Point;
import java.util.Random;

//Your bug is crazy and just moves in a square.
public class SquareStrategy implements BugStrategy {

	public static int SQUARE_CONST = 50;
	private Bug bug;
	private int flag;

	public SquareStrategy(Bug bug) {
		this.bug = bug;
		Random random = new Random();
		int randomInt = random.nextInt(4);
		flag = randomInt;
	}

	@Override
	public void doNextAction() {
		bug.move(bug.getObjectivePoint());
		if (bug.getLocation().equals(bug.getObjectivePoint())){
			if (flag == 0) {
				bug.setObjectivePoint(new Point(bug.getLocation().x, bug.getLocation().y
						+ SQUARE_CONST));
				flag = 1;
			} else if (flag == 2) {
				bug.setObjectivePoint(new Point(bug.getLocation().x, bug.getLocation().y
						- SQUARE_CONST));
				flag = 3;
			} else if (flag == 3) {
				bug.setObjectivePoint(new Point(bug.getLocation().x - SQUARE_CONST, bug
						.getLocation().y));
				flag = 0;
			} else if (flag == 1) {
				bug.setObjectivePoint(new Point(bug.getLocation().x + SQUARE_CONST, bug
						.getLocation().y));
				flag = 2;
			}
		}
	}
}
