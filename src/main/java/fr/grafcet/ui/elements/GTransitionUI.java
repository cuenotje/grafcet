package fr.grafcet.ui.elements;

import fr.grafcet.data.models.GTransitionModel;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/** Etape d'un grafcet */
public class GTransitionUI extends GElementUI {

    public GTransitionUI(GTransitionModel data, int gridRowIndex, int gridColumnIndex) {
	super(data, gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	Line l = new Line(EDGE_DISTANCE, 0, EDGE_DISTANCE, EDGE_DISTANCE * 2);
	getChildren().add(l);
	Line l1 = new Line(EDGE_DISTANCE, EDGE_DISTANCE, EDGE_DISTANCE + 5, EDGE_DISTANCE);
	getChildren().add(l1);

	Text t = new Text(EDGE_DISTANCE + 8, EDGE_DISTANCE + 2, getModel().getCondition());
	getChildren().add(t);
    }

    @Override
    public GTransitionModel getModel() {
	return (GTransitionModel) super.getModel();
    }
}
