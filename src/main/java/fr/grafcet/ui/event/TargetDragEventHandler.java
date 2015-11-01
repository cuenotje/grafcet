package fr.grafcet.ui.event;

import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GInitialStepUI;
import fr.grafcet.ui.elements.GrafcetElementsEnum;
import fr.grafcet.ui.elements.IElementBuilderCallback;
import fr.grafcet.util.GrafcetRepository;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TargetDragEventHandler implements EventHandler<DragEvent> {

    public enum DragPhase {
	DRAG_ENTERED, DRAG_OVER, DRAG_EXITED, DRAG_DROPPED
    }

    private GridPane container;
    private Pane pane;
    private DragPhase phase;
    private IElementBuilderCallback callback;

    public TargetDragEventHandler(GridPane container, Pane pane, DragPhase phase, IElementBuilderCallback callback) {
	this.container = container;
	this.phase = phase;
	this.pane = pane;
	this.callback = callback;
    }

    public void handle(DragEvent event) {
	switch (phase) {
	case DRAG_ENTERED:
	    onDragEntered(event);
	    break;
	case DRAG_EXITED:
	    onDragExited(event);
	    break;
	case DRAG_OVER:
	    onDragOver(event);
	    break;
	case DRAG_DROPPED:
	    onDragDropped(event);
	    break;
	default:
	    break;
	}
    }

    private void onDragEntered(DragEvent event) {
	if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
	    pane.getStyleClass().clear();
	    pane.getStyleClass().add("gridPane-over");
	}
	event.consume();
    }

    private void onDragOver(DragEvent event) {
	if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
	    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	}
	event.consume();
    }

    private void onDragExited(DragEvent event) {
	if (event.getGestureSource() != pane) {
	    pane.getStyleClass().clear();
	    pane.getStyleClass().add("gridPane");
	}
	event.consume();
    }

    private void onDragDropped(DragEvent event) {
	Dragboard db = event.getDragboard();
	boolean success = false;
	if (db.hasString()) {
	    String elementName = db.getString();
	    if (elementName.startsWith("moving")) {
		// déplacement d'un élément existant sur la grille
		System.out.println("onDragDropped: moving element: " + elementName);
		movingNode(elementName);
	    } else {
		System.out.println("onDragDropped: add element of type: " + elementName);
		// construction du noeud cible
		replaceNode(event, callback.handleBuild(GrafcetElementsEnum.getEnum(elementName), GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane)));
	    }
	    success = true;
	}
	event.setDropCompleted(success);
	event.consume();
    }

    private void movingNode(String elementName) {
	String[] movingElementData = elementName.split(":");
	int gridElementIndex = Integer.parseInt(movingElementData[1]);

	// element se déplacant
	GElementUI element = (GElementUI) container.getChildren().get(gridElementIndex);

	// coordonnée d'origin
	int originRowIndex = element.getGridRowIndex();
	int originColumnIndex = element.getGridColumnIndex();

	// coordonnée courante de la grille
	int currentRowIndex = GridPane.getRowIndex(pane);
	int currentColIndex = GridPane.getColumnIndex(pane);

	element.setGridRowIndex(currentRowIndex);
	element.setGridColumnIndex(currentColIndex);

	// suppression de la grille de l'element se deplacent
	container.getChildren().remove(element);
	// suppression de l'element courant de la grille
	container.getChildren().remove(pane);
	// déplacement de l'element source
	container.add(element, currentColIndex, currentRowIndex);
	// positionnement du pane courant sur l'emplacement source
	container.add(pane, originColumnIndex, originRowIndex);
	// MAJ index sur ecouteur drag and drop
	for (Node node : container.getChildren()) {
	    if (node instanceof GElementUI) {
		((GridElementSourceDragEventHandler) ((GElementUI) node).getOnDragDetected()).updateElementGridIndex(container.getChildren().indexOf(node));
	    }
	}
    }

    private void replaceNode(DragEvent event, GElementUI newNode) {
	System.out.println("Current Pane: " + pane + " at " + GridPane.getRowIndex(pane) + "/" + GridPane.getColumnIndex(pane));
	int rowIndex = GridPane.getRowIndex(pane);
	int colIndex = GridPane.getColumnIndex(pane);
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
	// ajout dans le repository que si etape initiale
	if (newNode instanceof GInitialStepUI) {
	    GrafcetRepository.getInstance().addNewGrafcet((GInitialStepUI) newNode);
	} else{
	    // raccrochement des elements entre eux
	    // TODO
	    // if(newNode instanceof )
	}
    }
}
