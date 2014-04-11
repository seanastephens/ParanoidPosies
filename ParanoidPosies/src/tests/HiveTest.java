package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import model.GameBoard;
import model.Hive;

import org.junit.Test;

public class HiveTest {

	Point origin = new Point(0, 0);

	@Test
	public void testGetLocation() {
		Hive h = new Hive();

		h.setLocation(origin);
		assertEquals(origin, h.getLocation());
	}

	@Test
	public void testGetHPAndIsDead() {
		Hive h = new Hive();

		assertEquals(50, h.getHP());
		assertFalse(h.isDead());
		assertFalse(h.shouldBeCleanedUp());
		h.setHP(100);
		assertEquals(100, h.getHP());
		h.updateHP(-150);
		assertEquals(-50, h.getHP());
		assertTrue(h.isDead());
		assertTrue(h.shouldBeCleanedUp());
	}

	@Test
	public void testGetLayer() {
		Hive h = new Hive();

		assertEquals(3, h.getLayer());
		h.setLayer(2);
		assertEquals(2, h.getLayer());
	}

	@Test
	public void testGetNector() {
		Hive h = new Hive();

		assertEquals(0, h.getNector());
		h.setNector(10);
		assertEquals(10, h.getNector());
		h.updateNector(-5);
		assertEquals(5, h.getNector());
	}

	@Test
	public void testGetHoney() {
		Hive h = new Hive();

		assertEquals(0, h.getHoney());
		h.setHoney(10);
		assertEquals(10, h.getHoney());
		h.updateHoney(-5);
		assertEquals(5, h.getHoney());
	}

	@Test
	public void testGetTimer() {
		Hive h = new Hive();

		assertEquals(0, h.getTimer());
		h.setTimer(10);
		assertEquals(10, h.getTimer());
		h.updateTimer(-5);
		assertEquals(5, h.getTimer());
	}

	@Test
	public void testGetSeeds() {
		Hive h = new Hive();

		assertEquals(0, h.getSeeds());
		h.setSeeds(10);
		assertEquals(10, h.getSeeds());
		h.updateSeeds(-5);
		assertEquals(5, h.getSeeds());
	}

	@Test
	public void testHTMLDescriptionAndName() {
		Hive h = new Hive();

		assertEquals("Its the Hive!<br>It has " + h.getNector() + " nectar, " + h.getHoney()
				+ " honey, and " + h.getSeeds() + " seeds", h.getHTMLDescription());
		assertEquals("Hive", h.getName());
	}

	@Test
	public void testCanBuildWarriorBee() {
		Hive h = new Hive();

		assertFalse(h.canBuildWarriorBee());
		h.updateHoney(20);
		assertTrue(h.canBuildWarriorBee());
	}

	@Test
	public void testBuildWarriorBee() {
		Hive h = new Hive();

		int bees = GameBoard.getBoard().getNumberOfBees();
		h.buildWarriorBee();
		assertEquals(0, h.getHoney());
		assertEquals(bees, GameBoard.getBoard().getNumberOfBees());
		h.updateHoney(25);
		h.buildWarriorBee();
		assertEquals(5, h.getHoney());
		assertEquals(bees + 1, GameBoard.getBoard().getNumberOfBees());
	}
}
