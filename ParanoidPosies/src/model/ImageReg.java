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

		try {
			/*
			 * Add images here.
			 */
			images.put("Bee", ImageIO.read(new File("images/bee.png")));
			images.put("Hive",
					ImageIO.read(new File("images/thisIsAPosie.jpg")));
			/*
			 * DON'T TOUCH BELOW HERE!
			 */

		} catch (IOException e) {
			System.out.println("image error");
		}
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
