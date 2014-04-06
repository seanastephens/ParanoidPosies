package model;

import java.awt.Point;

public class SegmentStrategy implements BugStrategy {

	private Bug bug;
	private GameBoard board;
	private boolean forward;

	public SegmentStrategy(Bug bug, GameBoard board) {
		this.bug = bug;
		this.board = board;
		forward = false;
	}

	public void move(Point endLocation) {
		int moveConstant = 1;
		if (bug.getLocation().distance(bug.getObjectiveThing().getLocation()) < bug.getImage()
				.getWidth(null) * 1 / 2) {
			forward = false;
		} else if (bug.getLocation().distance(bug.getObjectiveThing().getLocation()) > bug
				.getImage().getWidth(null) * 3 / 4) {
			forward = true;
		}

		if (forward) {
			if (bug.getLocation().x < endLocation.x) {
				bug.setLocation(new Point(bug.getLocation().x + moveConstant, bug.getLocation().y));
			}
			if (bug.getLocation().x > endLocation.x) {
				bug.setLocation(new Point(bug.getLocation().x - moveConstant, bug.getLocation().y));
			}
			if (bug.getLocation().y < endLocation.y) {
				bug.setLocation(new Point(bug.getLocation().x, bug.getLocation().y + moveConstant));
			}
			if (bug.getLocation().y > endLocation.y) {
				bug.setLocation(new Point(bug.getLocation().x, bug.getLocation().y - moveConstant));
			}
		}
	}

	@Override
	public void doNextAction() {
		move(bug.getObjectiveThing().getLocation());
		move(bug.getObjectiveThing().getLocation());
	}

}
