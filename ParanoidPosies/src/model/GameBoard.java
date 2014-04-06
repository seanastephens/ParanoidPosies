package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

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

	public static final int POSIE_COST_IN_HONEY = 2;
	public static final int POSIE_COST_IN_SEEDS = 1;

	public static final int BEE_SPAWN_X_OFFSET = 200;
	public static final int BEE_SPAWN_Y_OFFSET = 200;

	public static final int BEE_INTERVAL = 5;

	public static final int STARTING_BEES = 30;
	public static final int STARTING_FLOWERS = 5;
	public static final int FLOWER_OFFSET = 600;

	private int timer;
	private List<Bug> enemyList;
	private List<Bug> friendlyList;

	public static final Point NORTH_SPAWN = new Point(centerX, centerY - 700);
	public static final Point EAST_SPAWN = new Point(centerX + 700, centerY);
	public static final Point SOUTH_SPAWN = new Point(centerX, centerY + 700);
	public static final Point WEST_SPAWN = new Point(centerX - 700, centerY);
	public static final int SPAWN_PROBABILITY = 1;

	public static final int ENEMY_DISTANCE_FROM_HIVE = 600;
	public static final int ENEMY_NORTH_SPAWN_RANGE = 300;
	public static final int ENEMY_EAST_SPAWN_RANGE = 300;

	private int waveSize;
	private static final int INITIAL_WAVE_SIZE = 3;
	public static final int WAVE_INTERVAL = 120 * PPGUI.UPDATES_PER_SEC;
	public static final int SPAWN_INTERVAL = 10;

	private static final int HITLER_TIME = 120 * PPGUI.UPDATES_PER_SEC;

	private static final int HITLER_PROB_RANGE = 50;
	private static final int HITLER_PROB = 2;
	private static final String HITLER_MESSAGE = "ULTRA HYPER GIGA MECHA HITLER has just appeared! Are you a bad enough dude to stop him?";

	private static final int SCORE_PER_KILL = 10;
	private static final int SCORE_PER_PLANT = 2;
	private static final int PERCENT_KUBA_CATERBUGS = 100;

	private int totalScore = 0;

	public SoundManager sound;

	public GameBoard() {
		sound = new SoundManager();
		timer = 0;
		waveSize = INITIAL_WAVE_SIZE;
		hive = new Hive(this);
		hive.setLocation(new Point(centerX, centerY));
		things = new ArrayList<Thing>();
		friendlyList = new ArrayList<Bug>();
		enemyList = new ArrayList<Bug>();
		things.add(hive);
		Random rand = new Random();
		for (int i = 0; i < STARTING_FLOWERS; i++) {
			int randX = centerX + (rand.nextInt(FLOWER_OFFSET) - FLOWER_OFFSET / 2);
			int randY = centerY + (rand.nextInt(FLOWER_OFFSET) - FLOWER_OFFSET / 2);
			Posie temp = new Posie(new Point(randX, randY));
			for (int j = 0; j < 200; j++) {
				temp.update();
			}
			things.add(temp);
		}
		for (int i = 0; i < STARTING_BEES; i++) {

			Bee b = new Bee(getRandomBeeSpawn(), this);
			things.add(b);
			friendlyList.add(b);
		}

	}

	public void spawnWave() {
		Random rando = new Random();
		Point segP = new Point(centerX /*+ rando.nextInt(1000)-500*/, centerY /*+ rando.nextInt(1000)-500*/);
		Caterpillar d = new SegmentedCaterpillarHead(segP, this);
		for (int i = 0; i < waveSize; i++) {
			Point toSpawnAt = null;
			Random rand = new Random();
			int randInt = rand.nextInt(4) + 1; // Get random integer from 1-4
			switch (randInt) {
			case 1:
				toSpawnAt = getNorthSpawn();
				break;
			case 2:
				toSpawnAt = getEastSpawn();
				break;
			case 3:
				toSpawnAt = getSouthSpawn();
				break;
			case 4:
				toSpawnAt = getWestSpawn();
				break;
			}
			Caterpillar c = new Caterpillar(toSpawnAt, this);
			int hitlerRand = rand.nextInt(HITLER_PROB_RANGE);
			System.out.println(hitlerRand);
			if (hitlerRand < HITLER_PROB && timer >= 2 * WAVE_INTERVAL) {
				System.out.println("Making Hitler");

				c.makeHitler();

			}
			if (rand.nextInt(100) <= PERCENT_KUBA_CATERBUGS && !c.IS_LITERALLY_HITLER) {
				c = new SegmentedCaterpillarHead(toSpawnAt, this);

			}
			if (c.IS_LITERALLY_HITLER) {
				SoundManager.playSiren();
				JOptionPane.showMessageDialog(null, HITLER_MESSAGE);
			}
			things.add(c);

		}
		waveSize += INITIAL_WAVE_SIZE;
	}

	public Point getRandomBeeSpawn() {
		Random beeSpawn = new Random();
		int randX = beeSpawn.nextInt(BEE_SPAWN_X_OFFSET) - BEE_SPAWN_X_OFFSET / 2 + centerX;
		int randY = beeSpawn.nextInt(BEE_SPAWN_Y_OFFSET) - BEE_SPAWN_Y_OFFSET / 2 + centerY;
		return new Point(randX, randY);
	}

	public void addThing(Thing thing) {
		things.add(thing);
	}

	public void askHiveForBees() {
		if (timer % BEE_INTERVAL == 0) {
			int bees = hive.getBeesToMake();
			for (int i = 0; i < bees; i++) {
				things.add(new Bee(getRandomBeeSpawn(), this));
			}
			hive.updateBeesToMake(bees * -1);
		}
	}

	public void spawnAWarrior() {
		things.add(new BeeWarrior(new Point(centerX + BEE_SPAWN_X_OFFSET, centerY), this));
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
			if (t instanceof Caterpillar) {
				totalScore += SCORE_PER_KILL;
			}
		}

		if (timer % SPAWN_INTERVAL == 0) {
			spawnEnemies();
		}

	}

	public void spawnEnemies() {
		if (timer % WAVE_INTERVAL == 0) {
			spawnWave();
		}

		for (int i = 0; i < getNumberOfEnemiesToSpawn(); i++) {
			Point toSpawnAt = null;
			Random rand = new Random();
			int randInt = rand.nextInt(4) + 1; // Get random integer from 1-4
			switch (randInt) {
			case 1:
				toSpawnAt = getNorthSpawn();
				break;
			case 2:
				toSpawnAt = getEastSpawn();
				break;
			case 3:
				toSpawnAt = getSouthSpawn();
				break;
			case 4:
				toSpawnAt = getWestSpawn();
				break;
			}
			int spawnProb = rand.nextInt(100);

			if (spawnProb < SPAWN_PROBABILITY) {
				Caterpillar c = new Caterpillar(toSpawnAt, this);
				int hitlerRand = rand.nextInt(HITLER_PROB_RANGE);

				System.out.println(hitlerRand);
				if (hitlerRand < HITLER_PROB && timer >= 2 * WAVE_INTERVAL) {
					System.out.println("Making Hitler");
					c.makeHitler();
				}
				if (c.IS_LITERALLY_HITLER) {
					SoundManager.playSiren();
					JOptionPane.showMessageDialog(null, HITLER_MESSAGE);
				}
				things.add(c);
				enemyList.add(c);
			}
		}
		// Spawn Hitler after third wave
		if (timer == 3 * WAVE_INTERVAL) {
			Caterpillar hitler = new Caterpillar(getNorthSpawn(), this);
			hitler.makeHitler();
			SoundManager.playSiren();
			JOptionPane.showMessageDialog(null, HITLER_MESSAGE);
			things.add(hitler);
			enemyList.add(hitler);
		}
	}

	public Point getWestSpawn() {
		Random rand = new Random();
		int randX = centerX - (rand.nextInt(ENEMY_EAST_SPAWN_RANGE) + ENEMY_DISTANCE_FROM_HIVE);

		int randY = rand.nextInt(ENEMY_NORTH_SPAWN_RANGE) - (ENEMY_NORTH_SPAWN_RANGE / 2);
		randY += centerY;

		return new Point(randX, randY);
	}

	public Point getSouthSpawn() {
		Random rand = new Random();
		int randX = rand.nextInt(ENEMY_EAST_SPAWN_RANGE) - (ENEMY_EAST_SPAWN_RANGE / 2);
		randX += centerX;

		int randY = rand.nextInt(ENEMY_NORTH_SPAWN_RANGE) + ENEMY_DISTANCE_FROM_HIVE + centerY;

		return new Point(randX, randY);
	}

	public Point getEastSpawn() {
		Random rand = new Random();
		int randX = centerX + (rand.nextInt(ENEMY_EAST_SPAWN_RANGE) + ENEMY_DISTANCE_FROM_HIVE);

		int randY = rand.nextInt(ENEMY_NORTH_SPAWN_RANGE) - ENEMY_NORTH_SPAWN_RANGE / 2;
		randY += centerY;

		return new Point(randX, randY);
	}

	public Point getNorthSpawn() {
		Random rand = new Random();
		int randX = rand.nextInt(ENEMY_EAST_SPAWN_RANGE) - ENEMY_EAST_SPAWN_RANGE / 2;
		randX += centerX;

		int randY = centerY - (rand.nextInt(ENEMY_NORTH_SPAWN_RANGE) + ENEMY_DISTANCE_FROM_HIVE);

		return new Point(randX, randY);
	}

	public int getNumberOfEnemiesToSpawn() {
		if (timer < GRACE_PERIOD) {
			return 0;
		} else
			return (INITIAL_ENEMY_THRESHOLD + INCREASE_ENEMIES_BY * (timer / DIFFICULTY_INTERVAL))
					- enemyList.size();

	}

	public int getNumberOfBees() {
		int number = 0;
		for (Thing t : things) {
			if (t instanceof Bee) {
				number++;
			}
		}
		return number;
	}

	public int getNumberOfCats() {
		int number = 0;
		for (Thing t : things) {
			if (t instanceof Caterpillar) {
				number++;
			}
		}
		return number;
	}

	public int getNumberOfFlowers() {
		int number = 0;
		for (Thing t : things) {
			if (t instanceof Plant) {
				number++;
			}
		}
		return number;
	}

	public int getNumberOfTics() {
		return timer;
	}

	public int getWaveSize() {
		return waveSize;
	}

	public void plantWasBuilt() {
		totalScore += SCORE_PER_PLANT;
	}

	public int getScore() {
		return totalScore + timer / PPGUI.UPDATES_PER_SEC;
	}
}
