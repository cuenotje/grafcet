package fr.grafcet.util;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Utilitaire de chargement des vues xml */
public final class ViewLoaderHelper {

    private ViewLoaderHelper() {
    }

    /** Permet de charger un fichier fxml de vue */
    public static final IGrafcetController loadView(ViewList view, Stage container, IGrafcetController parent) throws IOException {
	FXMLLoader fxmlLoader = new FXMLLoader();
	fxmlLoader.setResources(ResourceBundle.getBundle(view.getBundleUrl()));
	final Parent parentPane = (Parent) fxmlLoader.load(ViewLoaderHelper.class.getClassLoader().getResourceAsStream(view.getViewUrl()));
	final Scene scene = new Scene(parentPane);
	final IGrafcetController controller = fxmlLoader.getController();
	controller.initController(container, scene, parent);
	return controller;
    }
}
