package GUI;

import java.awt.Image;
import java.awt.Point;

public class BackgroundTile {

	private Image image;
	private Point point;

	public BackgroundTile(Image image, Point point) {
		this.image = image;
		this.point = point;
	}

	public Image getImage() {
		return image;
	}

	public Point getLocation() {
		return point;
	}

}
