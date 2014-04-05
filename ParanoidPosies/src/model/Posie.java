package model;

import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import GUI.PPGUI;

/*
 * Will automatically grow and replenish nectar as it is updated. Use
 * updateHP(int) when attacking the Posie, and use takeNectar(int) when
 * collecting nectar. When the Posie is fully grown and does not have full
 * nectar, it will regenerate nectar at a rate of one per second until it is
 * full. When the Posie dies, it will drop a random number of seeds between 
 * 0 and posie_max_seeds_to_drop, its nectar will go to 0, and it will stop
 * regenerating nectar.
 */

public class Posie extends Plant {
	
	public static final int posie_max_seeds_to_drop = 5;
	public static final int posie_lifespan = 300 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_time_to_seedling = 30 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_time_to_flower = 90 * PPGUI.UPDATES_PER_SEC;
	public static final int posie_hitPoints = 10;
	public static final int posie_max_nectar = 10;

	public Posie(Image image, Point initialLocation) {
		super(image, initialLocation);
		maxSeedsToDrop = posie_max_seeds_to_drop;
		lifespan = posie_lifespan;
		setHP(posie_hitPoints);
	}

	@Override
	public void grow() {
		
		if(timer >= posie_time_to_seedling && timer < posie_time_to_flower){
			currentState = GrowthState.Seedling;
		}
		else if(timer >= posie_time_to_flower && timer < posie_lifespan){
			currentState = GrowthState.Flower;
			hasBloomed = true;
		}
		
		else if(timer >= posie_lifespan){
			hitPoints = 0;
		}
	}

	//If the flower has bloomed, and the nectar is not yet at capacity, increase
	//the nectar at a rate of 1 per second
	@Override
	public void replenishNectar() {
		if(hasBloomed && currentState == GrowthState.Flower && timer % PPGUI.UPDATES_PER_SEC == 0 
					&& currentNectar < posie_max_nectar && !isDead()){
			currentNectar++;
		}
	}

	@Override
	public void update() {
		timer++;
		grow();
		replenishNectar();
	}

	@Override
	public void updateHP(int hp) {
		hitPoints += hp;
		if(hitPoints <= 0){
			currentNectar = 0;
			Random rand = new Random();
			seedsDropped = rand.nextInt(maxSeedsToDrop);
		}
	}

}
