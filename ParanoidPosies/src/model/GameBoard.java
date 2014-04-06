package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GUI.GameInterface;
import GUI.PPGUI;

public class GameBoard implements GameInterface {
	public List<Thing> things;
	public Hive hive;
	public static final int centerX = 2500;
	public static final int centerY = 2500;

	public static final int DIFFICULTY_INTERVAL = 60 * PPGUI.UPDATES_PER_SEC;
	public static final int INITIAL_ENEMY_THRESHOLD = 5;
	public static final int INCREASE_ENEMIES_BY = 2;
	public static final int GRACE_PERIOD = 30 * PPGUI.UPDATES_PER_SEC;

	public static final int BEE_SPAWN_X_OFFSET = 50;

	public static final int STARTING_BEES = 5;
	private int timer;
	private List<Bug> enemyList;
	private List<Bug> friendlyList;
	
	public static final Point NORTH_SPAWN = new Point(centerX, centerY - 700);
	public static final Point EAST_SPAWN = new Point(centerX + 700, centerY);
	public static final Point SOUTH_SPAWN = new Point(centerX, centerY + 700);
	public static final Point WEST_SPAWN = new Point(centerX - 700, centerY);
	public static final int SPAWN_PROBABILITY = 1;

	public GameBoard() {
		timer = 0;
		hive = new Hive();
		hive.setLocation(new Point(centerX, centerY));
		things = new ArrayList<Thing>();
		friendlyList = new ArrayList<Bug>();
		enemyList = new ArrayList<Bug>();
		things.add(hive);
		for (int i = 0; i < STARTING_BEES; i++) {
			Bee b = new Bee(new Point(centerX + BEE_SPAWN_X_OFFSET, centerY), this);
			things.add(b);
			friendlyList.add(b);
		}

		// These Things are just here for testing
		//things.add(new Caterpillar(new Point(2200, 2200), this));
		things.add(new Posie(new Point(centerX, centerY + 200)));

		((Bee) things.get(1)).setStrategy(new GatherStrategy(((Bug) things.get(1)), this),
				things.get(6));

	}

	public void addThing(Thing thing) {
		things.add(thing);
	}

	public void askHiveForBees() {
		int bees = hive.getBeesToMake();
		for (int i = 0; i < bees; i++) {
			things.add(new Bee(new Point(centerX + BEE_SPAWN_X_OFFSET, centerY), this));
		}
		hive.updateBeesToMake(bees * -1);
	}

	@Override
	public List<Thing> getAllThingsOnBoard() {
		return things;
	}

	@Override
	public List<Thing> getThingsBetween(int xLow, int yLow, int xHigh, int yHigh) {
		List<Thing> toReturn = new ArrayList<Thing>();
		for (Thing t : things) {
			Point coords = t.getLocation();
			if (coords.x > xLow && coords.x <= xHigh && coords.y > yLow && coords.y <= yHigh) {
				toReturn.add(t);
			}
		}
		return toReturn;
	}

	@Override
	public Hive getHive() {
		return hive;
	}

	@Override
	public void update() {
		// TODO Handle enemy spawning
		timer++;
		askHiveForBees();

		List<Thing> toRemove = new ArrayList<Thing>();
		for (Thing t : things) {
			t.update();
			if (t.shouldBeCleanedUp()) {
				toRemove.add(t);
			}
		}
		for (Thing t : toRemove) {
			things.remove(t);
		}		
		spawnEnemies();
	}
	
	public void spawnEnemies(){
		for(int i = 0; i < getNumberOfEnemiesToSpawn(); i++){
			Point toSpawnAt = null;
			Random rand = new Random();
			int randInt = rand.nextInt(4) + 1; //Get random integer from 1-4
			switch(randInt){
			case 1: toSpawnAt = NORTH_SPAWN; break;
			case 2: toSpawnAt = EAST_SPAWN; break;
			case 3: toSpawnAt = SOUTH_SPAWN; break;
			case 4: toSpawnAt = WEST_SPAWN; break;
			} 
			int spawnProb = rand.nextInt(100);
			if(spawnProb < SPAWN_PROBABILITY){
				Caterpillar c = new Caterpillar(toSpawnAt, this);
				things.add(c);
				enemyList.add(c);
			}
		}
	}
	
	
	
	public int getNumberOfEnemiesToSpawn(){
		if(timer < GRACE_PERIOD){
			return 0;
		}
		else return (INITIAL_ENEMY_THRESHOLD + INCREASE_ENEMIES_BY * (timer/ DIFFICULTY_INTERVAL)) - enemyList.size();
		
	}

}
