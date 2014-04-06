package model;

import java.util.Random;

public class GatherStrategy implements BugStrategy {

	private Bug bug;
	private GameBoard board;

	public GatherStrategy(Bug bug, GameBoard board) {
		this.bug = bug;
		this.board = board;
	}

	@Override
	public void doNextAction() {
		if (!bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
			bug.move(bug.getObjectiveThing().getLocation());
		} else if (bug.getLocation().equals(
				bug.getObjectiveThing().getLocation())
				&& bug.getObjectiveThing() instanceof Posie) {
			if (bug instanceof Bee) {
				((Bee) bug).askFlowerForNectarOrSeeds();
				bug.setObjectiveThing(board.getHive());
			}
		} else if (bug.getLocation().equals(
				bug.getObjectiveThing().getLocation())
				&& bug.getObjectiveThing() instanceof Hive) {
			if (bug instanceof Bee) {
				((Bee) bug).unloadNectarAndSeedsToHive();
				Random random = new Random();
				int randomNum = random.nextInt(10);
				bug.setObjectiveToNull();
				if(randomNum < 2){
					bug.setObjectiveThing(bug.getClosestPosie());
				}
				else{
					bug.setObjectiveThing(bug.getRandomPosie());
				}
				
			}
		}
	}
}
