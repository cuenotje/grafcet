package fr.grafcet.ui.builder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GrafcetElementsEnum;
import fr.grafcet.ui.elements.ElementBuilderController;
import fr.grafcet.ui.event.TargetDragEventHandler.DragPhase;
import fr.grafcet.ui.event.TargetDragEventHandler;
import fr.grafcet.ui.event.SourceDragEventHandler;
import fr.grafcet.util.IGrafcetController;

/**
 * Controlleur de construction d'un grafcet.
 */
public class GrafcetBuilderController extends AbstractBuilderController implements Initializable {

    private static final int GRID_SIZE = 50;
    @FXML
    // private Canvas builderCanvas;
    private GridPane builderGridPane;

    @FXML
    private ToggleButton initialStepButton;

    private List<GElementUI> currentGraph;
    
    private ElementBuilderController builderController;
    
    public GrafcetBuilderController() {
	builderController = new ElementBuilderController(getStage());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	getScene().setCursor(Cursor.WAIT);
	currentGraph = new ArrayList<GElementUI>();
	builderGridPane.setGridLinesVisible(true);
	// initialisation de la grille et de la target pour le drag and drop
	for (int i = 0; i < GRID_SIZE; i++) {
	    ColumnConstraints colcon = new ColumnConstraints();
	    // Here we set the pref height of the row, but you could also
	    // use .setPercentHeight(double) if you don't know much space
	    // you will need for each label.
	    colcon.setMinWidth(20);
	    builderGridPane.getColumnConstraints().add(colcon);
	    RowConstraints rowcon = new RowConstraints();
	    // Here we set the pref height of the row, but you could also use
	    // .setPercentHeight(double) if you don't know much space you will
	    // need for each label.
	    rowcon.setMinHeight(20);
	    builderGridPane.getRowConstraints().add(rowcon);
	    for (int j = 0; j < GRID_SIZE; j++) {
		Pane pane = new Pane();
		// pane.setStyle("-fx-background-color: red;");
		// ajout des listeners
		pane.setOnDragEntered(new TargetDragEventHandler(builderGridPane, pane, DragPhase.DRAG_ENTERED, builderController));
		pane.setOnDragExited(new TargetDragEventHandler(builderGridPane, pane, DragPhase.DRAG_EXITED, builderController));
		pane.setOnDragOver(new TargetDragEventHandler(builderGridPane, pane, DragPhase.DRAG_OVER, builderController));
		pane.setOnDragDropped(new TargetDragEventHandler(builderGridPane, pane, DragPhase.DRAG_DROPPED, builderController));
		builderGridPane.add(pane, i, j);
	    }
	}
	//  
	// initialisation des sources pour le drag and drop
	initialStepButton.setOnDragDetected(new SourceDragEventHandler(initialStepButton, GrafcetElementsEnum.INITIAL_STEP));
	getScene().setCursor(Cursor.DEFAULT);
    }
}
