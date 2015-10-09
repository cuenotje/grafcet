package fr.grafcet.ui.elements;

import java.awt.Graphics;

import javafx.geometry.Point2D;

/**
 * classe de base permettat le dessin d'un graphcet.<br>
 * Dessine sur un carré de 40x40, le poinnt d'origine est positionné au centre
 * de l'élément.
 */
public abstract class GElementUI {

	protected final int EDGE_DISTANCE = 20;

	/** centre de l'element graphique */
	private Point2D origin;

	/** positionne l'origine de l'element (son centre) */
	public void setOrigin(Point2D origin) {
		this.origin = origin;
	}

	/** centre de l'élément graphique */
	public Point2D getOrigin() {
		return origin;
	}

	/** dessine l'element */
	public abstract void paint(Graphics g);

	/** test si le point est dans cet élément */
	public boolean isInElement(Point2D point) {
		boolean result = true;
		// test sur abscisse
		if (point.getX() < (origin.getX() - EDGE_DISTANCE) && //
				point.getX() > (origin.getX() - EDGE_DISTANCE)) {
			result = false;
		}
		// test sur ordonné
		if (point.getY() < (origin.getY() - EDGE_DISTANCE) && //
				point.getY() > (origin.getY() - EDGE_DISTANCE)) {
			result = false;
		}
		return result;
	}

	/** Renvoi l'abscisse du coin en haut à gauche de l'élément */
	public double getLeftUpperCornerX() {
		return origin.getX() - EDGE_DISTANCE;
	}

	/** Renvoi l'ordonné du coin en haut à gauche de l'élément */
	public double getLeftUpperCornerY() {
		return origin.getY() - EDGE_DISTANCE;
	}
}
