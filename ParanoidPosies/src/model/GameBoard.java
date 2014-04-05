package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import GUI.GameInterface;
import GUI.MockHive;
import GUI.ParanoidPosieGUI;

public class GameBoard implements GameInterface {
	public List<Thing> things;
	public Hive hive;
	public static final int centerX = 2500;
	public static final int centerY = 2500;
	
	public static final int DIFFICULTY_INTERVAL = ParanoidPosieGUI.UPDATES_PER_SEC * 60;
	public static final int INITIAL_ENEMY_THRESHOLD = 5;
	public static final int INCREASE_ENEMIES_BY = 2;
	public static final int GRACE_PERIOD = ParanoidPosieGUI.UPDATES_PER_SEC * 30;
	
	private int timer;

	public GameBoard(){
		timer = 0;
		hive = new Hive();
		hive.setLocation(new Point(centerX, centerY));
		things = new ArrayList<Thing>();
		things.add(hive);
	}
	
	public void addThing(Thing thing){
		things.add(thing);
	}
	
	public void askHiveForBees(){
		
	}
	
	@Override
	public List<Thing> getAllThingsOnBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Thing> getThingsBetween(int xLow, int yLow, int xHigh, int yHigh) {
		List<Thing> toReturn = new ArrayList<Thing>();
		for(Thing t : things){
			Point coords = t.getLocation();
			if(coords.x > xLow && coords.x <= xHigh && coords.y > yLow
				&& coords.y <= yHigh){
				toReturn.add(t);
			}
		}
		return toReturn;
	}

	@Override
	public MockHive getHive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
