package GUI;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.ImageReg;

public class TileManager {

	private Image tileImage;
	private int tileWidth;
	private int tileHeight;

	public TileManager() {
		tileImage = ImageReg.getInstance().getImageFromStr("GrassTile");
		tileWidth = tileImage.getWidth(null);
		tileHeight = tileImage.getHeight(null);
	}

	public List<BackgroundTile> getTiles(Point viewCenter) {
		List<BackgroundTile> tiles = new ArrayList<BackgroundTile>();

		int bufferedLeftBound = computeLeftMostImageBoundary(viewCenter);
		int bufferedTopBound = computeTopImageBoundary(viewCenter);
		int bufferedRightBound = PPGUI.WINDOW_WIDTH + bufferedLeftBound;
		int bufferedBottomBound = PPGUI.WINDOW_HEIGHT + bufferedTopBound;

		for (int i = bufferedLeftBound; i <= bufferedRightBound; i += tileWidth) {
			for (int j = bufferedTopBound; j <= bufferedBottomBound; j += tileHeight) {
				tiles.add(new BackgroundTile(tileImage, new Point(i, j)));
			}
		}
		return tiles;
	}

	private int computeLeftMostImageBoundary(Point viewCenter) {
		int boundary = 0;
		while (boundary < viewCenter.x - PPGUI.WINDOW_WIDTH / 2 - tileWidth) {
			boundary += tileWidth;
		}
		while (boundary > viewCenter.x - PPGUI.WINDOW_WIDTH / 2 - tileWidth) {
			boundary -= tileWidth;
		}
		return boundary;
	}

	private int computeTopImageBoundary(Point viewCenter) {
		int boundary = 0;
		while (boundary < viewCenter.y - PPGUI.WINDOW_HEIGHT / 2 - tileHeight) {
			boundary += tileHeight;
		}
		while (boundary > viewCenter.y - PPGUI.WINDOW_HEIGHT / 2 - tileHeight) {
			boundary -= tileHeight;
		}
		return boundary;
	}
}
