package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import GUI.PPGUI;

public class Caterpillar extends Bug implements UpgradeAttack, UpgradeSpeed, UpgradeTotalHP {
	public static int CATERPILLAR_HP = 3;
	public static int CATERPILLAR_ATTACK_DAMAGE = 1;
	public int timer;
	private List<String> CaterpillarNameList;
	public static final String CATERPILLAR_IMAGE = "Caterpillar";
	public static final int ULTRA_HYPER_GIGA_MECHA_HITLER_HP = 1000000;
	public static final int ULTRA_HYPER_GIGA_MECHA_HITLER_ATTACK_DAMAGE = 1000000;
	public static final String ULTRA_HYPER_GIGA_MECHA_HITLER_NAME = "ULTRA HYPER GIGA MECHA HITLER";
	public boolean IS_LITERALLY_HITLER;
	public static final String ULTRA_HYPER_GIGA_MECHA_HITLER_IMAGE_NAME = "HITLER";
	public int speed = 1;

	public Caterpillar(Point location, GameBoard gameboard) {
		super(gameboard);
		timer = 0;
		this.setLocation(location);
		this.setHP(CATERPILLAR_HP);
		this.setImage(ImageReg.getInstance().getImageFromStr(CATERPILLAR_IMAGE));
		CaterpillarNameList = new ArrayList<String>();
		this.setUpNameList();
		giveNewRandomName();

		this.setStrategy(new FightStrategy(this, gameboard),
				gameboard.getClosest(Posie.class, getLocation()));
	}

	public void makeHitler() {
		setName(ULTRA_HYPER_GIGA_MECHA_HITLER_NAME);
		this.setHP(ULTRA_HYPER_GIGA_MECHA_HITLER_HP);
		IS_LITERALLY_HITLER = true;
		this.setImage(ImageReg.getInstance().getImageFromStr(
				ULTRA_HYPER_GIGA_MECHA_HITLER_IMAGE_NAME));
	}

	// Caterpillar names go here
	private void setUpNameList() {
		CaterpillarNameList.add("Wormy");
		CaterpillarNameList.add("Hungry");
		CaterpillarNameList.add("Bulldozer");
		CaterpillarNameList.add("404 Name Not Found");
	}

	public void giveNewRandomName() {
		String name = "";
		Collections.shuffle(CaterpillarNameList);
		name = CaterpillarNameList.get(0);

		Random rand = new Random();
		int nameInt = rand.nextInt(1000000);
		if (nameInt == 666) {
			name = ULTRA_HYPER_GIGA_MECHA_HITLER_NAME;
			this.setHP(ULTRA_HYPER_GIGA_MECHA_HITLER_HP);
			IS_LITERALLY_HITLER = true;
			this.setImage(ImageReg.getInstance().getImageFromStr(
					ULTRA_HYPER_GIGA_MECHA_HITLER_IMAGE_NAME));
		} else
			IS_LITERALLY_HITLER = false;
		setName(name);
	}

	@Override
	public void update() {
		timer++;
		this.getStrategy().doNextAction();

	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		if (timer % PPGUI.UPDATES_PER_SEC == 0) {
			if (IS_LITERALLY_HITLER) {
				thingBeingAttacked.updateHP(-1 * ULTRA_HYPER_GIGA_MECHA_HITLER_ATTACK_DAMAGE);
			} else
				thingBeingAttacked.updateHP(-1 * CATERPILLAR_ATTACK_DAMAGE);
			SoundManager.playChomp();
		}

	}

	@Override
	public void upgradeAttack(int newAttack) {
		if (!IS_LITERALLY_HITLER) {
			CATERPILLAR_ATTACK_DAMAGE = newAttack;
		}
	}

	@Override
	public void upgradeSpeed(int newSpeed) {
		speed = newSpeed;
	}

	@Override
	public void upgradeTotalHP(int hp) {
		if (!IS_LITERALLY_HITLER) {
			CATERPILLAR_HP = hp;
		}
	}

}
