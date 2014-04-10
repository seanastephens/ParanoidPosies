package model;

public class MoveStrategy implements BugStrategy {

	private Bug bug;
	private GameBoard board;

	public MoveStrategy(Bug bug) {
		this.bug = bug;
		this.board = GameBoard.getBoard();
	}

	@Override
	public void doNextAction() {
		if (!bug.getLocation().equals(bug.getObjectivePoint())) {
			bug.move(bug.getObjectivePoint());
		}
	}

}
