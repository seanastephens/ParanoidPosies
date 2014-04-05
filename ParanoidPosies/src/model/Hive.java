package model;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hive implements Thing {

	private Point location;
	private int hp;
	private Image image;
	private final int layer = 3;
	private int nector;
	private int honey;
	private int timer;
	private final int timeToBuildABee = 10;
	private final int honeyCostToBuildABee = 10;
	private boolean beeProduction;
	private int beesToMake;

	public Hive() {
		nector = 0;
		honey = 0;
		timer = 0;
		beeProduction = true;
		beesToMake = 0;
		try {
			image = ImageIO.read(new File("images/thisIsAPosie.jpg"));
		} catch (IOException e) {
			System.out.println("image problems");
		}
	}

	@Override
	public void update() {
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
		updateNector(-1);
		updateHoney(1);
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
