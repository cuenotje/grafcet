package fr.grafcet.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fr.grafcet.bd.GrafcetBD;
import fr.grafcet.ui.dialogs.Dialogs;
import fr.grafcet.ui.elements.GInitialStepUI;
import fr.grafcet.util.GrafcetRepository;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Permet de charger, sauvegarder un grafcet.
 */
public class GrafcetPersistenceController {

    private Stage stage;
    private String currentProjectName;
    private File saveDirectory;

    public GrafcetPersistenceController(Stage currentStage) {
	this.stage = currentStage;
    }

    public GInitialStepUI openGrafcet() {
	// afficher une liste de grafcet sauvegardé précédement (table)
	return null;
    }

    public void save() {
	if (null == currentProjectName) {
	    currentProjectName = getProjectName();
	}
	if (null != currentProjectName) {
	    List<GInitialStepUI> grafcets = GrafcetRepository.getInstance().getAll();
	    for (GInitialStepUI gInitialStepUI : grafcets) {
		gInitialStepUI.getModel().setProjectName(currentProjectName);
		try {
		    GrafcetBD.getInstance().saveGrafcet(gInitialStepUI, currentProjectName, saveDirectory);
		} catch (IOException e) {
		    Dialogs.showErrorDialog(stage, e.getMessage(), "Une erreur est survenue à l'enregistrement", "Erreur d'enregistrement", e);
		}
	    }
	}
    }

    public void saveTo() {
	DirectoryChooser chooser = new DirectoryChooser();
	chooser.setTitle("Emplacement du projet");
	chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
	saveDirectory = chooser.showDialog(stage);
	save();
    }

    public void initProject() {
	currentProjectName = getProjectName();
    }

    private String getProjectName() {
	return Dialogs.showInputDialog(stage, "Nom du projet ?", "Entrer un nom pour votre projet", "Nommer son projet", "Grafcet-1");
    }
}
