package model;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

public class Bee extends Bug {

	private int nector;
	private List<String> beeNames;
	private String name;
	private final int maxNector = 10;
	private int extraNector;

	public Bee(Point location) {
		this.setHP(5);
		try {
			this.setImage(ImageIO.read(new File("images/bee.png")));
		} catch (IOException e) {
			System.out.println("image error");
		}
		this.setLocation(location);
		this.setStrategy(new SquareStrategy(), new Point(
				this.getLocation().x + 1, this.getLocation().y + 1));
		nector = 0;
		buildBeeNamesList();
		name = getBeeName();
		extraNector = 0;
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
		this.getStrategy().getNextAction(this);
		if (this.getLocation() != this.getObjective()) {
			this.move(this.getObjective());
		}
	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		thingBeingAttacked.updateHP(-1);
		this.updateHP(-5);
	}

	// Query to get amount of nector the bee is holding.
	public int getNectorBeingHeld() {
		return nector;
	}

	// Use this method to have the bee add nector to the amount it is holding.
	public void addNectorToBee(int value) {
		if(nector + value >= maxNector){
			extraNector = nector + value - maxNector;
			nector = maxNector;
		}
		else{
			nector += value;
		}
	}
	
	public boolean canAddNectorToBee(){
		if(nector >= maxNector){
			return false;
		}
		else{
			return true;
		}
	}
	
	public int getExtraNector(){
		return extraNector;
	}

	public void setExtraNector(int value){
		extraNector = value;
	}
	// Use this method to have the bee "drop" its nector. Resets the amount of
	// nector being held.
	public void unloadNector() {
		nector = 0;
	}

}
