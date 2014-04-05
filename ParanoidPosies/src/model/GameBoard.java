package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import GUI.GameInterface;
import GUI.PPGUI;

public class GameBoard implements GameInterface {
	public List<Thing> things;
	public Hive hive;
	public static final int centerX = 2500;
	public static final int centerY = 2500;

	public static final int DIFFICULTY_INTERVAL = PPGUI.UPDATES_PER_SEC * 60;
	public static final int INITIAL_ENEMY_THRESHOLD = 5;
	public static final int INCREASE_ENEMIES_BY = 2;
	public static final int GRACE_PERIOD = PPGUI.UPDATES_PER_SEC * 30;

	public static final int BEE_SPAWN_X_OFFSET = 50;

	public static final int STARTING_BEES = 5;
	private int timer;

	public GameBoard() {
		timer = 0;
		hive = new Hive();
		hive.setLocation(new Point(centerX, centerY));
		things = new ArrayList<Thing>();
		things.add(hive);
		for (int i = 0; i < STARTING_BEES; i++) {
			things.add(new Bee(new Point(centerX + BEE_SPAWN_X_OFFSET, centerY), this));
		}

		// These Things are just here for testing
		things.add(new Caterpillar(new Point(2200, 2200), this));
		things.add(new Posie(new Point(centerX, centerY + 200)));

		((Bee) things.get(1)).setStrategy(new GatherStrategy(((Bug) things.get(1)), this),
				things.get(7));

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

		// The following is only for testing, should be removed eventually

	}

}
