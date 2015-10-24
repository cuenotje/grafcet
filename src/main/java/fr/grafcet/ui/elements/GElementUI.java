package fr.grafcet.ui.elements;

import fr.grafcet.data.models.IGElementModel;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

/**
 * classe de base permettat le dessin d'un graphcet.<br>
 * Dessine sur un carré de 40x40, le poinnt d'origine est positionné au centre
 * de l'élément.
 */
public abstract class GElementUI extends Pane {

    private static final String CSS_ELEMENT_ID = "grafcet-element";
    private IGElementModel data;
    private int gridRowIndex, gridColumnIndex;

    protected final double EDGE_DISTANCE = 20D;

    /** centre de l'element graphique */
    private Point2D origin;

    public GElementUI(IGElementModel data, int gridRowIndex, int gridColumnIndex) {
	super();
	setId(CSS_ELEMENT_ID);
	this.gridRowIndex = gridRowIndex;
	this.gridColumnIndex = gridColumnIndex;
	this.data = data;
	initShape();
    }

    public int getGridRowIndex() {
	return gridRowIndex;
    }

    public int getGridColumnIndex() {
	return gridColumnIndex;
    }

    /** positionne l'origine de l'element (son centre) */
    public void setOrigin(Point2D origin) {
	this.origin = origin;
    }

    /** centre de l'élément graphique */
    public Point2D getOrigin() {
	return origin;
    }

    /** dessine l'element */
    public abstract void initShape();

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

    public IGElementModel getModel() {
	return data;
    }
}
