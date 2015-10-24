package fr.grafcet.ui.elements;

import fr.grafcet.data.models.GTransitionModel;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

/** Etape d'un grafcet */
public class GTransitionUI extends GElementUI {

    public GTransitionUI(GTransitionModel data, int gridRowIndex, int gridColumnIndex) {
	super(data, gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	double endOfRectangle = EDGE_DISTANCE * 2 - 8;
	Polyline pl = new Polyline(8, 8, endOfRectangle, 8, endOfRectangle, endOfRectangle, 8, endOfRectangle, 8, 8);
	getChildren().add(pl);

	Line l = new Line(10, 8, 10, EDGE_DISTANCE - 8);
	getChildren().add(l);
	Line l1 = new Line(10, EDGE_DISTANCE, 18, EDGE_DISTANCE);
	getChildren().add(l1);

	Text t = new Text(18, EDGE_DISTANCE + 2, getModel().getCondition());
	getChildren().add(t);
    }

    @Override
    public GTransitionModel getModel() {
	return (GTransitionModel) super.getModel();
    }
}
