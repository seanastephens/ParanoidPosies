package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import model.Bug;
import model.BugStrategy;
import model.GameBoard;
import model.SquareStrategy;
import model.Thing;

import org.junit.Test;

public class BugTest {

	@Test
	public void testConstructorAndDefaultGetters() {
		Bug bug = new MockBug(null);

		// These tests should probably not ever change -- everything in here
		// should be explicitly dealt with by the concrete classes anyways.
		assertEquals(0, bug.getHP());
		assertEquals(new Point(0, 0), bug.getLocation());
		assertEquals(null, bug.getStrategy());
		assertEquals(null, bug.getImage());
		assertEquals(null, bug.getObjectiveThing());
		assertEquals(null, bug.getObjectivePoint());
		assertTrue(bug.isDead());
		assertTrue(bug.shouldBeCleanedUp());
		assertEquals(null, bug.getGameBoard());

		// These tests *could* change if we make significant changes to the
		// layout of the game. In other words, failing these tests might
		// indicate breakage *or* it could just indicate a big game change.
		assertEquals(1, bug.getLayer());
		assertEquals("MockBug", bug.getType());
		assertEquals("HP=0 ", bug.getAction());
	}

	@Test
	public void testObjectivePointSetGetNull() {
		Bug bug = new MockBug(null);
		Point testPoint = new Point(1, 2);

		assertEquals(null, bug.getObjectivePoint());
		bug.setObjectivePoint(testPoint);
		assertEquals(testPoint, bug.getObjectivePoint());
		bug.setObjectiveToNull();
		assertEquals(null, bug.getObjectivePoint());
	}

	@Test
	public void testObjectiveThingSetGetNull() {
		Bug bug = new MockBug(null);
		Thing testThing = new MockBug(null);

		assertEquals(null, bug.getObjectivePoint());
		bug.setObjectiveThing(testThing);
		assertEquals(testThing, bug.getObjectiveThing());
		bug.setObjectiveToNull();
		assertEquals(null, bug.getObjectiveThing());
	}

	@Test
	public void testStrategyObjGetSet() {
		Bug bug = new MockBug(null);
		Thing testThing = new MockBug(null);
		BugStrategy testStrat = new SquareStrategy(bug, null);

		assertEquals(null, bug.getStrategy());
		bug.setStrategy(testStrat, testThing);
		assertEquals(testStrat, bug.getStrategy());
	}

	@Test
	public void testStrategyPointGetSet() {
		Bug bug = new MockBug(null);
		Point testPoint = new Point(1, 2);
		BugStrategy testStrat = new SquareStrategy(bug, null);

		assertEquals(null, bug.getStrategy());
		bug.setStrategy(testStrat, testPoint);
		assertEquals(testStrat, bug.getStrategy());
	}

	@Test
	public void testLocationGetSet() {
		Bug bug = new MockBug(null);
		Point testPoint = new Point(1, 2);
		bug.setLocation(testPoint);

		assertEquals(testPoint, bug.getLocation());
	}

	@Test
	public void testImageGetSet() {
		Bug bug = new MockBug(null);
		Image testImage = new BufferedImage(1, 1, 1);
		bug.setImage(testImage);

		assertEquals(testImage, bug.getImage());
	}

	@Test
	public void testHPSetGetUpdate() {
		Bug bug = new MockBug(null);

		bug.setHP(10);
		assertEquals(10, bug.getHP());
		bug.setHP(1);
		assertEquals(1, bug.getHP());
		bug.updateHP(1);
		assertEquals(2, bug.getHP());
	}

	@Test
	public void testIsDead() {
		Bug bug = new MockBug(null);

		bug.setHP(10);
		assertFalse(bug.isDead());
		bug.setHP(0);
		assertTrue(bug.isDead());
		bug.setHP(-3);
		assertTrue(bug.isDead());

	}

	/**
	 * MockBug extends Bug, but doesn't do anything else. This lets us test the
	 * methods in the abstract Bug class without without dealing with the
	 * specifics of what Bee or Caterpillar override.
	 * 
	 * @author Sean Stephens
	 * 
	 */
	private class MockBug extends Bug {
		public MockBug(GameBoard g) {
			super(g);
		}

		@Override
		public String getName() {
			return "";
		}

		@Override
		public void update() {
		}

		@Override
		public void attack(Thing thingBeingAttacked) {
		}
	}
}
