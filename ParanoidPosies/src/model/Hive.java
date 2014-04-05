package model;

import java.awt.Image;
import java.awt.Point;

public class Hive implements Thing{

	private Point location;
	private int hp;
	private Image image;
	private final int layer = 3;
	private int nector;
	private int honey;
	
	public Hive(){
		nector = 0;
		honey = 0;
	}
	
	public int getNector(){
		return nector;
	}
	
	public void setNector(int nector){
		this.nector = nector;
	}
	
	public int getHoney(){
		return honey;
	}
	
	public void setHoney(int honey){
		this.honey = honey;
	}
	
	public void updateNector(int value){
		nector += value;
	}
	
	public void updateHoney(int value){
		honey += value;
	}
	
	public void convertNectorToHoney(){
		updateNector(-1);
		updateHoney(1);
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
	public void updateHP(int hp) {
		this.hp += hp;
	}

	@Override
	public int getHP() {
		return hp;
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
	public int getLayer() {
		return layer;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
