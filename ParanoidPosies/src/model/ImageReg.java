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
		images.put("GrassTile", getImage("grasstile.png"));
		images.put("Caterpillar", getImage("caterpillar.png"));
		images.put("HITLER", getImage("HITLER.gif"));
		images.put("BeeSelected", getImage("bee_selected.png"));
		images.put("SegmentedCaterpillarHead", getImage("segmented_caterpillar_head.png"));
		images.put("SegmentedCaterpillarBody", getImage("segmented_caterpillar_body.png"));
		images.put("DeadBees", getImage("dead_bees.jpg").getScaledInstance(300, 300, 0));
		images.put("SegmentedCaterpillarHead", getImage("segmented_caterpillar_head.png"));
		images.put("SegmentedCaterpillarHeadLeft", getImage("segmented_caterpillar_head_left.png"));
		images.put("SegmentedCaterpillarHeadLeftDown",
				getImage("segmented_caterpillar_head_leftdown.png"));
		images.put("SegmentedCaterpillarHeadLeftUp", getImage("segmented_caterpillar_head_up.png"));
		images.put("SegmentedCaterpillarHeadRight",
				getImage("segmented_caterpillar_head_right.png"));
		images.put("SegmentedCaterpillarHeadRightDown",
				getImage("segmented_caterpillar_head_rightdown.png"));
		images.put("SegmentedCaterpillarHeadRightUp",
				getImage("segmented_caterpillar_head_rightup.png"));
		images.put("SegmentedCaterpillarHeadUp", getImage("segmented_caterpillar_head_up.png"));
		images.put("SegmentedCaterpillarBody", getImage("segmented_caterpillar_body.png"));
		images.put("Hive0", getImage("beehive_empty.png").getScaledInstance(200, 200, 0));
		images.put("Hive1", getImage("beehive_full_1.png").getScaledInstance(200, 200, 0));
		images.put("Hive2", getImage("beehive_full_2.png").getScaledInstance(200, 200, 0));
		images.put("Hive3", getImage("beehive_full_3.png").getScaledInstance(200, 200, 0));
		images.put("Hive4", getImage("beehive_full_4.png").getScaledInstance(200, 200, 0));
		images.put("Hive5", getImage("beehive_full_5.png").getScaledInstance(200, 200, 0));
		images.put("Hive6", getImage("beehive_full_6.png").getScaledInstance(200, 200, 0));
		images.put("Hive6A", getImage("beehive_full_6c.png").getScaledInstance(200, 200, 0));
		images.put("Hive6B", getImage("beehive_full_6d.png").getScaledInstance(200, 200, 0));
		images.put("JustPlanted", getImage("dirt.png"));
		images.put("Seedling", getImage("seedling.png"));
		images.put("Flower0", getImage("flower_blue.png"));
		images.put("Flower1", getImage("flower_fat_cage.png"));
		images.put("Flower2", getImage("flower_green.png"));
		images.put("Flower3", getImage("flower_red.png"));
		images.put("Flower4", getImage("flower_violet.png"));
		images.put("Flower5", getImage("flower_yellow.png"));
		images.put("DeadFlower", getImage("flower_dead.png"));

		// Filler images
		images.put("TotallyAPosie", getImage("totallyAPosie.png"));
		images.put("thisIsAPosie", getImage("thisIsAPosie.jpg"));
		images.put("forRealThoughItsAPosie", getImage("forRealThoughItsAPosie.png"));
		images.put("ISwearToGodThisOneIsAnHonestToGodLegitimatePosie",
				getImage("ISwearToGodThisOneIsAnHonestToGodLegitimatePosie.png"));
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
