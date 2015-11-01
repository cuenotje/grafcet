package fr.grafcet.ui.event;

import fr.grafcet.ui.elements.GElementUI;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class GridElementSourceDragEventHandler implements EventHandler<MouseEvent> {

    private GElementUI component;
    private int elementGridIndex;

    public GridElementSourceDragEventHandler(GElementUI component, int elementGridIndex) {
	this.component = component;
	this.elementGridIndex = elementGridIndex;
    }

    public void updateElementGridIndex(int elementGridIndex) {
	this.elementGridIndex = elementGridIndex;
    }

    public void handle(MouseEvent event) {
	System.out.println("onDragDetected: deplacement d'un element.");
	Dragboard db = component.startDragAndDrop(TransferMode.COPY_OR_MOVE);

	ClipboardContent content = new ClipboardContent();
	content.putString("movingElementIndex:" + elementGridIndex);
	db.setContent(content);
	event.consume();
    }
}
