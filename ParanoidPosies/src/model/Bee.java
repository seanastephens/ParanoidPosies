package model;

import java.awt.Image;
import java.awt.Point;

public class Bee extends Bug{
	
	private int nector;
	
	public Bee(Point location, Image image, BugStrategy strategy){
		this.setHP(5);
		this.setImage(image);
		this.setLocation(location);
		this.setStrategy(strategy);
		nector = 0;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		thingBeingAttacked.updateHP(-1);
		this.updateHP(-5);
	}
	
	//Query to get amount of nector the bee is holding.
	public int getNectorBeingHeld(){
		return nector;
	}
	
	//Use this method to have the bee add nector to the amount it is holding.
	public void addNectorToBee(int value){
		nector += value;
	}
	
	//Use this method to have the bee "drop" its nector.  Resets the amount of nector being held.
	public void unloadNector(){
		nector = 0;
	}

}
