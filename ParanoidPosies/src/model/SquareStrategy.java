package model;

import java.awt.Point;

//Your bug is crazy and just moves in a square.
public class SquareStrategy implements BugStrategy {

	public static int SQUARE_CONST = 50;
	private GameBoard board;
	
	public GameBoard getBoard(){
		return board;
	}
	
	@Override
	public void getNextAction(Bug thisBug, GameBoard board) {
		this.board = board;
		Point previousPoint;
		if (thisBug.getLocation().equals(thisBug.getObjective())) {
			thisBug.setALocationToMoveTo(new Point(thisBug.getLocation().x
					+ SQUARE_CONST, thisBug.getLocation().y + SQUARE_CONST));
		} else {
			previousPoint = thisBug.getLocation();
			thisBug.move(thisBug.getALocationToMoveTo());
			if (thisBug.getLocation().equals(thisBug.getObjective())) {
				if (thisBug.getLocation().x > previousPoint.x) {
					thisBug.setALocationToMoveTo(new Point(thisBug.getLocation().x,
							thisBug.getLocation().y + SQUARE_CONST));
				} else if (thisBug.getLocation().x < previousPoint.x) {
					thisBug.setALocationToMoveTo(new Point(thisBug.getLocation().x,
							thisBug.getLocation().y - SQUARE_CONST));
				} else if (thisBug.getLocation().y > previousPoint.y) {
					thisBug.setALocationToMoveTo(new Point(thisBug.getLocation().x
							- SQUARE_CONST, thisBug.getLocation().y));
				} else if (thisBug.getLocation().y < previousPoint.y) {
					thisBug.setALocationToMoveTo(new Point(thisBug.getLocation().x
							+ SQUARE_CONST, thisBug.getLocation().y
							+ SQUARE_CONST));
				}
			}
		}
	}

}
