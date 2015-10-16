package fr.grafcet.ui.elements;

import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

import fr.grafcet.data.models.GInitialStepModel;

/** Etape initiale d'un grafcet */
public class GInitialStep extends GElementUI {

    private GInitialStepModel data;

    public GInitialStep(GInitialStepModel data) {
	this.data = data;
    }

    public void initShape() {
	getChildren().add(new Rectangle(0, 0, EDGE_DISTANCE * 2, EDGE_DISTANCE * 2));
	getChildren().add(new Rectangle(5, 5, EDGE_DISTANCE * 2 - 10, EDGE_DISTANCE * 2 - 10));
	getChildren().add(new Text(10, 20, data.getName()));
	/**
	 * * / // les deux carrés getElements().add(new
	 * Rectangle(getLeftUpperCornerX(), getLeftUpperCornerY(), EDGE_DISTANCE
	 * * 2, EDGE_DISTANCE * 2)); getElements().add(new
	 * Rectangle(getLeftUpperCornerX()-5, getLeftUpperCornerY()-5,
	 * EDGE_DISTANCE * 2-10, EDGE_DISTANCE * 2-10)); // le nom de l'étape
	 * initiale getElements().add(new Text(getOrigin().getX() - 10,
	 * getOrigin().getY(), data.getName())); /
	 **/
    }
}
