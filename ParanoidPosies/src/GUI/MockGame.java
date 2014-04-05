package GUI;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Bee;
import model.Hive;
import model.Thing;

public class MockGame implements GameInterface {

	List<Thing> listofthings = new ArrayList<Thing>();
	private Hive hive;

	public MockGame() {
		listofthings.add(new Bee(new Point(2400, 2400)));
		listofthings.add(new Bee(new Point(2600, 2600)));
		hive = new Hive();
	}

	@Override
	public List<Thing> getAllThingsOnBoard() {
		// TODO Auto-generated method stub
		return listofthings;
	}

	public List<Thing> getThingsBetween(int xlow, int ylow, int xhigh, int yhigh) {
		List<Thing> ret = new ArrayList<Thing>();
		for (Thing t : listofthings) {
			int x = t.getLocation().x;
			int y = t.getLocation().y;
			if (x < xhigh && x > xlow && y < yhigh && y > ylow) {
				ret.add(t);
			}
		}
		return ret;
	}

	public Hive getHive() {
		return hive;
	}

	@Override
	public void update() {
		hive.updateNector(1);
		for (Thing t : listofthings) {
			t.update();
		}
	}
}
