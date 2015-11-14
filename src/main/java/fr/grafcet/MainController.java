package fr.grafcet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.grafcet.bd.GrafcetDTO;
import fr.grafcet.ui.builder.GrafcetBuilderController;
import fr.grafcet.ui.dialogs.Dialogs;
import fr.grafcet.ui.elements.GInitialStepUI;
import fr.grafcet.util.IGrafcetController;
import fr.grafcet.util.ViewList;
import fr.grafcet.util.ViewLoaderHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controlleur de la fenetre principale.
 */
public class MainController extends AbstractController implements Initializable {

    @FXML
    private MenuBar menuBar;

    private ResourceBundle resourceBundle;
    private GrafcetBuilderController builderController;

    /**
     * Handle action related to "About" menu item.
     * 
     * @param event
     *            Event on "About" menu item.
     */
    @FXML
    private void handleAboutAction(final ActionEvent event) {
	provideAboutFunctionality();
    }

    @FXML
    private void handleAction(final ActionEvent event) {
	String sourceId = "";
	if (event.getSource() instanceof MenuItem) {
	    sourceId = ((MenuItem) event.getSource()).getId();
	}
	switch (sourceId) {
	case "menuNew":
	    openGrafcetBuilder(null);
	    break;
	case "menuOpen":
	    try {
		openGrafcetBuilder(loadGrafcet());
	    } catch (IOException e) {
		Dialogs.showErrorDialog(getStage(), "Erreur de chargement du grafcet.", e.getMessage(), "Chargement d'un grafcet", e);
	    }
	    break;
	case "menuExit":
	default:
	    System.exit(0);
	    break;
	}
    }

    /**
     * Handle action related to input (in this case specifically only responds
     * to keyboard event CTRL-A).
     * 
     * @param event
     *            Input event.
     */
    @FXML
    private void handleKeyInput(final InputEvent event) {
	if (event instanceof KeyEvent) {
	    final KeyEvent keyEvent = (KeyEvent) event;
	    if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
		provideAboutFunctionality();
	    }
	} else {

	}
    }

    /**
     * Perform functionality associated with "About" menu selection or CTRL-A.
     */
    private void provideAboutFunctionality() {
	System.out.println("You clicked on About!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	this.resourceBundle = resourceBundle;
    }

    @Override
    public void initController(Stage stage, Scene scene, IGrafcetController parent) {
	super.initController(stage, scene, parent);
	getStage().setTitle(resourceBundle.getString("main.window.title"));
	getStage().getIcons().add(new Image("images/g7.png"));
    }

    /** Chargement ecran de construction d'un grafcet */
    private void openGrafcetBuilder(GrafcetDTO grafcet) {
	if (null == builderController) {
	    try {
		builderController = (GrafcetBuilderController) ViewLoaderHelper.loadView(ViewList.GRAFCET_BUILDER_VIEW, getStage(), this);
	    } catch (Exception exception) {
		Dialogs.showErrorDialog(getStage(), "Ooops, there was an error!", "Error Dialog With Exception", "title", exception);
	    }
	}
	if (null != builderController) {
	    builderController.initGrid();
	    getStage().setScene(builderController.getScene());
	    builderController.initProjectNameAndLoadGrafcet(grafcet);
	}
    }

    @Override
    public void handleChildClosing() {
	// on ré ouvre l'ecran principale
	getStage().setScene(getScene());
    }

    public ResourceBundle getBundle() {
	return resourceBundle;
    }

    private GrafcetDTO loadGrafcet() throws IOException {
	return getPersistenceController().openGrafcet();
    }
}
