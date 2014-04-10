package tests;

import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.List;

import model.Bee;
import model.Caterpillar;
import model.GameBoard;
import model.Posie;
import model.Thing;

import org.junit.Test;

public class GameBoardTest {

	Point origin = new Point(0, 0);

	@Test
	public void getClosestTest() {
		GameBoard g = new GameBoard(false);

		Thing t = g.getClosest(Posie.class, origin);
		assertTrue(t instanceof Posie);
		t = g.getClosest(Bee.class, origin);
		assertTrue(t instanceof Bee);
		t = g.getClosest(Caterpillar.class, origin);
		assertTrue(t == g.getHive());
		/*
		 * Eventually, I think it makes sense to change to returning null from
		 * the GameBoard method, and each strategy can decide what it wants to
		 * do after that.
		 * 
		 * assertTrue(t == null);
		 */
	}

	@Test
	public void getClosestReturnsClosest() {
		GameBoard g = new GameBoard(false);

		Thing closest = g.getClosest(Posie.class, origin);
		assertTrue(closest instanceof Posie);

		List<Thing> allThings = g.getAllThingsOnBoard();
		for (Thing t : allThings) {
			if (t instanceof Posie) {
				assertTrue(origin.distance(t.getLocation()) >= origin.distance(closest
						.getLocation()));
			}
		}
	}

	@Test
	public void getRandomTest() {
		GameBoard g = new GameBoard(false);

		Thing t = g.getRandom(Posie.class);
		assertTrue(t instanceof Posie);
		t = g.getRandom(Bee.class);
		assertTrue(t instanceof Bee);
		t = g.getRandom(Caterpillar.class);
		assertTrue(t == g.getHive());
		/*
		 * Eventually, I think it makes sense to change to returning null from
		 * the GameBoard method, and each strategy can decide what it wants to
		 * do after that.
		 * 
		 * assertTrue(t == null);
		 */
	}

}
