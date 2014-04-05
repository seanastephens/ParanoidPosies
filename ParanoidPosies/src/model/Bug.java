package model;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Bug implements Thing{
	private Point location;
	private int hp;
	private Image image;
	private final int layer = 1;
	private BugStrategy currentStrategy;
	private Point objective;
	private GameBoard gameboard;
	private List<BugStrategy> strategies;
	
	public Bug(GameBoard gameboard){
		this.gameboard = gameboard;
		strategies = new ArrayList<BugStrategy>();
	}
	
	public boolean shouldBeCleanedUp(){
		return isDead();
	}
	
	public List<BugStrategy> getListOfBugStrategies(){
		return strategies;
	}
	
	public void setListOfBugStrategies(List<BugStrategy> list){
		strategies = list;
	}
	
	public String getAction(){
		return currentStrategy.getClass().getSimpleName();
	}
	
	public String getType(){
		return this.getClass().getSimpleName();
	}
	
	public GameBoard getGameBoard(){
		return gameboard;
	}
	
	public void setGameBoard(GameBoard board){
		gameboard = board;
	}
	
	@Override
	public void setLocation(Point loc) {
		location = loc;
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void setHP(int hp) {
		this.hp = hp;
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public int getLayer(){
		return layer;
	}
	
	@Override
	public boolean isDead() {
		if(hp <= 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public abstract void update();
	
	@Override
	public void updateHP(int hp){
		this.hp += hp;
	}
	
	public void setStrategy(BugStrategy strat, Point objectiveLocation){
		currentStrategy = strat;
		objective = objectiveLocation;
	}
	
	public Point getObjective(){
		return objective;
	}
	
	public void setObjective(Point newObjective){
		objective = newObjective;
	}
	
	public BugStrategy getStrategy(){
		return currentStrategy;
	}
	
	//Since things don't teleport, this is where the animations take place to move the Thing
	//from one place to another.
	public void move(Point endLocation) {
		if(this.getLocation() != endLocation){
			if(this.getLocation().x < endLocation.x){
				this.setLocation(new Point(this.getLocation().x+1, this.getLocation().y));
			}
			if(this.getLocation().x > endLocation.x){
				this.setLocation(new Point(this.getLocation().x-1, this.getLocation().y));
			}
			if(this.getLocation().y < endLocation.y){
				this.setLocation(new Point(this.getLocation().x, this.getLocation().y+1));
			}
			if(this.getLocation().y > endLocation.y){
				this.setLocation(new Point(this.getLocation().x, this.getLocation().y-1));
			}
		}
	}
	
	//Takes the object of the thing being attacked so updates can be made to both objects.
	public abstract void attack(Thing thingBeingAttacked);
}
