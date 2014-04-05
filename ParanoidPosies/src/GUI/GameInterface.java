package GUI;

import java.util.List;

import model.Thing;

public interface GameInterface {

	/**
	 * Returns a List of all the Things on the board.
	 */
	public List<Thing> getAllThingsOnBoard();

	/**
	 * Returns a List of all Things around a specified Point2D.
	 * 
	 * @param coords
	 *            - a Point2D specifying the Tile to get Things from.
	 * @return List of Things there.
	 */
	public List<Thing> getThingsBetween(int xLow, int yLow, int xHigh, int yHigh);

	/**
	 * Returns a reference to the game hive.
	 * 
	 * @return the Hive!
	 */
	public MockHive getHive();

	/**
	 * Update -- will be called ~20x/sec
	 */
	public void update();
}
