package model;
import java.awt.Point;

public class SegmentedCaterpillarBody extends SegmentedCaterpillarHead {
	public static final String CATERPILLAR_IMAGE = "SegmentedCaterpillarBody";
	private SegmentedCaterpillarHead previous;

	public SegmentedCaterpillarBody(Point location, GameBoard gameboard, int segmentsNumber, SegmentedCaterpillarHead prev) {
		super(location, gameboard);
		previous = prev;
		this.setStrategy(new SegmentStrategy(this, gameboard), prev);
		this.setImage(ImageReg.getInstance().getImageFromStr(CATERPILLAR_IMAGE));
		if(segmentsNumber>0) {
			next(location,gameboard,segmentsNumber-1);
		}
	}
	
	
	
}
