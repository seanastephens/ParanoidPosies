package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import model.Bee;
import model.GameBoard;

import org.junit.Test;

public class BeeTests {
	
	/*
	 * Tests getBeeName, getName, setName, and uses buildBeeNamesList
	 */
	@Test
	public void testBeeNames(){
		System.out.println("\n\ntestBeeNames():\n\n");
		Bee bee = new Bee(new Point(1,1), new GameBoard());
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
		Bee bee = new Bee(new Point(1,1), new GameBoard());
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
		Bee bee = new Bee(new Point(1,1), new GameBoard());
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
	
}
