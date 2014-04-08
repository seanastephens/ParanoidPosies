package guitests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import GUI.BackgroundTile;
import GUI.PPGUI;
import GUI.TileManager;

public class TileManagerTest {

	TileManager tileManager = new TileManager();

	private int computeExpectedNumberOfTiles(Image tile) {
		int tileWidth = tile.getWidth(null);
		int tileHeight = tile.getHeight(null);

		// There will always be at least one tile
		int screenHeightInTiles = Math.max(PPGUI.WINDOW_HEIGHT / tileHeight, 1);
		int screenWidthInTiles = Math.max(PPGUI.WINDOW_WIDTH / tileWidth, 1);

		return screenHeightInTiles * screenWidthInTiles;
	}

	@Test
	public void testTileManagerWithCenteredView() {
		Point view = new Point(0, 0);
		List<BackgroundTile> tiles = tileManager.getTiles(view);
		int expectedNumberOfTiles = computeExpectedNumberOfTiles(tiles.get(0).getImage());

		assertEquals(expectedNumberOfTiles, tiles.size());
	}

	@Test
	public void testTileManagerWithNegativeView() {
		Point view = new Point(-2500, -2500);
		List<BackgroundTile> tiles = tileManager.getTiles(view);
		int expectedNumberOfTiles = computeExpectedNumberOfTiles(tiles.get(0).getImage());

		assertEquals(expectedNumberOfTiles, tiles.size());
	}

	@Test
	public void testTileManagerWithPositiveView() {
		Point view = new Point(2500, 2500);
		List<BackgroundTile> tiles = tileManager.getTiles(view);
		int expectedNumberOfTiles = computeExpectedNumberOfTiles(tiles.get(0).getImage());

		assertEquals(expectedNumberOfTiles, tiles.size());
	}

	private class TileComparator implements Comparator<BackgroundTile> {
		public int compare(BackgroundTile a, BackgroundTile b) {
			if (a.getLocation().x < b.getLocation().x) {
				return -1;
			} else if (a.getLocation().x > b.getLocation().x) {
				return 1;
			}
			if (a.getLocation().y < b.getLocation().y) {
				return -1;
			} else if (a.getLocation().y > b.getLocation().y) {
				return 1;
			}
			return 0;
		}
	}

	private Point lowestImagePoint(List<BackgroundTile> tiles) {
		return Collections.min(tiles, new TileComparator()).getLocation();
	}

	@Test
	public void testTilesStartOffScreen() {
		Point view = new Point(0, 0);
		List<BackgroundTile> tiles = tileManager.getTiles(view);
		Point upperLeft = lowestImagePoint(tiles);

		assertTrue(upperLeft.x <= view.x - PPGUI.WINDOW_WIDTH / 2);
		assertTrue(upperLeft.y <= view.y - PPGUI.WINDOW_HEIGHT / 2);
	}

	private Point highestImagePoint(List<BackgroundTile> tiles) {
		return Collections.max(tiles, new TileComparator()).getLocation();
	}

	@Test
	public void testTilesStartOnScreen() {
		Point view = new Point(0, 0);
		List<BackgroundTile> tiles = tileManager.getTiles(view);
		Point bottomRight = highestImagePoint(tiles);

		assertTrue(bottomRight.x <= view.x + PPGUI.WINDOW_WIDTH / 2);
		assertTrue(bottomRight.y <= view.y + PPGUI.WINDOW_HEIGHT / 2);
	}
}
