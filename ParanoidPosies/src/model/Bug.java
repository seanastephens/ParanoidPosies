package model;

import java.awt.Image;
import java.awt.Point;

public abstract class Bug implements Thing{
	private Point location;
	private int hp;
	private Image image;
	private final int layer = 1;
	private Strategy strategy;

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
	public int getHP() {
		return hp;
	}

	@Override
	public abstract void die();

	@Override
	public abstract void spawn(Point loc);
	
	public void setStrategy(Strategy strat){
		strategy = strat;
	}
	
	public void getStrategy(Strategy strat){
		return strategy;
	}
	
	//Since things don't teleport, this is where the animations take place to move the Thing
	//from one place to another.
	public abstract void move(Point endLocation);
	
	//Takes the object of the thing being attacked so updates can be made to both objects.
	public abstract void attack(Thing thingBeingAttacked);
}
