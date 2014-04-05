package model;

//The bugs are strategizing

// TODO Need strategies for when bugs want to attack, gather/harvest, a default strategy
//default strategy could be to move randomly(crazy/bored bug), or stay idle(could add to idle list)

public interface BugStrategy {
	public void getNextAction(Bug thisBug, GameBoard gameBoard);
}
