package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SegmentedCaterpillarHead extends Caterpillar {
	public static final String C1 = "SegmentedCaterpillarHead1";
	public static final String C2 = "SegmentedCaterpillarHead2";
	public static final String C3 = "SegmentedCaterpillarHead3";
	public static final String C4 = "SegmentedCaterpillarHead4";
	public static final String C5 = "SegmentedCaterpillarHead5";
	public static final String C6 = "SegmentedCaterpillarHead6";
	public static final String C7 = "SegmentedCaterpillarHead7";
	public static final String C0 = "SegmentedCaterpillarHead0";
	private int maxSegments=8;
	SegmentedCaterpillarBody next;

	public SegmentedCaterpillarHead(Point location, GameBoard gameboard) {
		super(location, gameboard);
		Random rand = new Random();
		int segmentsNumber = rand.nextInt(maxSegments);
		this.setImage(ImageReg.getInstance().getImageFromStr(C4));
		next = new SegmentedCaterpillarBody(location, gameboard, segmentsNumber,this);
		setHP(segmentsNumber+1);
	}
	public SegmentedCaterpillarHead(Point location, GameBoard gameboard, boolean a){
		super(location, gameboard);
	}
	public void update(){
		Point before = this.getLocation();
		super.update();
		Point after = this.getLocation();
		int diffx = after.x-before.x;
		int diffy = after.y-before.y;
		if (diffx > 0 && diffy == 0) {
			this.setImage(ImageReg.getInstance().getImageFromStr(C2));
		} else if (diffx > 0 && diffy > 0) {
			this.setImage(ImageReg.getInstance().getImageFromStr(C3));
		} else if (diffx > 0 && diffy < 0) {
			this.setImage(ImageReg.getInstance().getImageFromStr(C1));
		} else if (diffx == 0 && diffy > 0) {
			this.setImage(ImageReg.getInstance().getImageFromStr(C4));
		} else if (diffx == 0 && diffy < 0) {
			this.setImage(ImageReg.getInstance().getImageFromStr(C0));
		} else if (diffx < 0 && diffy == 0) {
			this.setImage(ImageReg.getInstance().getImageFromStr(C6));
		} else if (diffx < 0 && diffy > 0) {
			this.setImage(ImageReg.getInstance().getImageFromStr(C5));
		} else if (diffx < 0 && diffy < 0) {
			this.setImage(ImageReg.getInstance().getImageFromStr(C7));
		}
	}
	@Override
	public void updateHP(int hp) {
		System.out.println("updated!"+getHP());
		this.setHP(this.getHP()+hp);
		if (next!=null){
			next.propogateHP(hp);
		}
	}

}
