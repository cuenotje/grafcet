package fr.grafcet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import fr.grafcet.ui.builder.GrafcetBuilderController;
import fr.grafcet.ui.dialogs.Dialogs;
import fr.grafcet.util.IGrafcetController;
import fr.grafcet.util.ViewList;
import fr.grafcet.util.ViewLoaderHelper;

/**
 * Controlleur de la fenetre principale.
 */
public class MainController extends AbstractController implements Initializable {

    @FXML
    private MenuBar menuBar;

    private GrafcetBuilderController grafcetBuilderControler;

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
	if ("menuNew".equalsIgnoreCase(sourceId)) {
	    openGrafcetBuilder();
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
	menuBar.setUseSystemMenuBar(true);
    }

    /** Chargement ecran de construction d'un grafcet */
    private void openGrafcetBuilder() {
	try {
	    Scene s = ViewLoaderHelper.loadView(ViewList.GRAFCET_BUILDER_VIEW, getStage());
	    getStage().setScene(s);
	} catch (Exception exception) {
	    exception.printStackTrace();
	    /**
	     * * / Dialogs.showErrorDialog(primaryStage,
	     * "Ooops, there was an error!", "Error Dialog With Exception",
	     * "title", exception); /
	     **/
	}
    }

    /** Chargement écran principal contenant le menu */
    private void openMainView() {
	getStage().setScene(getScene());
    }
}
