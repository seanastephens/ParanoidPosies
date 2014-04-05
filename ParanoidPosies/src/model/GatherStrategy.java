package model;

public class GatherStrategy implements BugStrategy {

	private Bug bug;
	private GameBoard board;

	public GatherStrategy(Bug bug, GameBoard board) {
		this.bug = bug;
		this.board = board;
	}

	@Override
	public void doNextAction() {
		if (!bug.getLocation().equals(bug.getObjective())) {
			bug.move(bug.getObjective().getLocation());
		} else if (bug.getLocation().equals(bug.getObjective())
				&& bug.getObjective() instanceof Posie) {
			if (bug instanceof Bee) {
				((Bee) bug).askFlowerForNectorOrSeeds();
				bug.setObjective(board.getHive());
				// TODO tell bee to deposit nectar and seeds at hive
			}
		} else if (bug.getLocation().equals(bug.getObjective())
				&& bug.getObjective() instanceof Hive) {
			if (bug instanceof Bee) {
				// ((Bee) bug).
				// bug.setObjective(gameBoard.getHive());
				// TODO tell bee to deposit nectar and seeds at hive
			}
		}
	}
}
