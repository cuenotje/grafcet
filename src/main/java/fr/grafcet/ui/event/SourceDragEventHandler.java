package fr.grafcet.ui.event;

import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class SourceDragEventHandler implements EventHandler<MouseEvent> {

    private ToggleButton component;

    public SourceDragEventHandler(ToggleButton component) {
	this.component = component;
    }

    public void handle(MouseEvent event) {
	System.out.println("onDragDetected");
	Dragboard db = component.startDragAndDrop(TransferMode.ANY);

	ClipboardContent content = new ClipboardContent();
	content.putString(component.getId());
	db.setContent(content);

	event.consume();
    }
}
