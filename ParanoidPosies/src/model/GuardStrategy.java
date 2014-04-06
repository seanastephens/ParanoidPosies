package model;

import java.awt.Point;

public class GuardStrategy implements BugStrategy{
	private Bug bug;
	private GameBoard board;
	private Point rally;

	public GuardStrategy(Bug bug, GameBoard board) {
		this.bug = bug;
		this.board = board;
	}

	@Override
	public void doNextAction() {
		
		if(bug.getObjectivePoint() == null){
			if (!bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
				bug.move(bug.getObjectiveThing().getLocation());
			} else if (bug.getLocation().equals(bug.getObjectiveThing().getLocation())
					&& bug.getObjectiveThing() instanceof Caterpillar && !bug.getObjectiveThing().isDead()) {
				if (bug instanceof Bee) {
					bug.attack(bug.getObjectiveThing());
				}
			} else if (bug instanceof Bee && bug.getObjectiveThing() instanceof Hive) {
				if (bug instanceof Bee) {
					bug.setObjectivePoint(rally);
				}
			}
			else if(bug.getLocation().equals(bug.getObjectiveThing().getLocation())
					&& bug.getObjectiveThing() == null){
				bug.setObjectivePoint(rally);
			}
		}
		else if(bug.getObjectiveThing() == null){
			rally = bug.getObjectivePoint();
			if (!bug.getLocation().equals(bug.getObjectivePoint())) {
				bug.move(bug.getObjectivePoint());
			} 
			else if(bug.getLocation().equals(bug.getObjectivePoint()) && bug instanceof Bee){
				bug.setObjectiveThing(bug.getClosestCaterpillar());
				if(bug.getObjectiveThing() == null){
					bug.setObjectivePoint(rally);
				}
			}
		}
	}
}
