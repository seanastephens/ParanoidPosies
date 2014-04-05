package model;

import java.awt.Point;

//Your bug is crazy and just moves in a square.
public class SquareStrategy implements BugStrategy {

	public static int SQUARE_CONST = 50;
	private GameBoard board;
	private Bug bug;

	public SquareStrategy(Bug bug, GameBoard board) {
		this.bug = bug;
		this.board = board;

		if (bug.getLocation().equals(bug.getObjectivePoint())) {
			bug.setObjectivePoint(new Point(bug.getLocation().x + SQUARE_CONST, bug.getLocation().y
					+ SQUARE_CONST));
		}
	}

	public GameBoard getBoard() {
		return board;
	}

	@Override
	public void doNextAction() {

		Point previousPoint = bug.getLocation();
		bug.move(bug.getObjectivePoint());

		if (bug.getLocation().equals(bug.getObjectivePoint())) {
			if (bug.getLocation().x > previousPoint.x) {
				bug.setObjectivePoint(new Point(bug.getLocation().x, bug.getLocation().y
						+ SQUARE_CONST));
			} else if (bug.getLocation().x < previousPoint.x) {
				bug.setObjectivePoint(new Point(bug.getLocation().x, bug.getLocation().y
						- SQUARE_CONST));
			} else if (bug.getLocation().y > previousPoint.y) {
				bug.setObjectivePoint(new Point(bug.getLocation().x - SQUARE_CONST, bug
						.getLocation().y));
			} else if (bug.getLocation().y < previousPoint.y) {
				bug.setObjectivePoint(new Point(bug.getLocation().x + SQUARE_CONST, bug
						.getLocation().y));
			}
		}

	}
}
