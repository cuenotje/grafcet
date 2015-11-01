package fr.grafcet.util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/** utilitaire pour le grid pane */
public final class GridPaneHelper {

    private GridPaneHelper() {
    }

    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
	Node result = null;
	ObservableList<Node> childrens = gridPane.getChildren();
	for (Node node : childrens) {
	    if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
		result = node;
		break;
	    }
	}
	return result;
    }
}
