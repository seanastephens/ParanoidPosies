package GUI;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.ImageReg;

public class TileManager {

	private Image tileImage;
	private int width;
	private int height;

	public TileManager() {
		tileImage = ImageReg.getInstance().getImageFromStr("GrassTile");
		width = tileImage.getWidth(null);
		height = tileImage.getHeight(null);
	}

	public List<BackgroundTile> getTiles(Point viewCenter) {
		List<BackgroundTile> tiles = new ArrayList<BackgroundTile>();

		int startx = viewCenter.x;
		int starty = viewCenter.y;
		while (startx > viewCenter.x - PPGUI.WINDOW_WIDTH / 2) {
			startx -= width;
		}
		while (starty > viewCenter.y - PPGUI.WINDOW_HEIGHT / 2) {
			starty -= height;
		}

		for (int i = startx; i < PPGUI.WINDOW_WIDTH + startx; i += width) {
			for (int j = starty; j < PPGUI.WINDOW_HEIGHT + starty; j += height) {
				tiles.add(new BackgroundTile(tileImage, new Point(i, j)));
			}
		}

		return tiles;
	}
}
