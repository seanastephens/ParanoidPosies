package model;

import java.awt.Point;

public class BeeWarrior extends Bee{

	public BeeWarrior(Point location, GameBoard board) {
		super(location, board);
		setHP(BEE_HP * 2);
	}
	
	@Override
	public void attack(Thing thingBeingAttacked) {
		if(thingBeingAttacked != null){
			thingBeingAttacked.updateHP(-1 * BEE_ATTACK_DAMAGE * 2);
			this.updateHP(-1 * BEE_HP);
		}
	}
	
	@Override
	public String getAction() {
		String result = super.getAction();
		result += "\nAttack: " + BEE_ATTACK_DAMAGE * 2;
		return result;
	}
	
	@Override
	public void update() {
		if(this.getStrategy() instanceof GatherStrategy){
			this.setStrategy(new MoveStrategy(this, super.getGameBoard()), super.getGameBoard().getHive());
		}
		if (this.getStrategy() != null) {
			Point temp;
			for(int i = 0; i < super.getSpeed(); i++){
				temp = this.getLocation();
				this.getStrategy().doNextAction();
				if(temp.equals(this.getLocation())){
					break;
				}
			}
			
		}
	}

}
