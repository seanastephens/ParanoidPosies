package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import model.Caterpillar;
import model.GameBoard;
import model.Posie;

import org.junit.Test;

public class CaterpillarTests {

	private static GameBoard gb = new GameBoard();

	@Test
	public void testCaterpillarAttack() {
		Posie p = new Posie(new Point(1, 1));
		Caterpillar c = new Caterpillar(new Point(2, 2), gb);
		c.attack(p);
		int posieHealthShouldBe = Posie.posie_hitPoints - Caterpillar.CATERPILLAR_ATTACK_DAMAGE;
		assertEquals(posieHealthShouldBe, p.getHP());
		System.out.println("Failed loading posie.");
	}

	@Test
	public void testGetHTMLDescription() {
		Caterpillar c = new Caterpillar(new Point(1, 1), gb);
		assertTrue(c.getHTMLDescription().length() > 0);
	}
}
