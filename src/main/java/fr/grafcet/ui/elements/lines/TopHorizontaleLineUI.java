package fr.grafcet.ui.elements.lines;

import fr.grafcet.ui.elements.GElementUI;
import javafx.scene.shape.Line;

/** Transition d'un grafcet */
public class TopHorizontaleLineUI extends GElementUI {

    public TopHorizontaleLineUI(int gridRowIndex, int gridColumnIndex) {
	super(gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	Line l = new Line(0, 0, EDGE_DISTANCE * 2, 0);
	getChildren().add(l);
    }
}
