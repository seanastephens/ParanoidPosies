package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.GrowthState;
import model.Posie;

public class GrowthStateTest {

	Posie posie = new Posie(new Point(0,0));
	
	@Test
	public void testGrowthState(){
		posie.setCurrentState(GrowthState.DeadFlower);
		assertTrue(posie.getCurrentState() == GrowthState.DeadFlower);
		assertFalse(posie.getCurrentState() == GrowthState.Flower);
		
		posie.setCurrentState(GrowthState.Flower);
		assertTrue(posie.getCurrentState() == GrowthState.Flower);
		assertFalse(posie.getCurrentState() == GrowthState.JustPlanted);
		
		posie.setCurrentState(GrowthState.JustPlanted);
		assertTrue(posie.getCurrentState() == GrowthState.JustPlanted);
		assertFalse(posie.getCurrentState() == GrowthState.Flower);
		
		posie.setCurrentState(GrowthState.Seedling);
		assertTrue(posie.getCurrentState() == GrowthState.Seedling);
		assertFalse(posie.getCurrentState() == GrowthState.Flower);
	}
	
}
