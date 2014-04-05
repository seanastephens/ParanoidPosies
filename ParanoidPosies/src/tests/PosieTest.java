package tests;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.*;

import org.junit.Test;

import GUI.PPGUI;

public class PosieTest {
	
	

	@Test
	public void testPosieGrowing(){
		try {
			Image picture = ImageIO.read(new File("images/thisIsAPosie.jpg"));
			Posie p = new Posie(picture, new Point(1,1));
			assertEquals(p.currentState, GrowthState.JustPlanted);
			for(int i = 0; i < Posie.posie_time_to_seedling; i++){
				p.update();
			}
			assertEquals(p.currentState, GrowthState.Seedling);
			int interimTime = Posie.posie_time_to_flower - Posie.posie_time_to_seedling;
			
			for(int j = 0; j < interimTime; j++){
				p.update();
			}
			assertEquals(p.currentState, GrowthState.Flower);
			assertTrue(p.hasBloomed);
		} catch (IOException e) {
			System.out.println("Failed loading posie.");
		}
		
		
	}
	
	@Test
	public void testPosieGeneratingNectar(){
		try {
			Image picture = ImageIO.read(new File("images/thisIsAPosie.jpg"));
			Posie p = new Posie(picture, new Point(1,1));
			for(int i = 0; i < Posie.posie_time_to_flower + PPGUI.UPDATES_PER_SEC * 3; i ++){
				p.update();
			}
			assertEquals(4, p.currentNectar);
		} catch (IOException e) {
			System.out.println("Failed loading posie.");
		}
	}
	
	@Test
	public void testRemovingNectarFromPosie(){
		try {
			Image picture = ImageIO.read(new File("images/thisIsAPosie.jpg"));
			Posie p = new Posie(picture, new Point(1,1));
			for(int i = 0; i < Posie.posie_time_to_flower + PPGUI.UPDATES_PER_SEC * 3; i ++){
				p.update();
			}
			assertEquals(4, p.currentNectar);
			
			p.takeNectar(2);
			assertEquals(2, p.currentNectar);
			
			for(int k = 0; k < PPGUI.UPDATES_PER_SEC * 3; k++){
				p.update();
			}
			assertEquals(5, p.currentNectar);
		} catch (IOException e) {
			System.out.println("Failed loading posie.");
		}
	}
	
	@Test
	public void testPosieDoesNotFillBeyondCapacity(){
		try {
			Image picture = ImageIO.read(new File("images/thisIsAPosie.jpg"));
			Posie p = new Posie(picture, new Point(1,1));
			int nectarToAdd = Posie.posie_max_nectar - 1;
			
			for(int i = 0; i < Posie.posie_time_to_flower + PPGUI.UPDATES_PER_SEC * nectarToAdd; i ++){
				p.update();
			}
			assertEquals(p.currentNectar, Posie.posie_max_nectar);
			
			for(int i = 0; i < Posie.posie_time_to_flower + PPGUI.UPDATES_PER_SEC * nectarToAdd; i ++){
				p.update();
			}
			assertEquals(p.currentNectar, Posie.posie_max_nectar);
			
			p.takeNectar(5);
			assertEquals(p.currentNectar, Posie.posie_max_nectar - 5);
			
			for(int i = 0; i < Posie.posie_time_to_flower + PPGUI.UPDATES_PER_SEC * nectarToAdd; i ++){
				p.update();
			}
			assertEquals(p.currentNectar, Posie.posie_max_nectar);
			
			
			
		} catch (IOException e) {
			System.out.println("Failed loading posie.");
		}
	}
	
	@Test
	public void testPosieGettingAttacked(){
		try {
			Image picture = ImageIO.read(new File("images/thisIsAPosie.jpg"));
			Posie p = new Posie(picture, new Point(1,1));
			
			p.updateHP(-(Posie.posie_hitPoints - 3));
			assertEquals(3, p.getHP());
			
			for(int i = 0; i < Posie.posie_time_to_flower + PPGUI.UPDATES_PER_SEC * Posie.posie_max_nectar; i ++){
				p.update();
			}
			assertEquals(p.currentNectar, Posie.posie_max_nectar);
			
			p.updateHP(-3);
			assertTrue(p.isDead());
			System.out.println("Posie p dropped " + p.seedsDropped + " seeds.");
			
			assertEquals(0, p.currentNectar);
			for(int i = 0; i < Posie.posie_time_to_flower + PPGUI.UPDATES_PER_SEC * Posie.posie_max_nectar; i ++){
				p.update();
			}
			assertEquals(0, p.currentNectar);
			
		} catch (IOException e) {
			System.out.println("Failed loading posie.");
		}
	}
	
	@Test
	public void testPosieDeathFromTime(){
		try {
			Image picture = ImageIO.read(new File("images/thisIsAPosie.jpg"));
			Posie p = new Posie(picture, new Point(1,1));
			
			for(int i = 0; i < Posie.posie_lifespan; i++){
				p.update();
			}
			
			assertTrue(p.isDead());
			
		} catch (IOException e) {
			System.out.println("Failed loading posie.");
		}
	}
	
	@Test public void testPosieShouldBeCleanedUp(){
		try {
			Image picture = ImageIO.read(new File("images/thisIsAPosie.jpg"));
			Posie p = new Posie(picture, new Point(1,1));
			
			assertFalse(p.shouldBeCleanedUp);
			
			for(int i = 0; i < Posie.posie_lifespan; i++){
				p.update();
			}
			
			assertTrue(p.isDead());
			
			if(p.getSeeds() > 0){
				assertFalse(p.shouldBeCleanedUp);
				System.out.println("Taking " + p.takeSeeds() + " seeds from Posie p.");
				assertTrue(p.shouldBeCleanedUp());
			}
			assertTrue(p.shouldBeCleanedUp());
			
		} catch (IOException e) {
			System.out.println("Failed loading posie.");
		}
	}
	

}
