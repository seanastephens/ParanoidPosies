package GUI;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import model.Thing;

public class MockGame implements GameInterface {
	public MockGame() {
		List<Thing> listofthings = new ArrayList<Thing>();
		listofthings.add(new MockThing(null));
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
		return null;
	}

}
