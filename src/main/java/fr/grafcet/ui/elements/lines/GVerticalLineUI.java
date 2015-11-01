package fr.grafcet.ui.elements.lines;

import fr.grafcet.ui.elements.GElementUI;
import javafx.scene.shape.Line;

/** Transition d'un grafcet */
public class GVerticalLineUI extends GElementUI {

    public GVerticalLineUI(int gridRowIndex, int gridColumnIndex) {
	super(gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	Line l = new Line(EDGE_DISTANCE, 0, EDGE_DISTANCE, EDGE_DISTANCE * 2);
	getChildren().add(l);
    }
}
