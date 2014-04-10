package model;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * TODO add more unit tests
 * TODO add minimum and maximum bee speeds?
 * TODO add maximum attack rating that can be upgraded (maybe do attack ranges where attack value chosen randomly each time)
 * TODO change SquareStrategy to be about size of hive, set to a random location within hive to give effect of bees flying around hive
 * TODO by setting larger shape strategies where the shapes may have variable sizes we can build dances for the bees
 */

public class Bee extends Bug implements UpgradeAttack, UpgradeSpeed, UpgradeTotalHP {

	private int nectar, nectarToGet, maxNectar, beeAttackDamage, seeds;

	/*
	 * Image GUI related variables
	 */
	private boolean selected = false;
	private Image[][][] images = new Image[2][8][3];
	private int imageNumber = 0;
	private int state = 0;

	public Bee(Point location, GameBoard board) {
		super(board);

		/*
		 * Set initial values for inherited values from Bug
		 */
		setHP(5);
		setSpeed(2);
		setLocation(location);
		giveNewRandomName();
		setStrategy(new GatherStrategy(this, board), board.getClosest(Posie.class, getLocation()));

		/*
		 * Set initial values specific to Bee
		 */

		nectar = 0;
		seeds = 0;
		maxNectar = 10;
		beeAttackDamage = 1;

		nectarToGet = maxNectar;

		ImageReg i = ImageReg.getInstance();
		images[0][0][0] = i.getImageFromStr("Bee00");
		images[0][0][1] = i.getImageFromStr("Bee01");
		images[0][0][2] = i.getImageFromStr("Bee02");
		images[0][1][0] = i.getImageFromStr("Bee10");
		images[0][1][1] = i.getImageFromStr("Bee11");
		images[0][1][2] = i.getImageFromStr("Bee12");
		images[0][2][0] = i.getImageFromStr("Bee20");
		images[0][2][1] = i.getImageFromStr("Bee21");
		images[0][2][2] = i.getImageFromStr("Bee22");
		images[0][3][0] = i.getImageFromStr("Bee30");
		images[0][3][1] = i.getImageFromStr("Bee31");
		images[0][3][2] = i.getImageFromStr("Bee32");
		images[0][4][0] = i.getImageFromStr("Bee40");
		images[0][4][1] = i.getImageFromStr("Bee41");
		images[0][4][2] = i.getImageFromStr("Bee42");
		images[0][5][0] = i.getImageFromStr("Bee50");
		images[0][5][1] = i.getImageFromStr("Bee51");
		images[0][5][2] = i.getImageFromStr("Bee52");
		images[0][6][0] = i.getImageFromStr("Bee60");
		images[0][6][1] = i.getImageFromStr("Bee61");
		images[0][6][2] = i.getImageFromStr("Bee62");
		images[0][7][0] = i.getImageFromStr("Bee70");
		images[0][7][1] = i.getImageFromStr("Bee71");
		images[0][7][2] = i.getImageFromStr("Bee72");

		images[1][0][0] = i.getImageFromStr("BeeS00");
		images[1][0][1] = i.getImageFromStr("BeeS01");
		images[1][0][2] = i.getImageFromStr("BeeS02");
		images[1][1][0] = i.getImageFromStr("BeeS10");
		images[1][1][1] = i.getImageFromStr("BeeS11");
		images[1][1][2] = i.getImageFromStr("BeeS12");
		images[1][2][0] = i.getImageFromStr("BeeS20");
		images[1][2][1] = i.getImageFromStr("BeeS21");
		images[1][2][2] = i.getImageFromStr("BeeS22");
		images[1][3][0] = i.getImageFromStr("BeeS30");
		images[1][3][1] = i.getImageFromStr("BeeS31");
		images[1][3][2] = i.getImageFromStr("BeeS32");
		images[1][4][0] = i.getImageFromStr("BeeS40");
		images[1][4][1] = i.getImageFromStr("BeeS41");
		images[1][4][2] = i.getImageFromStr("BeeS42");
		images[1][5][0] = i.getImageFromStr("BeeS50");
		images[1][5][1] = i.getImageFromStr("BeeS51");
		images[1][5][2] = i.getImageFromStr("BeeS52");
		images[1][6][0] = i.getImageFromStr("BeeS60");
		images[1][6][1] = i.getImageFromStr("BeeS61");
		images[1][6][2] = i.getImageFromStr("BeeS62");
		images[1][7][0] = i.getImageFromStr("BeeS70");
		images[1][7][1] = i.getImageFromStr("BeeS71");
		images[1][7][2] = i.getImageFromStr("BeeS72");

	}

