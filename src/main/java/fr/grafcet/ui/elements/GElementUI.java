package fr.grafcet.ui.elements;

import java.awt.Graphics;
import java.awt.Point;

public abstract class GElementUI {

	/** centre de l'element graphique */
	private Point origin;

	/** positionne l'origine de l'element (son centre) */
	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	/** dessine l'element */
	public abstract void paint(Graphics g);
}
