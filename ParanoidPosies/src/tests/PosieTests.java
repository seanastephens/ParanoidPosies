package src.tests;

import src.GUI.PPGUI;
import src.model.GrowthState;
import src.model.Posie;

public class PosieTests {

	private static Posie p;

	@Test
	public void testGrow() {
		p = new Posie(new Point(1, 1));

		assertEquals(0, p.getNectar());
		assertEquals(GrowthState.JustPlanted, p.currentState);

		for (int i = 0; i < Posie.posie_time_to_seedling; i++) {
			p.update();
		}

		assertEquals(GrowthState.Seedling, p.currentState);
		assertEquals(0, p.getNectar());

		for (int i = 0; i < Posie.posie_time_to_flower - Posie.posie_time_to_seedling; i++) {
			p.update();
		}

		assertEquals(GrowthState.Flower, p.currentState);
		assertEquals(1, p.getNectar());

		// Increase by two nectar
		for (int i = 0; i < 2 * PPGUI.UPDATES_PER_SEC; i++) {
			p.update();
		}

		for (int i = 0; i < Posie.posie_lifespan - Posie.posie_time_to_flower; i++) {
			p.update();
		}

		assertEquals(GrowthState.DeadFlower, p.currentState);
		assertEquals(0, p.currentNectar);

	}

}
