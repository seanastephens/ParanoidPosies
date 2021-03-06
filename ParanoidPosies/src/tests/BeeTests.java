package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import model.Bee;
import model.Caterpillar;
import model.GameBoard;
import model.GatherStrategy;
import model.GrowthState;
import model.Hive;
import model.ImageReg;
import model.MoveStrategy;
import model.Posie;

import org.junit.Test;

public class BeeTests {

	Bee bee = new Bee(new Point(1, 1), GameBoard.getBoardWithNoSound());
	Caterpillar cage = new Caterpillar(new Point(0, 0));
	Posie posie = new Posie(new Point(0, 0));
	Hive hive = new Hive();

	/*
	 * Tests getBeeName, getName, setName, and uses buildBeeNamesList
	 */
	@Test
	public void testBeeNames() {
		System.out.println("\n\ntestBeeNames():\n\n");
		for (int i = 0; i < 10; i++) {
			bee.setName("LameBeeName");
			assertTrue(bee.getName().equals("LameBeeName"));
			System.out.print("Initial Bee name: " + bee.getName() + " and the new bee name: ");
			bee.giveNewRandomName();
			System.out.println(bee.getName());
			assertFalse(bee.getName().equals("LameBeeName"));
		}
	}

	/*
	 * Tests setSpeed, getSpeed, and UpgradeSpeed
	 */
	@Test
	public void testBeeSpeed() {
		System.out.println("\n\ntestBeeSpeed():\n\n");
		assertEquals(2, bee.getSpeed());
		System.out.println("Bee ininitial speed: " + bee.getSpeed());
		bee.setSpeed(4);
		System.out.println("Bee set speed: " + bee.getSpeed());
		assertEquals(4, bee.getSpeed());
		bee.upgradeSpeed(1);
		System.out.println("Bee upgraded speed: " + bee.getSpeed());
		assertEquals(5, bee.getSpeed());
	}

	/*
	 * Tests upgradeTotalHP and inherited methods getHP, setHP, and updateHP
	 */

	@Test
	public void testBeeHP() {
		System.out.println("\n\ntestBeeHP():\n\n");
		assertEquals(5, bee.getHP());
		System.out.println("Bee ininitial hp: " + bee.getHP());
		bee.setHP(10);
		System.out.println("Bee set hp: " + bee.getHP());
		assertEquals(10, bee.getHP());
		bee.upgradeTotalHP(1);
		System.out.println("Bee upgraded hp: " + bee.getHP());
		assertEquals(11, bee.getHP());
		bee.upgradeTotalHP(-1);
		System.out.println("Bee upgraded hp: " + bee.getHP());
		assertEquals(10, bee.getHP());
		assertFalse(bee.getHP() == 11);
		assertTrue(bee.getHP() == 10);
		bee.updateHP(-4);
		System.out.println("Bee updated hp: " + bee.getHP());
		assertEquals(6, bee.getHP());
		bee.updateHP(3);
		System.out.println("Bee updated hp: " + bee.getHP());
		assertEquals(9, bee.getHP());
	}

	@Test
	public void testGetImage() {
		ImageReg i = ImageReg.getInstance();
		assertTrue(bee.getImage().equals(i.getImageFromStr("Bee00")));
		assertFalse(bee.getImage().equals(i.getImageFromStr("BeeS00")));
		bee.setSelected(true);
		assertTrue(bee.getImage().equals(i.getImageFromStr("BeeS00")));
	}

	@Test
	public void testSetSelected() {
		boolean f = false;
		boolean t = true;
		assertTrue(!bee.getSelected());
		bee.setSelected(t);
		assertTrue(bee.getSelected());
		bee.setSelected(f);
		assertTrue(!bee.getSelected());
		assertFalse(bee.getSelected());
	}

	// Starting point 1,1
	@Test
	public void testUpdate() {
		// Use the following commented out lines to help debug when this test
		// fails.
		// System.out.println(bee.getLocation().toString() +
		// bee.getObjectivePoint().toString());
		System.out.println("\n\ntestBeeUpdate():\n\n");
		System.out.println(System.currentTimeMillis());
		bee.setLocation(new Point(10, 10));
		bee.setStrategy(new MoveStrategy(bee), new Point(200, 10));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}
		// System.out.println("\n" + bee.getLocation().toString() +
		// bee.getObjectivePoint().toString());
		assertEquals(2, bee.getImageNumberForTesting());
		// System.out.println("Passed movement to right test");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(-200, 10));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}
		// System.out.println("\n" + bee.getLocation().toString() +
		// bee.getObjectivePoint().toString());
		assertEquals(6, bee.getImageNumberForTesting());
		// System.out.println("Passed movement to left test");

