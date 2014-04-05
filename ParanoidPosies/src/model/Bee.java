package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bee extends Bug {

	private int nector;
	private List<String> beeNames;
	private String name;
	private int nectorToGet;
	private Plant nearestPlant;
	private static final int maxNector = 10;
	public static final int BEE_HP = 5;
	public static final int BEE_ATTACK_DAMAGE = 1;
	public static final String BEE_IMAGE_NAME = "Bee";
	private int seeds = 0;

	public Bee(Point location, GameBoard board) {
		super(board);
		this.setHP(BEE_HP);
		setImage(ImageReg.getInstance().getImageFromStr(BEE_IMAGE_NAME));
		this.setLocation(location);
		this.setStrategy(new SquareStrategy(), new Point(
				this.getLocation().x + 1, this.getLocation().y + 1));
		nector = 0;
		nectorToGet = maxNector;
		buildBeeNamesList();
		name = getBeeName();
		this.getListOfBugStrategies().add(new SquareStrategy());
		this.getListOfBugStrategies().add(new GatherStrategy(nearestPlant));
	}

	//TODO make sure to handle an instance of when nearestPlant is still null if this method is called
	public void getClosestPosie(){
		List<Thing> things;
		int multipleOf100 = 1;
		int maxMultipleOf100 = 20;
		int hundred = 5;
		while(multipleOf100 < maxMultipleOf100){
			things = this.getGameBoard().getThingsBetween(this.getObjective().getLocation().x-hundred*multipleOf100, 
					this.getObjective().getLocation().y-hundred*multipleOf100, this.getObjective().getLocation().x+hundred*multipleOf100
					, this.getObjective().getLocation().y+hundred*multipleOf100);
			for(Thing aThing: things){
				if(aThing instanceof Posie){
					nearestPlant = (Posie) aThing; 
				}
			}
			multipleOf100++;
		}
	}
	
	private void buildBeeNamesList() {
		beeNames = new ArrayList<String>();
		beeNames.add("BeeYourself");
		beeNames.add("Beeatrice");
		beeNames.add("BusyBee");
	}

	private String getBeeName() {
		Collections.shuffle(beeNames);
		return beeNames.get(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	// TODO this will need code to handle when bug makes it to objective
	// TODO need code to handle depending on if objective is null or just chasing point
	@Override
	public void update() {
		this.getStrategy().getNextAction(this, this.getGameBoard());
//		if(this.getObjective() == null){
//			this.move(getALocationToMoveTo());
//		}
//		if (this.getLocation() != this.getObjective()) {
//			this.move(this.getObjective().getLocation());
//		}
	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		thingBeingAttacked.updateHP(-1 * BEE_ATTACK_DAMAGE);
		this.updateHP(-1 * BEE_HP);
	}

	// Query to get amount of nector the bee is holding.
	public int getNectorBeingHeld() {
		return nector;
	}

	// Use this method to ask the bee how much nectar it's holding.
	public void calculateNectorToGet() {
		if (nector < maxNector) {
			nectorToGet = maxNector - nector;
		}
	}
	
	public void askFlowerForNectorOrSeeds(){
		if(nearestPlant == null){
			//TODO change strategy to move back to hive
		}
		else if(nearestPlant.isDead() == true){
			seeds = nearestPlant.takeSeeds();
		}
		else if(nearestPlant.isDead() == false){
			calculateNectorToGet();
			nearestPlant.takeNectar(nectorToGet);
		}
	}
	
	public void unloadNectorAndSeedsToHive(){
		
		unloadNector();
		setSeeds(0);
	}

	public int getSeeds(){
		return seeds;
	}
	
	public void setSeeds(int newSeeds){
		seeds = newSeeds;
	}
	
	public void updateSeeds(int value){
		seeds += value;
	}
	
	// Use this method to have the bee "drop" its nectar. Resets the amount of
	// nector being held.
	public void unloadNector() {
		nector = 0;
	}

}
