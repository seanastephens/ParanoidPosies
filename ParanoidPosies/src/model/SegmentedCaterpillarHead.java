package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SegmentedCaterpillarHead extends Caterpillar {
	public static final String CATERPILLAR_IMAGE = "SegmentedCaterpillarHead";
	private int maxSegments=8;

	public SegmentedCaterpillarHead(Point location, GameBoard gameboard) {
		super(location, gameboard);
		Random rand = new Random();
		int segmentsNumber = rand.nextInt(maxSegments);
		this.setImage(ImageReg.getInstance().getImageFromStr(CATERPILLAR_IMAGE));
		next(location, gameboard, segmentsNumber);
	}
	public SegmentedCaterpillarBody next(Point location, GameBoard gameboard, int number) {
		return new SegmentedCaterpillarBody(location, gameboard, number-1, this);
	}

}
