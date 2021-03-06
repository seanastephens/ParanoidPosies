package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageReg {

	public static int FLOWER_SIZE = 75;
	public static int HIVE_GRAPHIC_SIZE = 150;
	public static int BEE_SIZE = 40;

	private static ImageReg instance = null;
	private static Map<String, Image> images;
	private static String base = "images/";

	private ImageReg() {
		images = new HashMap<String, Image>();

		/*
		 * Add images here.
		 */
		images.put("Bee", getImage("bee.png"));
		images.put("Hive", getImage("beehive.png").getScaledInstance(200, 200, 0));
		images.put("GrassTile", getImage("grasstile.png"));
		images.put("Caterpillar", getImage("caterpillar.png"));
		images.put("HITLER", getImage("HITLER.png"));
		images.put("BeeSelected", getImage("bee_selected.png"));
		images.put("SegmentedCaterpillarHead", getImage("segmented_caterpillar_head.png"));
		images.put("SegmentedCaterpillarBody", getImage("segmented_caterpillar_body.png"));
		images.put("DeadBees", getImage("dead_bees.jpg").getScaledInstance(300, 300, 0));
		images.put("SegmentedCaterpillarHead4", getImage("segmented_caterpillar_head.png"));
		images.put("SegmentedCaterpillarHead6", getImage("segmented_caterpillar_head_left.png"));
		images.put("SegmentedCaterpillarHead5", getImage("segmented_caterpillar_head_leftdown.png"));
		images.put("SegmentedCaterpillarHead7", getImage("segmented_caterpillar_head_up.png"));
		images.put("SegmentedCaterpillarHead2", getImage("segmented_caterpillar_head_right.png"));
		images.put("SegmentedCaterpillarHead3",
				getImage("segmented_caterpillar_head_rightdown.png"));
		images.put("SegmentedCaterpillarHead1", getImage("segmented_caterpillar_head_rightup.png"));
		images.put("SegmentedCaterpillarHead0", getImage("segmented_caterpillar_head_up.png"));
		images.put("SegmentedCaterpillarBody", getImage("segmented_caterpillar_body.png"));
		images.put(
				"Hive0",
				getImage("beehive_empty.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put(
				"Hive1",
				getImage("beehive_full_1.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put(
				"Hive2",
				getImage("beehive_full_2.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put(
				"Hive3",
				getImage("beehive_full_3.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put(
				"Hive4",
				getImage("beehive_full_4.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put(
				"Hive5",
				getImage("beehive_full_5.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put(
				"Hive6",
				getImage("beehive_full_6.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put(
				"Hive6A",
				getImage("beehive_full_6c.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put(
				"Hive6B",
				getImage("beehive_full_6d.png").getScaledInstance(HIVE_GRAPHIC_SIZE,
						HIVE_GRAPHIC_SIZE, 0));
		images.put("JustPlanted",
				getImage("dirt.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE * 7 / 10, 0));
		images.put("Seedling",
				getImage("seedling.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE, 0));
		images.put("Flower0",
				getImage("flower_blue.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE, 0));
		images.put("Flower1",
				getImage("flower_fat_cage.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE, 0));
		images.put("Flower2",
				getImage("flower_green.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE, 0));
		images.put("Flower3",
				getImage("flower_red.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE, 0));
		images.put("Flower4",
				getImage("flower_violet.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE, 0));
		images.put("Flower5",
				getImage("flower_yellow.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE, 0));
		images.put("DeadFlower",
				getImage("flower_dead.png").getScaledInstance(FLOWER_SIZE, FLOWER_SIZE, 0));

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 3; j++) {
				String beePath = "bee" + i + "" + j + ".png";
				String beeSPath = "beeS" + i + "" + j + ".png";
				String warPath = "beeWarrior" + i + "" + j + ".png";
				String warSPath = "beeWarriorS" + i + "" + j + ".png";
				images.put("Bee" + i + "" + j,
						getImage(beePath).getScaledInstance(BEE_SIZE, BEE_SIZE, 0));
				images.put("BeeS" + i + "" + j,
						getImage(beeSPath).getScaledInstance(BEE_SIZE, BEE_SIZE, 0));
				images.put("BeeWarrior" + i + "" + j,
						getImage(warPath).getScaledInstance(BEE_SIZE, BEE_SIZE, 0));
				images.put("BeeWarriorS" + i + "" + j,
						getImage(warSPath).getScaledInstance(BEE_SIZE, BEE_SIZE, 0));
			}
		}

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
