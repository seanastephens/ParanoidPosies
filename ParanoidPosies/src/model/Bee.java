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
	private static final int maxNector = 10;
	public static final int BEE_HP = 5;
	public static final int BEE_ATTACK_DAMAGE = 1;
	public static final String BEE_IMAGE_NAME = "Bee";

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
		this.getListOfBugStrategies().add(new GatherStrategy(gameboard.g));
	}

	public Plant getClosestPosie(){
		Posie posie;
		List<Thing> things;
		int multipleOf5 = 1;
		int five = 5;
		while(posie == null){
			things = this.getGameBoard().getThingsBetween(this.getLocation().x-five*multipleOf5, 
					this.getLocation().y-five*multipleOf5, this.getLocation().x+five*multipleOf5
					, this.getLocation().y+five*multipleOf5);
			for(Thing aThing: things){
				if(aThing instanceOf Posie){
					posie = aThing;
					return posie;
				}
			}
			multipleOf5++;
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
	@Override
	public void update() {
		this.getStrategy().getNextAction(this, this.getGameBoard());
		if (this.getLocation() != this.getObjective()) {
			this.move(this.getObjective());
		}
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
	public void calculateNectorToGet(int value) {
		if (nector < maxNector) {
			nectorToGet = maxNector - nector;
		}
	}
	
	public void askFlowerForNector(){
		this.getStrategy().
	}

	// Use this method to have the bee "drop" its nectar. Resets the amount of
	// nector being held.
	public void unloadNector() {
		nector = 0;
	}

}
