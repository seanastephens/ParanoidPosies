package model;

public class MoveStrategy implements BugStrategy{

	private Bug bug;
	private GameBoard board;

	public MoveStrategy(Bug bug, GameBoard board) {
		this.bug = bug;
		this.board = board;
	}
	
	@Override
	public void doNextAction() {
		if (!bug.getLocation().equals(bug.getObjectivePoint())) {
			bug.move(bug.getObjectiveThing().getLocation());
		}
	}

}