		bee.setLocation(new Point(0, 0));
		bee.setObjectivePoint(new Point(0, 0));
		// System.out.println("\n" + bee.getLocation().toString() +
		// bee.getObjectivePoint().toString());
		assertEquals(6, bee.getImageNumberForTesting());
		// System.out.println("Passed break out of for loop test so image stayed the same");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(10, 200));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(4, bee.getImageNumberForTesting());
		// System.out.println("Passed movement down test");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(10, -200));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(0, bee.getImageNumberForTesting());
		// System.out.println("Passed movement up test");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(200, 200));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(3, bee.getImageNumberForTesting());
		// System.out.println("Passed movement down and to the right test");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(-200, -200));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(7, bee.getImageNumberForTesting());
		// System.out.println("Passed movement left and up test");

		bee.setLocation(new Point(0, 0));
		bee.setObjectivePoint(new Point(-200, 200));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(5, bee.getImageNumberForTesting());
		// System.out.println("Passed movement left and down test");

		bee.setLocation(new Point(0, 0));
		bee.setObjectivePoint(new Point(200, 0));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(2, bee.getImageNumberForTesting());
		// System.out.println("Passed movement right and up test");
		System.out.println(System.currentTimeMillis());

		bee.setLocation(new Point(0, 0));
		bee.setObjectivePoint(new Point(200, -200));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(1, bee.getImageNumberForTesting());
		// System.out.println("Passed movement right and up test");
		System.out.println(System.currentTimeMillis());
	}

	/*
	 * Tests getAttack, setAttack, upgradeAttack and attack
	 */

	@Test
	public void attackTest() {
		bee.setLocation(new Point(0, 0));
		assertEquals(1, bee.getAttack());
		bee.setAttack(2);
		assertEquals(2, bee.getAttack());
		bee.attack(cage);
		assertTrue(bee.isDead());
		assertEquals(1, cage.getHP());
		bee.upgradeAttack(-1);
		assertEquals(1, bee.getAttack());
	}

	/*
	 * Tests getNectarBeingHeld, calculateNectarToGet, getNectarToGetForTesting,
	 * askFlowerForNectarOrSeeds unloadNectarAndSeedsToHive, getSeeds,
	 * updateSeeds, setSeeds Exception thrown at end of method to ensure other
	 * parts of test run.
	 */

	@Test(expected = IllegalStateException.class)
	public void nectarAndSeedsTest() {
		bee.setHP(4);
		bee.setStrategy(new GatherStrategy(bee), posie);
		bee.setLocation(new Point(0, 0));
		posie.setLocation(new Point(0, 0));
		posie.setCurrentState(GrowthState.Flower);
		posie.setNectarForTesting(5);
		assertEquals(5, posie.getNectar());
		assertEquals(0, bee.getNectarBeingHeld());
		bee.calculateNectarToGet();
		assertEquals(10, bee.getNectarToGetForTesting());
		bee.askFlowerForNectarOrSeeds();
		assertEquals(5, bee.getNectarBeingHeld());
		bee.calculateNectarToGet();
		assertEquals(5, bee.getNectarToGetForTesting());
		assertEquals(0, posie.getNectar());
		posie.setNectarForTesting(5);
		bee.askFlowerForNectarOrSeeds();
		bee.calculateNectarToGet();
		assertEquals(10, bee.getNectarBeingHeld());
		assertEquals(0, bee.getNectarToGetForTesting());
		posie.setHP(0);
		hive.setLocation(new Point(1, 1));
		bee.setLocation(new Point(1, 1));
		bee.unloadNectarAndSeedsToHive();
		assertEquals(0, bee.getNectarBeingHeld());
		bee.setLocation(new Point(0, 0));
		posie.setSeedsForTesting(3);
		bee.askFlowerForNectarOrSeeds();
		assertEquals(3, bee.getSeeds());
		assertEquals(0, posie.getSeeds());
		bee.updateSeeds(2);
		assertEquals(5, bee.getSeeds());
		bee.setLocation(new Point(1, 1));
		bee.unloadNectarAndSeedsToHive();
		assertEquals(0, bee.getSeeds());

		bee.setSeeds(4);
		assertEquals(4, bee.getSeeds());
		bee.setObjectiveThing(hive);
		bee.askFlowerForNectarOrSeeds();
	}

	@Test
	public void testGetHTMLDescription() {
		assertTrue(bee.getHTMLDescription().length() > 0);
	}

}