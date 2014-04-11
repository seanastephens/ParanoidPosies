package model;

import java.awt.Image;
import java.awt.Point;

import GUI.PPGUI;

public class Hive implements Thing {

	private Point location;
	private int HIVE_HP = 50;
	private int hp;
	private Image image;
	private int layer = 3;
	private int nector;
	private int honey;
	private int timer;
	private int halfMaxImageDimension;

	private final int timeToBuildABee = 5 * PPGUI.UPDATES_PER_SEC;
	private final int honeyCostToBuildABee = 10;
	private final int honeyCostToBuildAWarriorBee = 20;
	private boolean beeProduction;
	private int beesToMake;
	private static String HIVE_NAME = "Hive";
	private int seeds;
	private GameBoard game;

	public Hive() {
		this.game = GameBoard.getBoard();
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

	public String getHTMLDescription() {
		return "Its the Hive!<br>It has " + getNector() + " nectar, " + getHoney() + " honey, and "
				+ getSeeds() + " seeds";
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

	public boolean canBuildWarriorBee() {
		return honey >= honeyCostToBuildAWarriorBee;
	}

	public void buildWarriorBee() {
		if (canBuildWarriorBee()) {
			game.spawnAWarrior();
			honey -= honeyCostToBuildAWarriorBee;
		}
	}

	public int getHoneyCostToBuildABeeWarrior() {
		return honeyCostToBuildAWarriorBee;
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
		if (getNector() > 0 && timer % PPGUI.UPDATES_PER_SEC / 4 == 0) {
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
		int imageHalfWidth = image.getWidth(null) / 2;
		int imageHalfHeight = image.getHeight(null) / 2;
		halfMaxImageDimension = Math.max(imageHalfWidth, imageHalfHeight);
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

	@Override
	public void setMaxHP(int newMaxHP) {
		HIVE_HP = newMaxHP;
		hp = HIVE_HP;
	}

	@Override
	public int getMaxHP() {
		return HIVE_HP;
	}

	@Override
	public void updateMaxHP(int valueToAdjustHPBy) {
		HIVE_HP += valueToAdjustHPBy;
		hp = HIVE_HP;
	}

	@Override
	public void setName(String newName) {
		HIVE_NAME = newName;
	}

	@Override
	public void setLayer(int newLayer) {
		layer = newLayer;
	}

	public boolean contains(Point point) {
		int xdiff = Math.abs(getLocation().x - point.x);
		int ydiff = Math.abs(getLocation().y - point.y);
		return Math.max(xdiff, ydiff) < halfMaxImageDimension;
	}

}
