package model;

import java.awt.Point;

//Your bug is crazy and just moves in a square.
public class SquareStrategy implements BugStrategy{

	@Override
	public void getNextAction(Bug thisBug) {
		Point previousPoint;
		if(thisBug.getLocation() == thisBug.getObjective()){
			thisBug.setObjective(new Point(thisBug.getLocation().x+1, thisBug.getLocation().y+1));
		}
		else{
			previousPoint = thisBug.getLocation();
			thisBug.move(thisBug.getObjective());
			if(thisBug.getLocation() == thisBug.getObjective()){
				if(thisBug.getLocation().x > previousPoint.x){
					thisBug.setObjective(new Point(thisBug.getLocation().x, thisBug.getLocation().y+1));
				}
				else if(thisBug.getLocation().x < previousPoint.x){
					thisBug.setObjective(new Point(thisBug.getLocation().x, thisBug.getLocation().y-1));
				}
				else if(thisBug.getLocation().y > previousPoint.y){
					thisBug.setObjective(new Point(thisBug.getLocation().x-1, thisBug.getLocation().y));
				}
				else if(thisBug.getLocation().y < previousPoint.y){
					thisBug.setObjective(new Point(thisBug.getLocation().x+1, thisBug.getLocation().y+1));
				}
			}
		}
	}

}
