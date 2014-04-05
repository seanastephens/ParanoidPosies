package GUI;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import model.Thing;

public class MockGame implements GameInterface {

	List<Thing> listofthings = new ArrayList<Thing>();
	private MockHive hive;

	public MockGame() {
		listofthings.add(new MockThing(new Point(45, 45)));
		listofthings.add(new MockThing(new Point(55, 55)));
		hive = new MockHive();
	}

	@Override
	public List<Thing> getAllThingsOnBoard() {
		// TODO Auto-generated method stub
		return listofthings;
	}

	@Override
	public List<Thing> getThingsAtPoint(Point2D coords) {
		// TODO Auto-generated method stub
		return null;
	}

	public MockHive getHive() {
		return hive;
	}

	@Override
	public void update() {
		hive.addHoney(1);
	}
}
