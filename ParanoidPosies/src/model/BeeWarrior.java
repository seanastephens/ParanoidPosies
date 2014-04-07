package model;

import java.awt.Image;
import java.awt.Point;
import java.util.Random;

public class BeeWarrior extends Bee {

	private int imageNumber = 0;
	private int state = 0;
	private Image[][][] images = new Image[2][8][3];
	
	

	public BeeWarrior(Point location, GameBoard board) {
		super(location, board);
		setHP(10);
		setAttack(2);
		setSpeed(4);
		int randomConstant = 100;

		Random random = new Random();
		Point ranPoint = new Point(board.getHive().getLocation().x + random.nextInt(randomConstant)
				+ 100, board.getHive().getLocation().x + random.nextInt(randomConstant) + 100);

		this.setObjectiveToNull();
		this.setStrategy(new GuardStrategy(this, board), ranPoint);

		ImageReg i = ImageReg.getInstance();
		images[0][0][0] = i.getImageFromStr("BeeWarrior00");
		images[0][0][1] = i.getImageFromStr("BeeWarrior01");
		images[0][0][2] = i.getImageFromStr("BeeWarrior02");
		images[0][1][0] = i.getImageFromStr("BeeWarrior10");
		images[0][1][1] = i.getImageFromStr("BeeWarrior11");
		images[0][1][2] = i.getImageFromStr("BeeWarrior12");
		images[0][2][0] = i.getImageFromStr("BeeWarrior20");
		images[0][2][1] = i.getImageFromStr("BeeWarrior21");
		images[0][2][2] = i.getImageFromStr("BeeWarrior22");
		images[0][3][0] = i.getImageFromStr("BeeWarrior30");
		images[0][3][1] = i.getImageFromStr("BeeWarrior31");
		images[0][3][2] = i.getImageFromStr("BeeWarrior32");
		images[0][4][0] = i.getImageFromStr("BeeWarrior40");
		images[0][4][1] = i.getImageFromStr("BeeWarrior41");
		images[0][4][2] = i.getImageFromStr("BeeWarrior42");
		images[0][5][0] = i.getImageFromStr("BeeWarrior50");
		images[0][5][1] = i.getImageFromStr("BeeWarrior51");
		images[0][5][2] = i.getImageFromStr("BeeWarrior52");
		images[0][6][0] = i.getImageFromStr("BeeWarrior60");
		images[0][6][1] = i.getImageFromStr("BeeWarrior61");
		images[0][6][2] = i.getImageFromStr("BeeWarrior62");
		images[0][7][0] = i.getImageFromStr("BeeWarrior70");
		images[0][7][1] = i.getImageFromStr("BeeWarrior71");
		images[0][7][2] = i.getImageFromStr("BeeWarrior72");

		images[1][0][0] = i.getImageFromStr("BeeWarriorS00");
		images[1][0][1] = i.getImageFromStr("BeeWarriorS01");
		images[1][0][2] = i.getImageFromStr("BeeWarriorS02");
		images[1][1][0] = i.getImageFromStr("BeeWarriorS10");
		images[1][1][1] = i.getImageFromStr("BeeWarriorS11");
		images[1][1][2] = i.getImageFromStr("BeeWarriorS12");
		images[1][2][0] = i.getImageFromStr("BeeWarriorS20");
		images[1][2][1] = i.getImageFromStr("BeeWarriorS21");
		images[1][2][2] = i.getImageFromStr("BeeWarriorS22");
		images[1][3][0] = i.getImageFromStr("BeeWarriorS30");
		images[1][3][1] = i.getImageFromStr("BeeWarriorS31");
		images[1][3][2] = i.getImageFromStr("BeeWarriorS32");
		images[1][4][0] = i.getImageFromStr("BeeWarriorS40");
		images[1][4][1] = i.getImageFromStr("BeeWarriorS41");
		images[1][4][2] = i.getImageFromStr("BeeWarriorS42");
		images[1][5][0] = i.getImageFromStr("BeeWarriorS50");
		images[1][5][1] = i.getImageFromStr("BeeWarriorS51");
		images[1][5][2] = i.getImageFromStr("BeeWarriorS52");
		images[1][6][0] = i.getImageFromStr("BeeWarriorS60");
		images[1][6][1] = i.getImageFromStr("BeeWarriorS61");
		images[1][6][2] = i.getImageFromStr("BeeWarriorS62");
		images[1][7][0] = i.getImageFromStr("BeeWarriorS70");
		images[1][7][1] = i.getImageFromStr("BeeWarriorS71");
		images[1][7][2] = i.getImageFromStr("BeeWarriorS72");

	}

	@Override
	public Image getImage() {
		return images[selected ? 1 : 0][imageNumber][state];
	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		if (thingBeingAttacked != null) {
			thingBeingAttacked.updateHP(-1 * getAttack());
			this.updateHP(-5);
		}
	}

	@Override
	public String getCriticalInfo() {
		String result = super.getCriticalInfo();
		result += "\nAttack: " + getAttack() * 2;
		return result;
	}

	@Override
	public void update() {
		Point prev = getLocation();
		if (this.getStrategy() instanceof GatherStrategy) {
			this.setStrategy(new MoveStrategy(this, getGameBoard()), getGameBoard()
					.getHive());
		}
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
}
