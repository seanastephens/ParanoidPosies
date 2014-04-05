package model;

public class GatherStrategy implements BugStrategy{

	private Plant flower;
	
	public GatherStrategy(Plant someFlower){
		flower = someFlower;
	}
	
	@Override
	public void getNextAction(Bug thisBug, GameBoard gameBoard) {
		if(!thisBug.getLocation().equals(thisBug.getObjective())){
			thisBug.move(thisBug.getObjective());
		}
		if(thisBug.getLocation().equals(thisBug.getObjective())){
			if(thisBug instanceof Bee){
				((Bee) thisBug).askFlowerForNectorOrSeeds();
				//TODO tell bee to move back to hive
				//TODO tell bee to deposit nectar and seeds at hive
			}
		}
	}
	
	public Plant getFlower(){
		return flower;
	}
	
	public void setFlower(Plant newFlower){
		flower = newFlower;
	}

}
