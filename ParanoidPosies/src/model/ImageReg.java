package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageReg {

	private static ImageReg instance = null;
	private static Map<String, Image> images;

	private ImageReg() {
		images = new HashMap<String, Image>();

		/*
		 * Add images here.
		 */
		images.put("Bee", getImage("bee.png"));
		images.put("Hive", getImage("beehive.png").getScaledInstance(200, 200, 0));
		images.put("GrassTile", getImage("bee.png"));
		images.put("Caterpillar", getImage("caterpillar.jpg"));
		images.put("HITLER", getImage("HITLER.gif"));
		/*
		 * DON'T TOUCH BELOW HERE!
		 */
	}

	private static String base = "images/";

	private Image getImage(String path) {
		Image temp = null;
		try {
			temp = ImageIO.read(new File(base + path));
		} catch (IOException e) {
			System.out.println("Image read error: " + base + path);
			System.exit(1);
		}
		return temp;
	}

	public static ImageReg getInstance() {
		if (instance == null) {
			instance = new ImageReg();
		}
		return instance;
	}

	public Image getImageFromStr(String name) {
		if (!images.containsKey(name)) {
			throw new IllegalArgumentException();
		}
		return images.get(name);
	}
}
