package fr.grafcet.ui.builder;

import fr.grafcet.AbstractController;
import fr.grafcet.ui.elements.ElementBuilderController;
import fr.grafcet.ui.elements.GrafcetElementsEnum;
import fr.grafcet.ui.event.SourceDragEventHandler;
import fr.grafcet.ui.event.TargetDragEventHandler;
import fr.grafcet.ui.event.TargetDragEventHandler.DragPhase;
import fr.grafcet.util.IGrafcetController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Controlleur de construction d'un grafcet.
 */
public class GrafcetBuilderController extends AbstractController {

    private static final int H_GRID_SIZE = 20;
    private static final int V_GRID_SIZE = 20;
    @FXML
    private GridPane builderGridPane;

    @FXML
    private ToggleButton initialStepButton;
    @FXML
    private ToggleButton stepButton;
    @FXML
    private ToggleButton transitionButton;

    private ElementBuilderController builderController;

    @Override
    public void initController(Stage stage, Scene scene, IGrafcetController parent) {
	super.initController(stage, scene, parent);
	getScene().setCursor(Cursor.WAIT);
	// builderGridPane.setGridLinesVisible(true);
	// initialisation de la grille et de la target pour le drag and drop
	for (int i = 0; i < V_GRID_SIZE; i++) {
	    ColumnConstraints colcon = new ColumnConstraints();
	    // Here we set the pref height of the row, but you could also
	    // use .setPercentHeight(double) if you don't know much space
	    // you will need for each label.
	    colcon.setMinWidth(40);
	    builderGridPane.getColumnConstraints().add(colcon);
	    RowConstraints rowcon = new RowConstraints();
	    // Here we set the pref height of the row, but you could also use
	    // .setPercentHeight(double) if you don't know much space you will
	    // need for each label.
	    rowcon.setMinHeight(40);
	    builderGridPane.getRowConstraints().add(rowcon);
	    for (int j = 0; j < H_GRID_SIZE; j++) {
		Pane pane = new Pane();
		// pane.setStyle("-fx-background-color: red;");
		// ajout des listeners
		pane.setOnDragEntered(new TargetDragEventHandler(builderGridPane, pane, DragPhase.DRAG_ENTERED, getBuilderController()));
		pane.setOnDragExited(new TargetDragEventHandler(builderGridPane, pane, DragPhase.DRAG_EXITED, getBuilderController()));
		pane.setOnDragOver(new TargetDragEventHandler(builderGridPane, pane, DragPhase.DRAG_OVER, getBuilderController()));
		pane.setOnDragDropped(new TargetDragEventHandler(builderGridPane, pane, DragPhase.DRAG_DROPPED, getBuilderController()));
		builderGridPane.add(pane, i, j);
	    }
	}
	//
	// initialisation des sources pour le drag and drop
	initialStepButton.setOnDragDetected(new SourceDragEventHandler(initialStepButton, GrafcetElementsEnum.INITIAL_STEP));
	stepButton.setOnDragDetected(new SourceDragEventHandler(stepButton, GrafcetElementsEnum.STEP));
	transitionButton.setOnDragDetected(new SourceDragEventHandler(transitionButton, GrafcetElementsEnum.TRANSITION));
	getScene().setCursor(Cursor.DEFAULT);
    }

    public ElementBuilderController getBuilderController() {
	if (null == builderController) {
	    builderController = new ElementBuilderController(getStage());
	}
	return builderController;
    }
    

    @FXML
    private void handleGoBack(final ActionEvent event) {
	// sauvegarde grafcet en cours
	// retour ecran d'accueil
	closeView();
    }
}
