package fr.grafcet;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Chargeur de l'application.
 */
public class ApplicationLoaderController extends Application {

	/** {@inheritDoc} */
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		final Parent fxmlRoot = (Parent) fxmlLoader.load(new FileInputStream(
				"menuBar.fxml"));
		primaryStage.setScene(new Scene(fxmlRoot));
		primaryStage.show();
	}

	public static void main(String[] arguments) {
		Application.launch(ApplicationLoaderController.class, arguments);
	}
}
