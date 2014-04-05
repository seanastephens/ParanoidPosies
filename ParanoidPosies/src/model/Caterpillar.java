package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Caterpillar extends Bug {
	public static final int CATERPILLAR_HP = 5;
	public static final int CATERPILLAR_ATTACK_DAMAGE = 1;
	public int timer;
	private List<String> CaterpillarNameList;
	public static final String CATERPILLAR_IMAGE = "Caterpillar";
	public static final int ULTRA_HYPER_GIGA_MECHA_HITLER_HP = 1000000;
	public static final int ULTRA_HYPER_GIGA_MECHA_HITLER_ATTACK_DAMAGE = 1000000;
	public static final String ULTRA_HYPER_GIGA_MECHA_HITLER_NAME = "ULTRA HYPER GIGA MECHA HITLER";
	public boolean IS_LITERALLY_HITLER;
	public static final String ULTRA_HYPER_GIGA_MECHA_HITLER_IMAGE_NAME = "HITLER";

	private String name;
	
	public Caterpillar(Point location, GameBoard gameboard) {
		super(gameboard);
		timer = 0;
		this.setLocation(location);
		this.setHP(CATERPILLAR_HP);
		this.setImage(ImageReg.getInstance().getImageFromStr(CATERPILLAR_IMAGE));
		CaterpillarNameList = new ArrayList<String>();
		this.setUpNameList();
		name = getCaterpillarName();
		
		this.setStrategy(new SquareStrategy(), new Point(
				this.getLocation().x + 1, this.getLocation().y + 1));
		
		// TODO Auto-generated constructor stub
	}
	
	//Caterpillar names go here
	private void setUpNameList(){
		CaterpillarNameList.add("Wormy");
		CaterpillarNameList.add("Hungry");
		CaterpillarNameList.add("Bulldozer");
		CaterpillarNameList.add("404 Name Not Found");
	}
	
	private String getCaterpillarName(){
		String name = "";
		Collections.shuffle(CaterpillarNameList);
		name = CaterpillarNameList.get(0);
		
		Random rand = new Random();
		int nameInt = rand.nextInt(1000000);
		if(nameInt == 666){
			name = ULTRA_HYPER_GIGA_MECHA_HITLER_NAME;
			this.setHP(ULTRA_HYPER_GIGA_MECHA_HITLER_HP);
			IS_LITERALLY_HITLER = true;
			this.setImage(ImageReg.getInstance().getImageFromStr(ULTRA_HYPER_GIGA_MECHA_HITLER_IMAGE_NAME));
		}
		else IS_LITERALLY_HITLER = false;
		return name;
	}

	@Override
	public void update() {
		timer++;
		
		this.getStrategy().getNextAction(this, this.getGameBoard());
		if (this.getLocation() != this.getObjective()) {
			this.move(this.getObjective());
		}

	}

	@Override
	public void attack(Thing thingBeingAttacked) {
		// TODO Auto-generated method stub

	}

}
