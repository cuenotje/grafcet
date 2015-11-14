package fr.grafcet.ui.builder;

import fr.grafcet.AbstractController;
import fr.grafcet.bd.GrafcetDTO;
import fr.grafcet.exception.NonValideGrafcet;
import fr.grafcet.ui.dialogs.Dialogs;
import fr.grafcet.ui.elements.ElementBuilderController;
import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GInitialStepUI;
import fr.grafcet.ui.event.SourceDragEventHandler;
import fr.grafcet.ui.event.TargetDragEventHandler;
import fr.grafcet.ui.event.TargetDragEventHandler.DragPhase;
import fr.grafcet.util.GrafcetRepository;
import fr.grafcet.util.GridPaneHelper;
import fr.grafcet.util.IGrafcetController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
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
    private ToggleButton actionButton;
    @FXML
    private ToggleButton transitionButton;

    private ElementBuilderController builderController;

    @Override
    public void initController(Stage stage, Scene scene, IGrafcetController parent) {
	super.initController(stage, scene, parent);
	// initialisation des sources pour le drag and drop
	initialStepButton.setOnDragDetected(new SourceDragEventHandler(initialStepButton));
	stepButton.setOnDragDetected(new SourceDragEventHandler(stepButton));
	actionButton.setOnDragDetected(new SourceDragEventHandler(actionButton));
	transitionButton.setOnDragDetected(new SourceDragEventHandler(transitionButton));
    }

    public void initGrid() {
	getScene().setCursor(Cursor.WAIT);
	// nettoyage
	cleanGrid();
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
	// TODO
	// suppression des listeners sur la grille
	cleanGrid();
	// retour ecran d'accueil
	closeView();
    }

    /**
     * Suppression des listeners, contraintes et element de la grille.
     */
    private void cleanGrid() {
	builderGridPane.getColumnConstraints().clear();
	builderGridPane.getRowConstraints().clear();
	for (Node node : builderGridPane.getChildren()) {
	    node.setOnDragEntered(null);
	    node.setOnDragExited(null);
	    node.setOnDragOver(null);
	    node.setOnDragDropped(null);
	}
	builderGridPane.getChildren().clear();
    }

    public void initProjectNameAndLoadGrafcet(GrafcetDTO grafcet) {
	String projectName = null;
	if (null != grafcet) {
	    projectName = grafcet.getProjectName();
	    // chargement du grafcet
	    // element non validé
	    if (!grafcet.getNonValidateElements().isEmpty()) {
		for (GElementUI element : grafcet.getNonValidateElements()) {
		    GridPaneHelper.replaceNode(element, element.getGridRowIndex(), element.getGridColumnIndex(), builderGridPane);
		}
	    } else if (null != grafcet.getValidateGrafcet()) {
		// FIXME remplir la grille avec un grafcet validé
	    } else {
		// no grafcet elements
	    }
	}
	// nommage du projet
	getPersistenceController().initProject(projectName);
    }

    private void validateGrafcet() throws NonValideGrafcet {
	GInitialStepUI grafcet = GridPaneHelper.validateGrafcet(builderGridPane);
	GrafcetRepository.getInstance().addValidGrafcet(grafcet);
    }

    @FXML
    private void handleSave(final ActionEvent event) {
	getPersistenceController().save();
    }

    @FXML
    private void handleSaveTo(final ActionEvent event) {
	getPersistenceController().saveTo();
    }

    @FXML
    private void handleSimulate(final ActionEvent event) {
	try {
	    // lancer une validation du grafcet pour creer les liens entre les
	    // elements avant de démarrer la simulation
	    validateGrafcet();
	    // FIXME demarrage de la simulation
	} catch (NonValideGrafcet e) {
	    Dialogs.showErrorDialog(getStage(), "Erreur de validatio du grafcet.", e.getErrorRootCause(), "Erreur de validation.", e);
	}
    }

    @FXML
    private void handleValidate(final ActionEvent event) {
	try {
	    validateGrafcet();
	} catch (NonValideGrafcet e) {
	    Dialogs.showErrorDialog(getStage(), "Erreur de validatio du grafcet.", e.getErrorRootCause(), "Erreur de validation.", e);
	}
    }
}
