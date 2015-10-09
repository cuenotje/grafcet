package fr.grafcet.util;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/** Utilitaire de chargement des vues xml */
public final class ViewLoaderHelper {

	private ViewLoaderHelper() {
	}

	/** Permet de charger un fichier fxml de vue */
	public static final Parent loadView(ViewList view) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setResources(ResourceBundle.getBundle(view.getBundleUrl()));
		return (Parent) fxmlLoader.load(ViewLoaderHelper.class.getClassLoader()
				.getResourceAsStream(view.getViewUrl()));
	}
}
