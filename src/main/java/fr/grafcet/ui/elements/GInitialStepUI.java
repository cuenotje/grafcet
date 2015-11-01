package fr.grafcet.ui.elements;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

/** Etape initiale d'un grafcet */
public class GInitialStepUI extends GAbstractStepUI {

    private String projectName;

    public GInitialStepUI(int gridRowIndex, int gridColumnIndex) {
	super(gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	double endOfFirstRectangle = EDGE_DISTANCE * 2 - 2;
	double endOfSecondRectangle = EDGE_DISTANCE * 2 - 8;
	Polyline pl = new Polyline(2, 2, endOfFirstRectangle, 2, endOfFirstRectangle, endOfFirstRectangle, 2, endOfFirstRectangle, 2, 2);
	getChildren().add(pl);
	Polyline pl2 = new Polyline(8, 8, endOfSecondRectangle, 8, endOfSecondRectangle, endOfSecondRectangle, 8, endOfSecondRectangle, 8, 8);
	getChildren().add(pl2);

	Line l = new Line(EDGE_DISTANCE, endOfFirstRectangle, EDGE_DISTANCE, EDGE_DISTANCE * 2);
	getChildren().add(l);

	Text t = new Text(12, EDGE_DISTANCE + 5, getName());
	getChildren().add(t);
    }

    public String getProjectName() {
	return projectName;
    }

    public void setProjectName(String projectName) {
	this.projectName = projectName;
    }
}
