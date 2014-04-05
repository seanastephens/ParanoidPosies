package tests;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Caterpillar;
import model.GameBoard;
import model.GrowthState;
import model.Posie;

import org.junit.Test;

public class CaterpillarTests {
	
	private static GameBoard gb = new GameBoard();
	
	

	@Test
	public void testCaterpillarAttack(){
		try {
			Image picture = ImageIO.read(new File("images/thisIsAPosie.jpg"));
			Posie p = new Posie(new Point(1,1));
			
			Caterpillar c = new Caterpillar(new Point(2,2), gb);
			
			c.attack(p);
			
			int posieHealthShouldBe = Posie.posie_hitPoints - Caterpillar.CATERPILLAR_ATTACK_DAMAGE;
			
			assertEquals(posieHealthShouldBe, p.getHP());
			
		} catch (IOException e) {
			System.out.println("Failed loading posie.");
		}
	}

}
