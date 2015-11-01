package fr.grafcet.ui.elements.lines;

import fr.grafcet.ui.elements.GElementUI;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/** Transition d'un grafcet */
public class GOrientedLineUI extends GElementUI {

    private String condition;

    public GOrientedLineUI(int gridRowIndex, int gridColumnIndex) {
	super(gridRowIndex, gridColumnIndex);
    }

    public void initShape() {
	Line l = new Line(EDGE_DISTANCE, 0, EDGE_DISTANCE, EDGE_DISTANCE * 2);
	getChildren().add(l);
	Line l1 = new Line(EDGE_DISTANCE, EDGE_DISTANCE, EDGE_DISTANCE + 5, EDGE_DISTANCE);
	getChildren().add(l1);

	Text t = new Text(EDGE_DISTANCE + 8, EDGE_DISTANCE + 2, getCondition());
	getChildren().add(t);
    }

    public String getCondition() {
	return condition;
    }

    public void setCondition(String condition) {
	this.condition = condition;
    }
}
