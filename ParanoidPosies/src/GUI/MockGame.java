package GUI;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import model.Thing;

public class MockGame implements GameInterface {

	List<Thing> listofthings = new ArrayList<Thing>();
	private MockHive hive;

	public MockGame() {
		listofthings.add(new MockThing(null));
		hive = new MockHive();
	}

	@Override
	public List<Thing> getAllThingsOnBoard() {
		// TODO Auto-generated method stub
		return null;
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
