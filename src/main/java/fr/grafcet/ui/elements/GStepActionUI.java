package fr.grafcet.ui.elements;

import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

/** Action d'une étape d'un grafcet */
public class GStepActionUI extends GElementUI {

    private String action;

    public GStepActionUI(int gridRowIndex, int gridColumnIndex) {
	super(gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	double endOfRectangle = EDGE_DISTANCE * 2;
	Polyline pl = new Polyline(0, 0, endOfRectangle, 0, endOfRectangle, endOfRectangle, 0, endOfRectangle, 0, 0);
	getChildren().add(pl);

	Text t = new Text(10, EDGE_DISTANCE + 5, getAction());
	getChildren().add(t);
    }

    public String getAction() {
	return action;
    }

    public void setAction(String action) {
	this.action = action;
    }
}
