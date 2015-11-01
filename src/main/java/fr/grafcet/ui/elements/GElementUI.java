package fr.grafcet.ui.elements;

import javafx.scene.layout.Pane;

/**
 * classe de base permettat le dessin d'un graphcet.<br>
 * Dessine sur un carré de 40x40, le poinnt d'origine est positionné au centre
 * de l'élément.
 */
public abstract class GElementUI extends Pane {

    private static final String CSS_ELEMENT_ID = "grafcet-element";
    private int gridRowIndex, gridColumnIndex;

    protected final double EDGE_DISTANCE = 20D;

    public GElementUI(int gridRowIndex, int gridColumnIndex) {
	super();
	setId(CSS_ELEMENT_ID);
	this.gridRowIndex = gridRowIndex;
	this.gridColumnIndex = gridColumnIndex;
    }

    public int getGridRowIndex() {
	return gridRowIndex;
    }

    public int getGridColumnIndex() {
	return gridColumnIndex;
    }

    public void setGridRowIndex(int gridRowIndex) {
	this.gridRowIndex = gridRowIndex;
    }

    public void setGridColumnIndex(int gridColumnIndex) {
	this.gridColumnIndex = gridColumnIndex;
    }

    /** dessine l'element */
    public abstract void initShape();
}
