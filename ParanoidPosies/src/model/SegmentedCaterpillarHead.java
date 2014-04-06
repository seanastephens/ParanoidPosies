package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SegmentedCaterpillarHead extends Caterpillar {
	public static final String CATERPILLAR_IMAGE = "SegmentedCaterpillarHead";
	private int maxSegments=8;
	SegmentedCaterpillarBody next;

	public SegmentedCaterpillarHead(Point location, GameBoard gameboard) {
		super(location, gameboard);
		Random rand = new Random();
		int segmentsNumber = rand.nextInt(maxSegments);
		this.setImage(ImageReg.getInstance().getImageFromStr(CATERPILLAR_IMAGE));
		next = new SegmentedCaterpillarBody(location, gameboard, segmentsNumber,this);
	}
	public SegmentedCaterpillarHead(Point location, GameBoard gameboard, boolean a){
		super(location, gameboard);
	}

}
