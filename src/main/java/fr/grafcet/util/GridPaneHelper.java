package fr.grafcet.util;

import fr.grafcet.exception.NonValideGrafcet;
import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GInitialStepUI;
import fr.grafcet.ui.event.GridElementSourceDragEventHandler;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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

    /** valide le grafcet et créer les liens entre les elements */
    public static GInitialStepUI validateGrafcet(GridPane gridPane) throws NonValideGrafcet {
	// parcour de la grille à la recherche de l'etapa initiale ou pas ?
	return null;
    }

    /** Remplace un noeud sur la grille */
    public static void replaceNode(GElementUI newNode, int rowIndex, int colIndex, GridPane container) {

	Pane pane = (Pane) getNodeByRowColumnIndex(rowIndex, colIndex, container);
	System.out.println("Current Pane: " + pane + " at " + rowIndex + "/" + colIndex);

	container.getChildren().remove(pane);
	// supprimer listener
	pane.setOnDragEntered(null);
	pane.setOnDragExited(null);
	pane.setOnDragOver(null);
	pane.setOnDragDropped(null);

	// ajout element à la grille
	container.add(newNode, colIndex, rowIndex);
	// ajout listener souris pour le déplacement en drag and drop
	newNode.setOnDragDetected(new GridElementSourceDragEventHandler(newNode, container.getChildren().indexOf(newNode)));
	// ajout dans la liste des elements non validé
	GrafcetRepository.getInstance().addNonValidElement(newNode);
    }
}
