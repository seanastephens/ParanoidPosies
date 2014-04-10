package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.awt.Point;

import model.GrowthState;
import model.ImageReg;
import model.Posie;

import org.junit.Test;

import GUI.PPGUI;

public class PosieTests {

	private static Posie p;

	@Test
	public void testGrow() {
		p = new Posie(new Point(1, 1));

		assertEquals(0, p.getNectar());
		assertEquals(GrowthState.JustPlanted, p.getCurrentState());

		for (int i = 0; i < Posie.posie_time_to_seedling; i++) {
			p.update();
		}

		assertEquals(GrowthState.Seedling, p.getCurrentState());
		assertEquals(0, p.getNectar());

		for (int i = 0; i < Posie.posie_time_to_flower - Posie.posie_time_to_seedling; i++) {
			p.update();
		}

		assertEquals(GrowthState.Flower, p.getCurrentState());
		assertEquals(1, p.getNectar());

		// Increase by two nectar
		for (int i = 0; i < 2 * PPGUI.UPDATES_PER_SEC; i++) {
			p.update();
		}

		for (int i = 0; i < Posie.posie_lifespan - Posie.posie_time_to_flower; i++) {
			p.update();
		}

		assertEquals(GrowthState.DeadFlower, p.getCurrentState());
		assertEquals(0, p.getNectar());

	}

	@Test
	public void testGetHTMLDescription() {
		Posie p = new Posie(new Point(1, 1));
		
		String baseString = p.getName() + ", a " + p.getClass().getSimpleName() + "<br>";
		String seedString = baseString + Posie.SEED_ACTION + "<br>(" + p.getSeeds() + " seeds and " + p.getNectar() + " nectar).";
		
		assertEquals(p.getHTMLDescription().compareTo(seedString), 0);
		
		for(int i = 0; i < Posie.posie_time_to_seedling; i++){
			p.update();
		}
		
		assertEquals(GrowthState.Seedling, p.getCurrentState());
		
		String seedlingString = baseString + Posie.SEEDLING_ACTION + "<br>(" + p.getSeeds() + " seeds and " + p.getNectar() + " nectar).";
		assertEquals(p.getHTMLDescription().compareTo(seedlingString), 0);

		for(int i = 0; i < Posie.posie_time_to_flower - Posie.posie_time_to_seedling; i++){
			p.update();
		}
		assertEquals(GrowthState.Flower, p.getCurrentState());
		
		String flowerString = baseString + Posie.FLOWER_ACTION + "<br>(" + p.getSeeds() + " seeds and " + p.getNectar() + " nectar).";
				
		assertEquals(p.getHTMLDescription().compareTo(flowerString), 0);

		for(int i = 0; i < Posie.posie_lifespan - Posie.posie_time_to_flower; i++){
			p.update();
		}
		assertEquals(GrowthState.DeadFlower, p.getCurrentState());
		
		String deadFlowerString = baseString + Posie.DEAD_FLOWER_ACTION + "<br>(" + p.getSeeds() + " seeds and " + p.getNectar() + " nectar).";
		assertEquals(p.getHTMLDescription().compareTo(deadFlowerString), 0);

		
	}
	
	@Test
	public void testKillingPosie(){
		Posie p = new Posie(new Point(1, 1));
		
		for(int i = 0; i < Posie.posie_time_to_flower; i++){
			p.update();
		}
		assertEquals(GrowthState.Flower, p.getCurrentState());
		
		p.updateHP(-1 * Posie.posie_hitPoints);
		
		assertEquals(GrowthState.DeadFlower, p.getCurrentState());
		
		assertEquals(0, p.getHP());
		
		assertEquals(0, p.getNectar());
		
		assertEquals(0, p.getSeeds());
		
		assertTrue(p.shouldBeCleanedUp());
	}
	
	@Test
	public void testGettingImages(){
		ImageReg i = ImageReg.getInstance();
		Image justPlantedImage = i.getImageFromStr("JustPlanted");
		Image seedlingImage = i.getImageFromStr("Seedling");
		Image deadImage = i.getImageFromStr("DeadFlower");

		Image[] adultImage = new Image[6];
		adultImage[0] = i.getImageFromStr("Flower0");
		adultImage[1] = i.getImageFromStr("Flower1");
		adultImage[2] = i.getImageFromStr("Flower2");
		adultImage[3] = i.getImageFromStr("Flower3");
		adultImage[4] = i.getImageFromStr("Flower4");
		adultImage[5] = i.getImageFromStr("Flower5");
		
		Posie p = new Posie(new Point(1, 1));
		assertEquals(justPlantedImage, p.getImage());
		
		for(int k = 0; k < Posie.posie_time_to_seedling; k++){
			p.update();
		}
		assertEquals(GrowthState.Seedling, p.getCurrentState());
		assertEquals(seedlingImage, p.getImage());
		
		for(int k = 0; k < Posie.posie_time_to_flower - Posie.posie_time_to_seedling; k++){
			p.update();
		}
		assertEquals(GrowthState.Flower, p.getCurrentState());
		boolean isFlowerImage = false;
		for(int k = 0; k < adultImage.length; k++){
			if(adultImage[k] == p.getImage()){
				isFlowerImage = true;
			}
		}
		assertTrue(isFlowerImage);

		for(int k = 0; k < Posie.posie_lifespan - Posie.posie_time_to_flower; k++){
			p.update();
		}
		assertEquals(GrowthState.DeadFlower, p.getCurrentState());
		assertEquals(deadImage, p.getImage());

		
	}

}
