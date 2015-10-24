package fr.grafcet.ui.event;

import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GInitialStepUI;
import fr.grafcet.ui.elements.GrafcetElementsEnum;
import fr.grafcet.ui.elements.IElementBuilderCallback;
import fr.grafcet.util.GrafcetRepository;
import javafx.event.EventHandler;
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
	    System.out.println("onDragDropped: add element of type: " + elementName);
	    // construction du noeud cible
	    replaceNode(event, callback.handleBuild(GrafcetElementsEnum.valueOf(elementName), GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane)));
	    success = true;
	}
	event.setDropCompleted(success);
	event.consume();
    }

    private void replaceNode(DragEvent event, GElementUI newNode) {
	System.out.println("Current Pane: " + pane + " at " + GridPane.getRowIndex(pane) + "/" + GridPane.getColumnIndex(pane));
	int rowIndex = GridPane.getRowIndex(pane);
	int colIndex = GridPane.getColumnIndex(pane);
	container.getChildren().remove(pane);
	container.add(newNode, colIndex, rowIndex);
	// ajout dans le repository que si etape initiale
	if (newNode instanceof GInitialStepUI) {
	    GrafcetRepository.getInstance().addNewGrafcet((GInitialStepUI) newNode);
	}
    }
}
