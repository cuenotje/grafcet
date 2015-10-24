package fr.grafcet.ui.elements;

import fr.grafcet.data.models.StepModel;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

/** Etape d'un grafcet */
public class GStepUI extends GElementUI {

    public GStepUI(StepModel data, int gridRowIndex, int gridColumnIndex) {
	super(data, gridRowIndex, gridColumnIndex);
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

	Text t = new Text(12, EDGE_DISTANCE + 5, getModel().getName());
	getChildren().add(t);
    }

    @Override
    public StepModel getModel() {
	return (StepModel) super.getModel();
    }
}
