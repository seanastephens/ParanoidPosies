package model;
import java.awt.Point;

public class SegmentedCaterpillarBody extends SegmentedCaterpillarHead {
	public static final String CATERPILLAR_IMAGE = "SegmentedCaterpillarBody";
	private SegmentedCaterpillarHead previous;
	SegmentedCaterpillarBody next;

	public SegmentedCaterpillarBody(Point location, GameBoard gameboard, int segmentsNumber, SegmentedCaterpillarHead prev) {
		super(location, gameboard, true);
		previous = prev;
		this.setStrategy(new SegmentStrategy(this, gameboard), prev);
		this.setImage(ImageReg.getInstance().getImageFromStr(CATERPILLAR_IMAGE));
		if(segmentsNumber>0) {
			next = new SegmentedCaterpillarBody(location,gameboard,segmentsNumber-1, this);
			gameboard.addThing(this);
		}
		setHP(segmentsNumber);
	}
	public void update(){
		super.update();
		this.setImage(ImageReg.getInstance().getImageFromStr(CATERPILLAR_IMAGE));
	}
	public void propogateHP(int hp) {
		System.out.println("updated body!" +getHP());
		updateHP(hp);
		if (next!=null){
			next.propogateHP(hp);
		}
	}
	
	
	
}
