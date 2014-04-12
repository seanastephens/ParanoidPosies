package model;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import GUI.PPGUI;

/*
 * Will automatically grow and replenish nectar as it is updated. Use
 * updateHP(int) when attacking the Posie, and use takeNectar(int) when
 * collecting nectar. When the Posie is fully grown and does not have full
 * nectar, it will regenerate nectar at a rate of one per second until it is
 * full. When the Posie dies, it will drop a random number of seeds between 
 * 0 and posie_max_seeds_to_drop, its nectar will go to 0, and it will stop
 * regenerating nectar.
 */

public class Posie extends Plant {

	private static final int posie_max_seeds_to_drop = 5;
	public static final int posie_lifespan = 60 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_time_to_seedling = 3 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_time_to_flower = 7 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_hitPoints = 10;
	private static final int posie_max_nectar = 5;

	public static final Image SEED_IMAGE = ImageReg.getInstance().getImageFromStr("thisIsAPosie");
	public static final Image SEEDLING_IMAGE = ImageReg.getInstance().getImageFromStr(
			"TotallyAPosie");
	public static final Image FLOWER_IMAGE = ImageReg.getInstance().getImageFromStr(
			"forRealThoughItsAPosie");
	public static final Image DEAD_FLOWER_IMAGE = ImageReg.getInstance().getImageFromStr(
			"ISwearToGodThisOneIsAnHonestToGodLegitimatePosie");

	public static final String SEED_ACTION = "Beginning life.";
	public static final String SEEDLING_ACTION = "Growing up big and strong.";
	public static final String FLOWER_ACTION = "Enjoying sunshine, brother.";
	public static final String DEAD_FLOWER_ACTION = "Flower is dead.";

	private int currentNectar = 0;
	private int timer = 0;// Start life at 0, will be incremented by one each
							// time update is called
	private int seedsDropped = 0;
	private boolean hasBloomed = false;

	private List<String> nameList;
	private Image justPlantedImage;
	private Image seedlingImage;
	private Image[] adultImage;
	private Image deadImage;
	private int whichAdult;

	public Posie(Point initialLocation) {
		super(initialLocation);
		setHP(posie_hitPoints);
		nameList = new ArrayList<String>();
		setUpNameList();
		setName(getPosieName());
		Random r = new Random();
		whichAdult = r.nextInt(6);

		ImageReg i = ImageReg.getInstance();
		justPlantedImage = i.getImageFromStr("JustPlanted");
		seedlingImage = i.getImageFromStr("Seedling");
		deadImage = i.getImageFromStr("DeadFlower");
		setImage(seedlingImage); // for Thing.contains

		adultImage = new Image[6];
		adultImage[0] = i.getImageFromStr("Flower0");
		adultImage[1] = i.getImageFromStr("Flower1");
		adultImage[2] = i.getImageFromStr("Flower2");
		adultImage[3] = i.getImageFromStr("Flower3");
		adultImage[4] = i.getImageFromStr("Flower4");
		adultImage[5] = i.getImageFromStr("Flower5");
	}

	public Image getImage() {
		if (getCurrentState() == GrowthState.JustPlanted) {
			return justPlantedImage;
		} else if (getCurrentState() == GrowthState.Seedling) {
			return seedlingImage;
		} else if (getCurrentState() == GrowthState.Flower) {
			return adultImage[whichAdult];
		} else {
			return deadImage;
		}
	}

	@Override
	public String getHTMLDescription() {
		String temp = super.getHTMLDescription() + "<br>";
		switch (getCurrentState()) {
		case JustPlanted:
			temp += SEED_ACTION;
			break;
		case Seedling:
			temp += SEEDLING_ACTION;
			break;
		case Flower:
			temp += FLOWER_ACTION;
			break;
		case DeadFlower:
			temp += DEAD_FLOWER_ACTION;
			break;
		}
		temp += "<br>(" + seedsDropped + " seeds and " + currentNectar + " nectar).";
		return temp;
	}

	// Names go here
	private void setUpNameList() {
		nameList.add("Pansy");
		nameList.add("Pocket full of");
		nameList.add("Bane of caterpillars");
		nameList.add("Rosie");
		nameList.add("Cozy");
		nameList.add("Cage++");
	}

	private String getPosieName() {
		Collections.shuffle(nameList);
		return nameList.get(0);
	}

	@Override
	public void grow() {

		if (timer >= posie_time_to_seedling && timer < posie_time_to_flower) {
			setCurrentState(GrowthState.Seedling);
			setImage(SEEDLING_IMAGE);
		} else if (timer >= posie_time_to_flower && timer < posie_lifespan) {
			setCurrentState(GrowthState.Flower);
			setImage(FLOWER_IMAGE);
			hasBloomed = true;
		}

		else if (timer >= posie_lifespan) {
			setCurrentState(GrowthState.DeadFlower);
			setImage(DEAD_FLOWER_IMAGE);
			currentNectar = 0;
			setHP(0);
			if (seedsDropped == 0 && !shouldBeCleanedUp()) {
				Random rand = new Random();
				seedsDropped = rand.nextInt(posie_max_seeds_to_drop) + 1;
			}
		}
	}

	// return how many seeds the plant has dropped
	public int getSeeds() {
		return seedsDropped;
	}

	public int takeSeeds() {
		int temp = seedsDropped;
		seedsDropped = 0;
		setHP(-1);
		return temp;
	}

	public void setSeedsForTesting(int seeds) {
		seedsDropped = seeds;
	}

	// If the flower has bloomed, and the nectar is not yet at capacity,
	// increase
	// the nectar at a rate of 1 per second
	@Override
	public void replenishNectar() {
		if (hasBloomed && getCurrentState() == GrowthState.Flower
				&& timer % PPGUI.UPDATES_PER_SEC == 0 && currentNectar < posie_max_nectar
				&& !isDead()) {
			currentNectar++;
		}
	}

	public void setNectarForTesting(int newNectar) {
		currentNectar = newNectar;
	}

	// return how much nectar this plant has
	public int getNectar() {
		return currentNectar;
	}

	public int takeNectar(int reduceBy) {
		if (currentNectar >= reduceBy) {
			currentNectar -= reduceBy;
			return reduceBy;
		} else {
			int temp = currentNectar;
			currentNectar = 0;
			return temp;
		}
	}

	@Override
	public boolean isDead() {
		boolean deathStatus = super.isDead();
		if (deathStatus == true) {
			currentNectar = 0;
		}
		return deathStatus;
	}

	@Override
	public void update() {
		timer++;
		grow();
		replenishNectar();
	}

	@Override
	public void updateHP(int hp) {
		super.updateHP(hp);
		if (getHP() <= 0) {
			setCurrentState(GrowthState.DeadFlower);
			currentNectar = 0;
			// only reached if plant is killed by enemy
			seedsDropped = 0;
		}
	}
}
