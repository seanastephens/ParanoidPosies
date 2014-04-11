package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import model.GrowthState;
import model.ImageReg;
import model.Plant;

import org.junit.Test;

public class PlantTest {

	Point origin = new Point(0, 0);

	@Test
	public void testDefault() {
		MockPlant p = new MockPlant(origin);

		assertEquals(0, p.getHP());
		assertEquals(origin, p.getLocation());
		assertEquals(null, p.getImage());
		assertEquals(GrowthState.JustPlanted, p.getCurrentState());
		assertEquals(null, p.getName());
		assertEquals(0, p.getMaxHP());
		assertEquals(2, p.getLayer());
	}

	@Test
	public void testHTML() {
		MockPlant p = new MockPlant(origin);
		assertEquals(p.getName() + ", a " + p.getClass().getSimpleName(), p.getHTMLDescription());
	}

	@Test
	public void testCurrentState() {
		MockPlant p = new MockPlant(origin);

		assertEquals(GrowthState.JustPlanted, p.getCurrentState());
		p.setCurrentState(GrowthState.Flower);
		assertEquals(GrowthState.Flower, p.getCurrentState());
	}

	@Test
	public void testHP() {
		MockPlant p = new MockPlant(origin);
		p.updateHP(10);
		assertEquals(10, p.getHP());
		p.setHP(15);
		assertEquals(15, p.getHP());
	}

	@Test
	public void testIsDead() {
		MockPlant p = new MockPlant(origin);
		assertTrue(p.isDead());
		p.setHP(1);
		assertFalse(p.isDead());
	}

	@Test
	public void testShouldBeCleanedUp() {
		MockPlant p = new MockPlant(origin);
		assertTrue(p.shouldBeCleanedUp());
		p.setHP(1);
		assertFalse(p.shouldBeCleanedUp());
	}

	@Test
	public void testMaxHP() {
		MockPlant p = new MockPlant(origin);
		p.setMaxHP(10);
		assertEquals(10, p.getMaxHP());
		p.updateMaxHP(20);
		assertEquals(30, p.getMaxHP());
	}

	@Test
	public void testName() {
		MockPlant p = new MockPlant(origin);
		p.setName("Fil");
		assertEquals("Fil", p.getName());
	}

	@Test
	public void testLayer() {
		MockPlant p = new MockPlant(origin);
		p.setLayer(5);
		assertEquals(5, p.getLayer());
	}

	@Test
	public void testContains() {
		MockPlant p = new MockPlant(origin);
		p.setImage(ImageReg.getInstance().getImageFromStr("Hive"));
		assertTrue(p.contains(origin));
		assertFalse(p.contains(new Point(1000, 1000)));
	}

	private class MockPlant extends Plant {
		public MockPlant(Point loc) {
			super(loc);
		}

		@Override
		public void update() {

		}

		@Override
		public void grow() {

		}

		@Override
		public void replenishNectar() {

		}
	}

}
