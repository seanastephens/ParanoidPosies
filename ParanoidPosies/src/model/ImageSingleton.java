package model;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageSingleton {

	private static ImageSingleton instance = null;
	private static Map<String, Image> images;

	private ImageSingleton() {
		images = new HashMap<String, Image>();
	}

	public static ImageSingleton getInstance() {
		if (instance == null) {
			instance = new ImageSingleton();
		}
		return instance;
	}

	public Image getImageFromString(String name) {
		if (!images.containsKey(name)) {
			throw new IllegalArgumentException();
		}
		return images.get(name);
	}
}
