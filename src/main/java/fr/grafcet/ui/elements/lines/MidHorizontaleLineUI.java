package fr.grafcet.ui.elements.lines;

import fr.grafcet.ui.elements.GElementUI;
import javafx.scene.shape.Line;

/** Transition d'un grafcet */
public class MidHorizontaleLineUI extends GElementUI {

    public MidHorizontaleLineUI(int gridRowIndex, int gridColumnIndex) {
	super(gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	Line l = new Line(0, EDGE_DISTANCE, EDGE_DISTANCE * 2, EDGE_DISTANCE);
	getChildren().add(l);
    }
}
