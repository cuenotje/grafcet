package fr.grafcet.ui.event;

import fr.grafcet.ui.elements.GrafcetElementsEnum;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class SourceDragEventHandler implements EventHandler<MouseEvent> {

    private ToggleButton component;
    private GrafcetElementsEnum type;

    public SourceDragEventHandler(ToggleButton component, GrafcetElementsEnum type) {
	this.component = component;
	this.type = type;
    }

    public void handle(MouseEvent event) {
	/* drag was detected, start drag-and-drop gesture */
	System.out.println("onDragDetected");

	/* allow any transfer mode */
	Dragboard db = component.startDragAndDrop(TransferMode.ANY);

	/* put a string on dragboard */
	ClipboardContent content = new ClipboardContent();
	content.putString(type.name());
	db.setContent(content);

	event.consume();
    }
}