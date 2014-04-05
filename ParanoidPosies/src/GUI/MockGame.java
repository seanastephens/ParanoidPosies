package GUI;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Thing;

public class MockGame implements GameInterface {

	List<Thing> listofthings = new ArrayList<Thing>();
	private MockHive hive;

	public MockGame() {
		listofthings.add(new MockThing(new Point(45, 45)));
		listofthings.add(new MockThing(new Point(47, 47)));
		listofthings.add(new MockThing(new Point(49, 49)));
		listofthings.add(new MockThing(new Point(51, 51)));
		listofthings.add(new MockThing(new Point(53, 53)));
		listofthings.add(new MockThing(new Point(55, 55)));
		hive = new MockHive();
	}

	@Override
	public List<Thing> getAllThingsOnBoard() {
		// TODO Auto-generated method stub
		return listofthings;
	}

	@Override
	public List<Thing> getThingsAtPoint(Point coords) {
		List<Thing> ret = new ArrayList<Thing>();
		for (Thing t : listofthings) {
			if (t.getLocation().equals(coords)) {
				ret.add(t);
			}
		}
		return ret;
	}

	public MockHive getHive() {
		return hive;
	}

	@Override
	public void update() {
		hive.addHoney(1);
	}
}
