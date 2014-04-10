package GUI;

import java.util.Comparator;

import model.Bee;
import model.Caterpillar;
import model.Hive;
import model.Plant;
import model.Thing;

public class ThingPriorityComparator implements Comparator<Thing> {

	@Override
	public int compare(Thing a, Thing b) {
		int aNum = convertTypeToNumber(a);
		int bNum = convertTypeToNumber(b);
		return bNum - aNum;
	}

	private int convertTypeToNumber(Thing t) {
		if (t instanceof Bee) {
			return 0;
		} else if (t instanceof Plant) {
			return 1;
		} else if (t instanceof Caterpillar) {
			return 2;
		} else if (t instanceof Hive) {
			return 3;
		} else {
			throw new IllegalArgumentException("No ordering specified for " + t.getClass());
		}
	}

}
