package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import model.Caterpillar;
import model.Posie;

import org.junit.Test;

public class CaterpillarTests {

	@Test
	public void testCaterpillarAttack() {
		Posie p = new Posie(new Point(1, 1));
		Caterpillar c = new Caterpillar(new Point(2, 2));
		c.attack(p);
		int posieHealthShouldBe = Posie.posie_hitPoints - Caterpillar.CATERPILLAR_ATTACK_DAMAGE;
		assertEquals(posieHealthShouldBe, p.getHP());
		System.out.println("Failed loading posie.");
	}

	@Test
	public void testGetHTMLDescription() {
		Caterpillar c = new Caterpillar(new Point(1, 1));
		assertTrue(c.getHTMLDescription().length() > 0);
	}

	@Test
	public void testHitlerAttack() {
		Posie p = new Posie(new Point(1, 1));
		Caterpillar c = new Caterpillar(new Point(2, 2));
		c.makeHitler();
		c.attack(p);
		int posieHealthShouldBe = Posie.posie_hitPoints
				- Caterpillar.ULTRA_HYPER_GIGA_MECHA_HITLER_ATTACK_DAMAGE;
		assertEquals(posieHealthShouldBe, p.getHP());
		System.out.println("Failed loading posie.");
	}

	@Test
	public void testGetHTMLDescriptionHitler() {
		Caterpillar c = new Caterpillar(new Point(1, 1));
		c.makeHitler();
		assertTrue(c.getHTMLDescription().length() > 0);
	}

	@Test
	public void testUpgradeAttackWithHitler() {
		Caterpillar c = new Caterpillar(new Point(0, 0));
		Caterpillar h = new Caterpillar(new Point(0, 0));
		h.makeHitler();
		Posie p = new Posie(new Point(0, 0));

		c.upgradeAttack(100);
		h.upgradeAttack(100);
		c.attack(p);
		assertTrue(p.getHP() < 0);
		h.attack(p);
		assertTrue(p.getHP() < -10000);
	}

	@Test
	public void testUpgradeSpeedWithHitler() {
		Caterpillar c = new Caterpillar(new Point(0, 0));
		Caterpillar h = new Caterpillar(new Point(0, 0));
		h.makeHitler();

		c.upgradeSpeed(10);
		h.upgradeSpeed(10);
		assertEquals(10, c.getSpeed());
		assertEquals(10, h.getSpeed());
	}

	@Test
	public void testUpgradeTotalHPWithHitler() {
		Caterpillar c = new Caterpillar(new Point(0, 0));
		Caterpillar h = new Caterpillar(new Point(0, 0));
		h.makeHitler();

		c.upgradeTotalHP(10);
		h.upgradeTotalHP(10);
		assertEquals(10, c.getMaxHP());
		assertEquals(10, c.getHP());
		System.out.println(h.getMaxHP());
		System.out.println(h.getHP());
		assertTrue(h.getMaxHP() > 1000);
		assertTrue(h.getHP() > 1000);
	}
}
