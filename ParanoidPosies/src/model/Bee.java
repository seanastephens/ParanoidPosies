package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bee extends Bug {

	private int nector;
	private List<String> beeNames;
	private String name;
	private int nectarToGet;
	private int speed = 2;
	private static final int maxNectar = 10;
	public static final int BEE_HP = 5;
	public static final int BEE_ATTACK_DAMAGE = 1;
	public static final String BEE_IMAGE_NAME = "Bee";
	private int seeds;

	public Bee(Point location, GameBoard board) {
		super(board);
		this.setHP(BEE_HP);
		setImage(ImageReg.getInstance().getImageFromStr(BEE_IMAGE_NAME));
		this.setLocation(location);
		this.setStrategy(new GatherStrategy(this, board),
				getClosestPosie());
		if(getObjectiveThing() == null){
			this.setStrategy(new SquareStrategy(this, board),
					new Point(this.getLocation().x + 50, this.getLocation().y));
		}
		nector = 0;
		seeds = 0;
		nectarToGet = maxNectar;
		buildBeeNamesList();
		name = getBeeName();
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

	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String getAction() {
		String result = super.getAction();
		result += "\nNectar: " + nector + "\nSeeds: " + seeds;
		return result;
	}

	public void setName(String newName) {
		name = newName;
	}
	
	@Override
	public void update() {
		if (this.getStrategy() != null) {
			Point temp;
			for(int i = 0; i < speed; i++){
				temp = this.getLocation();
				this.getStrategy().doNextAction();
				if(temp.equals(this.getLocation())){
					break;
				}
			}
			
		}
	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		if(thingBeingAttacked != null){
			thingBeingAttacked.updateHP(-1 * BEE_ATTACK_DAMAGE);
			this.updateHP(-1 * BEE_HP);
		}
	}

	// Query to get amount of nector the bee is holding.
	public int getNectarBeingHeld() {
		return nector;
	}

	// Use this method to ask the bee how much nectar it's holding.
	public void calculateNectarToGet() {
		if (nector < maxNectar) {
			nectarToGet = maxNectar - nector;
		} else {
			nectarToGet = 0;
		}
	}

	public void askFlowerForNectarOrSeeds() {
		if (!(getObjectiveThing() instanceof Plant)) {
			throw new IllegalStateException("Can't get nectoar from :"
					+ getObjectiveThing().toString());
		}
		if (getObjectiveThing().isDead() == true) {
			seeds = ((Plant) getObjectiveThing()).takeSeeds();
		} else {

			calculateNectarToGet();
			nector += ((Plant) getObjectiveThing()).takeNectar(nectarToGet);
		}
	}

	public void unloadNectarAndSeedsToHive() {
		this.getGameBoard().getHive().updateNector(nector);
		this.getGameBoard().getHive().updateSeeds(seeds);
		unloadNectar();
		setSeeds(0);
	}

	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int newSeeds) {
		seeds = newSeeds;
	}

	public void updateSeeds(int value) {
		seeds += value;
	}

	// Use this method to have the bee "drop" its nectar. Resets the amount of
	// nector being held.
	public void unloadNectar() {
		nector = 0;
	}

	@Override
	public void upgradeAttack(int newAttack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgradeSpeed(int newSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgradeTotalHP(int hp) {
		// TODO Auto-generated method stub
		
	}

}
