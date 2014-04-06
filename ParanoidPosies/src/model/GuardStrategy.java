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
		
		if(bug.getObjectivePoint() == null && bug.getObjectiveThing() != null){
			System.out.println("we have an objective thing");
			if (!bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
				System.out.println("we are not at the same location as our objective");
				bug.move(bug.getObjectiveThing().getLocation());
			} 
			else if (bug.getLocation().equals(bug.getObjectiveThing().getLocation())
					&& bug.getObjectiveThing() instanceof Caterpillar && !bug.getObjectiveThing().isDead()) {
				System.out.println("we are at the same location as a living catapillar");
				if (bug instanceof Bee) {
					System.out.println("we are a bee, lets attack that catapillar");
					bug.attack(bug.getObjectiveThing());
				}
			} 
			else if (bug instanceof Bee && bug.getObjectiveThing() instanceof Hive) {
				System.out.println("we are a bee and our objective is the hive");
				if (bug instanceof Bee) {
					System.out.println("go to the rally point instead");
					bug.setObjectiveToNull();
					bug.setObjectivePoint(rally);
				}
			}
			else if(bug.getLocation().equals(bug.getObjectiveThing().getLocation())
					&& bug.getObjectiveThing().isDead()){
				System.out.println("we are at our objective's location and it's dead so we will head to rally");
				bug.setObjectiveToNull();
				bug.setObjectivePoint(rally);
			}
		}
		else if(bug.getObjectiveThing() == null && bug.getObjectivePoint() != null){
			System.out.println("our objective is a point");
			rally = bug.getObjectivePoint();
			if (!bug.getLocation().equals(bug.getObjectivePoint())) {
				System.out.println("we are not yet at the objective point");
				bug.move(bug.getObjectivePoint());
			} 
			else if(bug.getLocation().equals(bug.getObjectivePoint()) && bug instanceof Bee){
				System.out.println("we are at the objective point and we are a bee");
				bug.setObjectiveToNull();
				bug.setObjectiveThing(bug.getClosestCaterpillar());
				if(bug.getObjectiveThing() == null){
					System.out.println("if our objective thing is still null after looking for a catapillar return to rally");
					bug.setObjectivePoint(rally);
				}
				else if(bug.getObjectiveThing() instanceof Hive){
					bug.setObjectiveToNull();
					bug.setObjectivePoint(rally);
				}
			}
		}
	}
}
