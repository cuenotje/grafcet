package fr.grafcet.ui.event;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.Dragboard;
import javafx.scene.control.ToggleButton;

import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.IElementBuilderCallback;
import fr.grafcet.ui.elements.GrafcetElementsEnum;

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
	/* the drag-and-drop gesture entered the target */
	System.out.println("onDragEntered");
	/* show to the user that it is an actual gesture target */
	if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
	    pane.setStyle("-fx-background-color: green;");
	}
	event.consume();
    }

    private void onDragOver(DragEvent event) {
	System.out.println("onDragOver");
	/*
	 * accept it only if it is not dragged from the same node and if it has
	 * a string data
	 */
	if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
	    /* allow for both copying and moving, whatever user chooses */
	    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	}
	event.consume();
    }

    private void onDragExited(DragEvent event) {
	pane.setStyle("-fx-background-color: white;");
	event.consume();
    }

    private void onDragDropped(DragEvent event) {
	/* data dropped */
	System.out.println("onDragDropped");
	/* if there is a string data on dragboard, read it and use it */
	Dragboard db = event.getDragboard();
	boolean success = false;
	if (db.hasString()) {
	    // construire la cible ici
	    replaceNode(event, callback.handleBuild(GrafcetElementsEnum.getEnum(((ToggleButton)event.getSource()).getId())));
	    success = true;
	}
	/*
	 * let the source know whether the string was successfully transferred
	 * and used
	 */
	event.setDropCompleted(success);
	event.consume();
    }

    private void replaceNode(DragEvent event, GElementUI newNode) {
	for (Node node : container.getChildren()) {
	    if (node instanceof Pane) {
		if (node.getBoundsInParent().contains(event.getSceneX(), event.getSceneY())) {
		    System.out.println("Node: " + node + " at " + GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node));
		    int rowIndex = GridPane.getRowIndex(node);
		    int colIndex = GridPane.getColumnIndex(node);
		    container.getChildren().remove(node);
		    container.add(newNode, rowIndex, colIndex);
		    break;
		}
	    }
	}
    }
}
