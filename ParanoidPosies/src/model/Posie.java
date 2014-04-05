package model;

import java.awt.Image;
import java.awt.Point;

import GUI.ParanoidPosieGUI;

public class Posie extends Plant {
	
	public static final int posie_lifespan = 300 * ParanoidPosieGUI.UPDATES_PER_SEC;
	public static final int posie_time_to_seedling = 30 * ParanoidPosieGUI.UPDATES_PER_SEC;
	public static final int posie_time_to_flower = 90 * ParanoidPosieGUI.UPDATES_PER_SEC;
	public static final int posie_hitPoints = 10;
	public static final int posie_max_nectar = 10;

	public Posie(Image image, Point initialLocation) {
		super(image, initialLocation);
		lifespan = posie_lifespan;
		setHP(posie_hitPoints);
	}

	@Override
	public void grow() {
		if(timer >= posie_time_to_seedling && timer < posie_time_to_flower){
			currentState = GrowthState.Seedling;
		}
		else if(timer >= posie_time_to_flower){
			currentState = GrowthState.Flower;
			hasBloomed = true;
		}
	}

	@Override
	public void replenishNectar() {
		if(hasBloomed && currentState == GrowthState.Flower && timer % ParanoidPosieGUI.UPDATES_PER_SEC == 0 
					&& currentNectar < posie_max_nectar){
			currentNectar++;
		}
	}

	@Override
	public void update() {
		timer++;
		grow();
		replenishNectar();
	}

}
