package model;

public class MoveStrategy implements BugStrategy {

	private Bug bug;

	public MoveStrategy(Bug bug) {
		this.bug = bug;
	}

	@Override
	public void doNextAction() {
		if (!bug.getLocation().equals(bug.getObjectivePoint())) {
			bug.move(bug.getObjectivePoint());
		} else {
			bug.setStrategy(new SquareStrategy(bug), bug.getLocation());
		}
	}
}
