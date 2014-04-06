package model;

public class FightStrategy implements BugStrategy{

	private Bug bug;
	private GameBoard board;

	public FightStrategy(Bug bug, GameBoard board) {
		this.bug = bug;
		this.board = board;
	}
	
	@Override
	public void doNextAction() {
		if (!bug.getLocation().equals(bug.getObjectiveThing().getLocation())) {
			bug.move(bug.getObjectiveThing().getLocation());
		} else if (!bug.getObjectiveThing().isDead() && 
				bug.getLocation().equals(bug.getObjectiveThing().getLocation())){
			bug.attack(bug.getObjectiveThing());
		}
		else if(bug.getObjectiveThing().isDead() && bug instanceof Bee){
			bug.setStrategy(new MoveStrategy(bug, board), board.getHive());
		}
		else if(bug.getObjectiveThing().isDead() && bug instanceof Caterpillar){
			bug.setObjectiveToNull();
			bug.setObjectiveThing(bug.getClosestPosie());
			if(bug.getObjectiveThing() == null){
				bug.setObjectiveThing(board.getHive());
			}
		}
	}
}
