package model;

import java.awt.Image;
import java.awt.Point;

public class Bee extends Bug{
	
	public Bee(Point location, Image image, BugStrategy strategy){
		this.setHP(1);
		this.setImage(image);
		this.setLocation(location);
		this.setStrategy(strategy);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		thingBeingAttacked.updateHP(-1);
		this.updateHP(-1);
	}

}
