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

	public static final int posie_max_seeds_to_drop = 5;
	public static final int posie_lifespan = 60 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_time_to_seedling = 3 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_time_to_flower = 7 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_hitPoints = 10;
	public static final int posie_max_nectar = 5;

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
	public static final String DEAD_FLOWER_ACTION = "Flower is kill.";

	private List<String> nameList;
	private String name;

	private Image justPlantedImage;
	private Image seedlingImage;
	private Image[] adultImage;
	private Image deadImage;
	private int whichAdult;

	public Posie(Point initialLocation) {
		super(initialLocation);
		maxSeedsToDrop = posie_max_seeds_to_drop;
		lifespan = posie_lifespan;
		setHP(posie_hitPoints);
		nameList = new ArrayList<String>();
		setUpNameList();
		name = getPosieName();
		Random r = new Random();
		whichAdult = r.nextInt(6);

		ImageReg i = ImageReg.getInstance();
		justPlantedImage = i.getImageFromStr("JustPlanted");
		seedlingImage = i.getImageFromStr("Seedling");
		deadImage = i.getImageFromStr("DeadFlower");

		adultImage = new Image[6];
		adultImage[0] = i.getImageFromStr("Flower0");
		adultImage[1] = i.getImageFromStr("Flower1");
		adultImage[2] = i.getImageFromStr("Flower2");
		adultImage[3] = i.getImageFromStr("Flower3");
		adultImage[4] = i.getImageFromStr("Flower4");
		adultImage[5] = i.getImageFromStr("Flower5");

	}

	public Image getImage() {
		if (currentState == GrowthState.JustPlanted) {
			return justPlantedImage;
		} else if (currentState == GrowthState.Seedling) {
			return seedlingImage;
		} else if (currentState == GrowthState.Flower) {
			return adultImage[whichAdult];
		} else {
			return deadImage;
		}
	}

	public String getAction() {
		String temp = "";
		switch (currentState) {
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
		temp += " (" + seedsDropped + " seeds and " + currentNectar + " nectar).";
		return temp;
	}

	// Names go here
	private void setUpNameList() {
		nameList.add("Pansy");
		nameList.add("Pocket full of");
		nameList.add("Bane of caterpillars");
	}

	private String getPosieName() {
		Collections.shuffle(nameList);
		return nameList.get(0);
	}

	public String getName() {
		return name;
	}

	@Override
	public void grow() {

		if (timer >= posie_time_to_seedling && timer < posie_time_to_flower) {
			currentState = GrowthState.Seedling;
			setImage(SEEDLING_IMAGE);
		} else if (timer >= posie_time_to_flower && timer < posie_lifespan) {
			currentState = GrowthState.Flower;
			setImage(FLOWER_IMAGE);
			hasBloomed = true;
		}

		else if (timer >= posie_lifespan) {
			currentState = GrowthState.DeadFlower;
			setImage(DEAD_FLOWER_IMAGE);
			currentNectar = 0;
			hitPoints = 0;
			if (seedsDropped == 0 && !shouldBeCleanedUp) {
				Random rand = new Random();
				seedsDropped = rand.nextInt(posie_max_seeds_to_drop) + 1;
			}
		}
	}

	// If the flower has bloomed, and the nectar is not yet at capacity,
	// increase
	// the nectar at a rate of 1 per second
	@Override
	public void replenishNectar() {
		if (hasBloomed && currentState == GrowthState.Flower && timer % PPGUI.UPDATES_PER_SEC == 0
				&& currentNectar < posie_max_nectar && !isDead()) {
			currentNectar++;
		}
	}

	@Override
	public void update() {
		timer++;
		grow();
		replenishNectar();
	}

	@Override
	public void updateHP(int hp) {
		hitPoints += hp;
		if (hitPoints <= 0) {
			currentState = GrowthState.DeadFlower;
			currentNectar = 0;
			// only reached if plant is killed by enemy
			seedsDropped = 0;
			shouldBeCleanedUp = true;
		}
	}

}
