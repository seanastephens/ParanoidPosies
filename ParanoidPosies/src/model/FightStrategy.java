package model;

public class FightStrategy implements BugStrategy {

	private Bug bug;
	private GameBoard board;

	public FightStrategy(Bug bug) {
		this.bug = bug;
		this.board = GameBoard.getBoard();
	}

	@Override
	public void doNextAction() {
		if (bug.getObjectiveThing().isDead() && bug instanceof Caterpillar) {
			bug.setObjectiveToNull();
			bug.setObjectiveThing(board.getClosest(Posie.class, bug.getLocation()));
			if (bug.getObjectiveThing() == null) {
				bug.setObjectiveThing(board.getHive());
			}
		} else if (!bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
			bug.move(bug.getObjectiveThing().getLocation());
		} else if (!bug.getObjectiveThing().isDead()
				&& bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
			bug.attack(bug.getObjectiveThing());
		} else if (bug.getObjectiveThing().isDead() && bug instanceof Bee) {
			bug.setStrategy(new MoveStrategy(bug), board.getHive());
		}

	}
}
