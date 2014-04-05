package model;

public class GatherStrategy implements BugStrategy{

	private Plant flower;
	
	public GatherStrategy(Plant someFlower){
		flower = someFlower;
	}
	
	@Override
	public void getNextAction(Bug thisBug, GameBoard gameBoard) {
		if(!thisBug.getLocation().equals(thisBug.getObjective())){
			thisBug.move(thisBug.getObjective().getLocation());
		}
		else if(thisBug.getLocation().equals(thisBug.getObjective()) && thisBug.getObjective() instanceof Posie){
			if(thisBug instanceof Bee){
				((Bee) thisBug).askFlowerForNectorOrSeeds();
				thisBug.setObjective(gameBoard.getHive());
				//TODO tell bee to deposit nectar and seeds at hive
			}
		}
		else if(thisBug.getLocation().equals(thisBug.getObjective()) && thisBug.getObjective() instanceof Hive){
			if(thisBug instanceof Bee){
				((Bee) thisBug).
				thisBug.setObjective(gameBoard.getHive());
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
