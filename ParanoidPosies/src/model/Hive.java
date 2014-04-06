package model;

import java.awt.Image;
import java.awt.Point;

import GUI.PPGUI;

public class Hive implements Thing {

	private Point location;
	private final int HIVE_HP = 50;
	private int hp;
	private Image image;
	private final int layer = 3;
	private int nector;
	private int honey;
	private int timer;
	private final int timeToBuildABee = 5 * PPGUI.UPDATES_PER_SEC;
	private final int honeyCostToBuildABee = 7;
	private boolean beeProduction;
	private int beesToMake;
	public static final String HIVE_NAME = "Hive";
	private int seeds;

	public Hive() {
		nector = 0;
		honey = 0;
		timer = 0;
		beeProduction = true;
		beesToMake = 0;
		this.setHP(HIVE_HP);
		setImage(ImageReg.getInstance().getImageFromStr("Hive"));
	}

	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int newSeedCount) {
		seeds = newSeedCount;
	}

	public void updateSeeds(int value) {
		seeds += value;
	}

	public boolean shouldBeCleanedUp() {
		return isDead();
	}

	public String getAction() {
		return "Has " + getNector() + " nectar and " + getSeeds() + " seeds";
	}

	public String getType() {
		return this.getClass().getSimpleName();
	}

	public String getName() {
		return HIVE_NAME;
	}

	@Override
	public void update() {
		convertNectorToHoney();
		if (beeProduction == true) {
			updateTimer(1);
			if (timer == timeToBuildABee) {
				setTimer(0);
				if (honey >= honeyCostToBuildABee) {
					honey -= honeyCostToBuildABee;
					beesToMake++;
				}
			}
		}
	}

	public int getBeesToMake() {
		return beesToMake;
	}

	public void setBeesToMake(int value) {
		beesToMake = value;
	}

	public void updateBeesToMake(int value) {
		beesToMake += value;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int newTime) {
		timer = newTime;
	}

	public void updateTimer(int value) {
		timer += value;
	}

	public boolean getStateOfBeeProduction() {
		return beeProduction;
	}

	public void setStateOfBeeProduction(boolean value) {
		beeProduction = value;
	}

	public int getNector() {
		return nector;
	}

	public void setNector(int nector) {
		this.nector = nector;
	}

	public int getHoney() {
		return honey;
	}

	public void setHoney(int honey) {
		this.honey = honey;
	}

	public void updateNector(int value) {
		nector += value;
	}

	public void updateHoney(int value) {
		honey += value;
	}

	public void convertNectorToHoney() {
		if (getNector() > 0 && timer % PPGUI.UPDATES_PER_SEC/2 == 0) {
			updateNector(-1);
			updateHoney(1);
		}
	}

	@Override
	public void setLocation(Point loc) {
		location = loc;
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void setHP(int hp) {
		this.hp = hp;
	}

	@Override
	public void updateHP(int hp) {
		this.hp += hp;
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public boolean isDead() {
		if (hp <= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getLayer() {
		return layer;
	}

}