	@Override
	public Image getImage() {
		return images[selected ? 1 : 0][imageNumber][state];
	}

	public void setSelected(boolean change) {
		selected = change;
	}

	public boolean getSelected() {
		return selected;
	}

	public void giveNewRandomName() {
		List<String> beeNames = new ArrayList<String>();
		beeNames.add("BeeYourself");
		beeNames.add("Beeatrice");
		beeNames.add("BusyBee");
		beeNames.add("Beelieve");
		beeNames.add("Beethoven");
		beeNames.add("Babee");
		beeNames.add("A_C");
		beeNames.add("Beeast");
		beeNames.add("To Bee or Not to");
		Collections.shuffle(beeNames);
		setName(beeNames.get(0));
	}

	@Override
	public String getHTMLDescription() {
		return super.getHTMLDescription() + "<br>" + "Carrying " + getNectarBeingHeld()
				+ " nectar and " + getSeeds() + " seeds.<br>Has " + getHP() + " HP.";
	}

	public int getImageNumberForTesting() {
		return imageNumber;
	}

	@Override
	public void update() {
		Point prev = getLocation();
		if (this.getStrategy() != null) {
			Point temp;
			for (int i = 0; i < getSpeed(); i++) {
				temp = this.getLocation();
				this.getStrategy().doNextAction();
				if (temp.equals(this.getLocation())) {
					break;
				}
			}

		}
		Point next = getLocation();
		int diffx = next.x - prev.x;
		int diffy = next.y - prev.y;
		// System.out.println(next.toString() + " - " + prev.toString());
		if (diffx > 0 && diffy == 0) {
			imageNumber = 2;
		} else if (diffx > 0 && diffy > 0) {
			imageNumber = 3;
		} else if (diffx > 0 && diffy < 0) {
			imageNumber = 1;
		} else if (diffx == 0 && diffy > 0) {
			imageNumber = 4;
		} else if (diffx == 0 && diffy < 0) {
			imageNumber = 0;
		} else if (diffx < 0 && diffy == 0) {
			imageNumber = 6;
		} else if (diffx < 0 && diffy > 0) {
			imageNumber = 5;
		} else if (diffx < 0 && diffy < 0) {
			imageNumber = 7;
		}

		state = (state + 1) % 3;
	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		if (thingBeingAttacked != null) {
			thingBeingAttacked.updateHP(-beeAttackDamage);
			this.updateHP(-1 * getHP());
		}
	}

	// Query to get amount of nector the bee is holding.
	public int getNectarBeingHeld() {
		return nectar;
	}

	public void setNectarBeingHeldForTesting(int nectarValue) {
		nectar = nectarValue;
	}

	// Use this method to ask the bee how much nectar it's holding.
	public void calculateNectarToGet() {
		if (nectar < maxNectar) {
			nectarToGet = maxNectar - nectar;
		} else {
			nectarToGet = 0;
		}
	}

	public int getNectarToGetForTesting() {
		return nectarToGet;
	}

	public void askFlowerForNectarOrSeeds() {
		if (!(getObjectiveThing() instanceof Plant)) {
			throw new IllegalStateException("Can't get nectar from :"
					+ getObjectiveThing().toString());
		}
		if (getObjectiveThing().isDead() == true) {
			seeds = ((Posie) getObjectiveThing()).takeSeeds();
		} else {

			calculateNectarToGet();
			nectar += ((Posie) getObjectiveThing()).takeNectar(nectarToGet);
		}
	}

	public void unloadNectarAndSeedsToHive() {
		getGameBoard().getHive().updateNector(nectar);
		getGameBoard().getHive().updateSeeds(seeds);
		nectar = 0;
		setSeeds(0);
	}

	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int newSeeds) {
		seeds = newSeeds;
	}

	public void updateSeeds(int value) {
		seeds += value;
	}

	@Override
	public void upgradeAttack(int newAttack) {
		beeAttackDamage += newAttack;
	}

	@Override
	public void upgradeTotalHP(int hp) {
		updateHP(hp);
	}

	public int getAttack() {
		return beeAttackDamage;
	}

	public void setAttack(int attack) {
		beeAttackDamage = attack;
	}

	@Override
	public void upgradeSpeed(int newSpeed) {
		setSpeed(getSpeed() + newSpeed);

	}
}
