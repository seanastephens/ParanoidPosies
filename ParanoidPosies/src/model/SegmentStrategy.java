package model;

import java.awt.Point;
import java.util.Random;

public class SegmentStrategy implements BugStrategy{

	private Bug bug;
	private GameBoard board;
	private int velocity=1;

	public SegmentStrategy(Bug bug, GameBoard board) {
		this.bug = bug;
		this.board = board;
	}
	
	public void move(Point endLocation, int speed) {
		int moveConstant = speed;
		if (!bug.getLocation().equals(endLocation)) {
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
		if (bug.getLocation().distance(bug.getObjectivePoint())<bug.getImage().getHeight(null)/2) {
			velocity =  (int)(-2*(bug.getLocation().distance(bug.getObjectivePoint())-bug.getImage().getHeight(null))/(bug.getImage().getHeight(null)));
		} else if (bug.getLocation().distance(bug.getObjectivePoint())>bug.getImage().getHeight(null)) {
			velocity = (int)(2*(bug.getLocation().distance(bug.getObjectivePoint())-bug.getImage().getHeight(null))/(bug.getImage().getHeight(null)));
		}
		move(bug.getObjectiveThing().getLocation(), velocity);
	}

}
