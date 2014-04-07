package model;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bee extends Bug implements UpgradeAttack, UpgradeSpeed, UpgradeTotalHP{

	private int nector;
	private List<String> beeNames;
	private String name;
	private int nectarToGet;
	private int speed = 2;
	private static final int maxNectar = 10;
	public static int BEE_HP = 5;
	public static int BEE_ATTACK_DAMAGE = 1;
	public static final String BEE_IMAGE_NAME = "Bee";
	private int seeds;
	public boolean selected = false;

	private Image[][][] images = new Image[2][8][3];
	private int imageNumber = 0;
	private int state = 0;

	public Bee(Point location, GameBoard board) {
		super(board);
		this.setHP(BEE_HP);
		// setImage(ImageReg.getInstance().getImageFromStr(BEE_IMAGE_NAME));
		this.setLocation(location);
		this.setStrategy(new GatherStrategy(this, board), getClosestPosie());
		if (getObjectiveThing() == null) {
			this.setStrategy(new SquareStrategy(this, board), new Point(this.getLocation().x + 50,
					this.getLocation().y));
		}
		nector = 0;
		seeds = 0;
		nectarToGet = maxNectar;
		name = getNewBeeName();

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

	private void buildBeeNamesList() {
		beeNames = new ArrayList<String>();
		beeNames.add("BeeYourself");
		beeNames.add("Beeatrice");
		beeNames.add("BusyBee");
		beeNames.add("Beelieve");
		beeNames.add("Beethoven");
		beeNames.add("Babee");
		beeNames.add("A_C");
		beeNames.add("Beeast");
		beeNames.add("To Bee or Not to");
	}

	public String getNewBeeName() {
		buildBeeNamesList();
		Collections.shuffle(beeNames);
		return beeNames.get(0);
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getAction() {
		String result = super.getAction();
		result += "Nectar=" + nector + "<br>Seeds=" + seeds;
		return result;
	}

	@Override
	public void update() {
		Point prev = getLocation();
		if (this.getStrategy() != null) {
			Point temp;
			for (int i = 0; i < speed; i++) {
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
			thingBeingAttacked.updateHP(-1 * BEE_ATTACK_DAMAGE);
			this.updateHP(-1 * BEE_HP);
		}
	}

	// Query to get amount of nector the bee is holding.
	public int getNectarBeingHeld() {
		return nector;
	}

	// Use this method to ask the bee how much nectar it's holding.
	public void calculateNectarToGet() {
		if (nector < maxNectar) {
			nectarToGet = maxNectar - nector;
		} else {
			nectarToGet = 0;
		}
	}

	public void askFlowerForNectarOrSeeds() {
		if (!(getObjectiveThing() instanceof Plant)) {
			throw new IllegalStateException("Can't get nectoar from :"
					+ getObjectiveThing().toString());
		}
		if (getObjectiveThing().isDead() == true) {
			seeds = ((Plant) getObjectiveThing()).takeSeeds();
		} else {

			calculateNectarToGet();
			nector += ((Plant) getObjectiveThing()).takeNectar(nectarToGet);
		}
	}

	public void unloadNectarAndSeedsToHive() {
		this.getGameBoard().getHive().updateNector(nector);
		this.getGameBoard().getHive().updateSeeds(seeds);
		unloadNectar();
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

	// Use this method to have the bee "drop" its nectar. Resets the amount of
	// nector being held.
	public void unloadNectar() {
		nector = 0;
	}

	@Override
	public void upgradeAttack(int newAttack) {
		BEE_ATTACK_DAMAGE = newAttack;

	}

	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	public int getSpeed() {
		return speed;
	}
	
	@Override
	public void upgradeSpeed(int newSpeed) {
		speed = newSpeed;

	}

	@Override
	public void upgradeTotalHP(int hp) {
		BEE_HP = hp;

	}

}
