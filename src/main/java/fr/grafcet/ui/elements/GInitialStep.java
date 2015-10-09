package fr.grafcet.ui.elements;

import java.awt.Graphics;

import fr.grafcet.data.models.GInitialStepModel;

/** Etape initiale d'un grafcet */
public class GInitialStep extends GElementUI {

	private GInitialStepModel data;

	public void paint(Graphics g) {

		// les deux carrés
		g.drawRect((int) getLeftUpperCornerX(), (int) getLeftUpperCornerY(),
				EDGE_DISTANCE * 2, EDGE_DISTANCE * 2);
		g.drawRect((int) getLeftUpperCornerX() - 5,
				(int) getLeftUpperCornerY() - 5, EDGE_DISTANCE * 2 - 10,
				EDGE_DISTANCE * 2 - 10);
		// le nom de l'étape initiale
		g.drawString(data.getName(), (int) getOrigin().getX() - 10,
				(int) getOrigin().getY());
	}
}
