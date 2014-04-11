package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import model.BeeWarrior;
import model.Caterpillar;
import model.GameBoard;
import model.GatherStrategy;
import model.Hive;
import model.ImageReg;
import model.MoveStrategy;
import model.Posie;

import org.junit.Test;

public class BeeWarriorTests {

	BeeWarrior bee = new BeeWarrior(new Point(0, 0), GameBoard.getBoardWithNoSound());
	Caterpillar cage = new Caterpillar(new Point(0, 0), bee.getGameBoard());
	Posie posie = new Posie(new Point(0, 0));
	Hive hive = new Hive(bee.getGameBoard());

	@Test
	public void testGetImage() {
		ImageReg i = ImageReg.getInstance();
		assertTrue(bee.getImage().equals(i.getImageFromStr("BeeWarrior00")));
		assertFalse(bee.getImage().equals(i.getImageFromStr("BeeWarriorS00")));
		bee.setSelected(true);
		assertTrue(bee.getImage().equals(i.getImageFromStr("BeeWarriorS00")));
	}

	// Starting point 1,1
	@Test
	public void testUpdate() {
		// Use the following commented out lines to help debug when this test
		// fails.
		// System.out.println(bee.getLocation().toString() +
		// bee.getObjectivePoint().toString());
		// System.out.println("\n\ntestBeeUpdate():\n\n");
		// System.out.println(System.currentTimeMillis());
		bee.setLocation(new Point(10, 10));
		bee.setStrategy(new MoveStrategy(bee), new Point(400, 10));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}
		// System.out.println("\n" + bee.getLocation().toString() +
		// bee.getObjectivePoint().toString());
		assertEquals(2, bee.getImageNumberForTesting());
		// System.out.println("Passed movement to right test");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(-400, 10));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}
		// System.out.println("\n" + bee.getLocation().toString() +
		// bee.getObjectivePoint().toString());
		assertEquals(6, bee.getImageNumberForTesting());
		// System.out.println("Passed movement to left test");

		bee.setLocation(new Point(0, 0));
		bee.setObjectivePoint(new Point(0, 0));
//		for (int i = 0; i < 50; i++) {
//			bee.update();
//		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(6, bee.getImageNumberForTesting());
		// System.out.println("Passed break out of for loop test so image stayed the same");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(10, 400));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(4, bee.getImageNumberForTesting());
		// System.out.println("Passed movement down test");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(10, -400));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(0, bee.getImageNumberForTesting());
		// System.out.println("Passed movement up test");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(400, 400));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(3, bee.getImageNumberForTesting());
		// System.out.println("Passed movement down and to the right test");

		bee.setLocation(new Point(10, 10));
		bee.setObjectivePoint(new Point(-400, -400));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(7, bee.getImageNumberForTesting());
		// System.out.println("Passed movement left and up test");

		bee.setLocation(new Point(0, 0));
		bee.setObjectivePoint(new Point(-400, 400));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(5, bee.getImageNumberForTesting());
		// System.out.println("Passed movement left and down test");

		bee.setLocation(new Point(0, 0));
		bee.setObjectivePoint(new Point(400, 0));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(2, bee.getImageNumberForTesting());
		// System.out.println("Passed movement right and up test");
		// System.out.println(System.currentTimeMillis());

		bee.setLocation(new Point(0, 0));
		bee.setObjectivePoint(new Point(400, -400));
		for (int i = 0; i < 50; i++) {
			bee.update();
		}// System.out.println("\n" + bee.getLocation().toString() +
			// bee.getObjectivePoint().toString());
		assertEquals(1, bee.getImageNumberForTesting());
		// System.out.println("Passed movement right and up test");
		// System.out.println(System.currentTimeMillis());

		bee.setStrategy(new GatherStrategy(bee), posie);
		assertTrue(bee.getStrategy() instanceof GatherStrategy);
		bee.update();
		assertFalse(bee.getStrategy() instanceof GatherStrategy);
		assertTrue(bee.getStrategy() instanceof MoveStrategy);
	}

	/*
	 * Tests getAttack, setAttack, upgradeAttack and attack
	 */

	@Test
	public void attackTest() {
		bee.setLocation(new Point(0, 0));
		assertEquals(2, bee.getAttack());
		bee.setAttack(4);
		assertEquals(4, bee.getAttack());
		bee.attack(cage);
		// assertTrue(bee.isDead());
		assertEquals(-1, cage.getHP());
		bee.upgradeAttack(-1);
		assertEquals(3, bee.getAttack());
		cage.setHP(10);
		bee.attack(cage);
		assertEquals(7, cage.getHP());
		assertTrue(bee.isDead());
	}

	@Test
	public void testGetHTMLDescription() {
		assertTrue(bee.getHTMLDescription().length() > 0);
	}
}
