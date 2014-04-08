package tests;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Point;

import model.Bee;
import model.GameBoard;
import model.ImageReg;
import model.MoveStrategy;

import org.junit.Test;

public class BeeTests {
	
	Bee bee = new Bee(new Point(1,1), new GameBoard());
	
	/*
	 * Tests getBeeName, getName, setName, and uses buildBeeNamesList
	 */
	@Test
	public void testBeeNames(){
		System.out.println("\n\ntestBeeNames():\n\n");
		for(int i = 0; i < 10; i++){
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
	public void testBeeSpeed(){
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
	public void testBeeHP(){
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
	public void testGetImage(){
		ImageReg i = ImageReg.getInstance();
		assertTrue(bee.getImage().equals(i.getImageFromStr("Bee00")));
		assertFalse(bee.getImage().equals(i.getImageFromStr("BeeS00")));
	}
	
	@Test
	public void testSetSelected(){
		boolean f = false;
		boolean t = true;
		assertTrue(!bee.getSelected());
		bee.setSelected(t);
		assertTrue(bee.getSelected());
		bee.setSelected(f);
		assertTrue(!bee.getSelected());
		assertFalse(bee.getSelected());
	}
	
	//Starting point 1,1
	@Test
	public void testUpdate(){
		//Use the following commented out lines to help debug when this test fails.
		//System.out.println(bee.getLocation().toString() + bee.getObjectivePoint().toString());
		System.out.println("\n\ntestBeeUpdate():\n\n");
		
		bee.setLocation(new Point(10,10));
		bee.setStrategy(new MoveStrategy(bee, bee.getGameBoard()), new Point(20,10));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(2, bee.getImageNumberForTesting());
		//System.out.println("Passed movement to right test");
		
		bee.setLocation(new Point(10,10));
		bee.setObjectivePoint(new Point(0,10));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(6, bee.getImageNumberForTesting());
		//System.out.println("Passed movement to left test");
		
		bee.setLocation(new Point(0,0));
		bee.setObjectivePoint(new Point(0,0));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(6, bee.getImageNumberForTesting());
		//System.out.println("Passed break out of for loop test so image stayed the same");
		
		bee.setLocation(new Point(10,10));
		bee.setObjectivePoint(new Point(10,20));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(4, bee.getImageNumberForTesting());
		//System.out.println("Passed movement down test");
		
		bee.setLocation(new Point(10,10));
		bee.setObjectivePoint(new Point(10,0));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(0, bee.getImageNumberForTesting());
		//System.out.println("Passed movement up test");
		
		bee.setLocation(new Point(10,10));
		bee.setObjectivePoint(new Point(20,20));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(3, bee.getImageNumberForTesting());
		//System.out.println("Passed movement down and to the right test");
		
		bee.setLocation(new Point(10,10));
		bee.setObjectivePoint(new Point(0,0));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(7, bee.getImageNumberForTesting());
		//System.out.println("Passed movement left and up test");
		
		bee.setLocation(new Point(10,10));
		bee.setObjectivePoint(new Point(0,20));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(5, bee.getImageNumberForTesting());
		//System.out.println("Passed movement left and down test");
		
		bee.setLocation(new Point(10,10));
		bee.setObjectivePoint(new Point(20,0));
		bee.update();
		//System.out.println("\n" + bee.getLocation().toString() + bee.getObjectivePoint().toString());
		assertEquals(1, bee.getImageNumberForTesting());
		//System.out.println("Passed movement right and up test");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
