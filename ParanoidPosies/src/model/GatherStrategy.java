package model;

public class GatherStrategy implements BugStrategy{

	private Plant flower;
	
	public GatherStrategy(Plant someFlower){
		flower = someFlower;
	}
	
	@Override
	public void getNextAction(Bug thisBug, GameBoard gameBoard) {
		//TODO
	}
	
	public Plant getFlower(){
		return flower;
	}
	
	public void setFlower(Plant newFlower){
		flower = newFlower;
	}

}
