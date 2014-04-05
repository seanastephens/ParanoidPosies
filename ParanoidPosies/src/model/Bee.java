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
	private int seeds;

	public Bee(Point location, GameBoard board) {
		super(board);
		this.setHP(BEE_HP);
		setImage(ImageReg.getInstance().getImageFromStr(BEE_IMAGE_NAME));
		this.setLocation(location);
		this.setStrategy(new SquareStrategy(this, board),
				new Point(this.getLocation().x + 50, this.getLocation().y));
		nector = 0;
		seeds = 0;
		nectorToGet = maxNector;
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

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	// TODO this will need code to handle when bug makes it to objective
	@Override
	public void update() {
		if (this.getStrategy() != null) {
			this.getStrategy().doNextAction();
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
	public void calculateNectorToGet() {
		if (nector < maxNector) {
			nectorToGet = maxNector - nector;
		} else {
			nectorToGet = 0;
		}
	}

	public void askFlowerForNectorOrSeeds() {
		if (!(getObjectiveThing() instanceof Plant)) {
			throw new IllegalStateException("Can't get nectoar from :"
					+ getObjectiveThing().toString());
		}
		if (getObjectiveThing().isDead() == true) {
			seeds = ((Plant) getObjectiveThing()).takeSeeds();
		} else {
			calculateNectorToGet();
			((Plant) getObjectiveThing()).takeNectar(nectorToGet);
		}
	}

	public void unloadNectorAndSeedsToHive() {
		this.getGameBoard().getHive().updateNector(nector);
		this.getGameBoard().getHive().updateSeeds(seeds);
		unloadNector();
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
	public void unloadNector() {
		nector = 0;
	}

}
