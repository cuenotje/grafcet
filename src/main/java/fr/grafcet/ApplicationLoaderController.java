package fr.grafcet;

import fr.grafcet.ui.dialogs.Dialogs;
import fr.grafcet.util.ViewList;
import fr.grafcet.util.ViewLoaderHelper;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Chargeur de l'application.
 */
public class ApplicationLoaderController extends Application {

    /** {@inheritDoc} */
    public void start(Stage primaryStage) throws Exception {
	try {
	    primaryStage.setScene(ViewLoaderHelper.loadView(ViewList.MAIN_VIEW, primaryStage));
	    primaryStage.show();
	} catch (Exception exception) {
	    Dialogs.showErrorDialog(primaryStage, "Ooops, there was an error!", "Error Dialog With Exception", "title", exception);
	}
    }

    public static void main(String[] arguments) {
	Application.launch(ApplicationLoaderController.class, arguments);
    }
}
