package model;

public class IdleStrategy implements BugStrategy {

	private Bug bug;
	private GameBoard board;

	public IdleStrategy(Bug bug) {
		this.bug = bug;
		this.board = GameBoard.getBoard();
	}

	@Override
	public void doNextAction() {
		bug.setObjectivePoint(board.getHive().getLocation());
		if (!bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
			bug.move(bug.getObjectiveThing().getLocation());
		}
	}

}
