package fr.grafcet.ui.elements;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

/** Etape d'un grafcet */
public class GStepUI extends GAbstractStepUI {

    private GStepActionUI action;

    public GStepUI(int gridRowIndex, int gridColumnIndex) {
	super(gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	double endOfRectangle = EDGE_DISTANCE * 2 - 8;
	Polyline pl = new Polyline(8, 8, endOfRectangle, 8, endOfRectangle, endOfRectangle, 8, endOfRectangle, 8, 8);
	getChildren().add(pl);

	Line l = new Line(EDGE_DISTANCE, 0, EDGE_DISTANCE, 8);
	getChildren().add(l);
	Line l1 = new Line(EDGE_DISTANCE, endOfRectangle, EDGE_DISTANCE, EDGE_DISTANCE * 2);
	getChildren().add(l1);
	Line l2 = new Line(endOfRectangle, EDGE_DISTANCE, EDGE_DISTANCE * 2, EDGE_DISTANCE);
	getChildren().add(l2);

	Text t = new Text(12, EDGE_DISTANCE + 5, getName());
	getChildren().add(t);
    }

    public GStepActionUI getAction() {
	return action;
    }

    public void setAction(GStepActionUI action) {
	this.action = action;
    }
}
