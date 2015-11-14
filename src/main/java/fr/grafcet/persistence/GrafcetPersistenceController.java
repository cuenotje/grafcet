package fr.grafcet.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fr.grafcet.bd.ExistingGrafcetDTO;
import fr.grafcet.bd.GrafcetBD;
import fr.grafcet.bd.GrafcetDTO;
import fr.grafcet.ui.dialogs.Dialogs;
import fr.grafcet.ui.dialogs.Dialogs.DialogOptions;
import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GInitialStepUI;
import fr.grafcet.util.GrafcetRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
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

    public GrafcetDTO openGrafcet() throws IOException {
	// affiche une liste de grafcet sauvegardé précédement
	ExistingGrafcetDTO current = showListGrafcetDialog();
	// charge le grafcet en question
	return GrafcetBD.getInstance().loadGrafcet(current);
    }

    public void save() {
	if (null == currentProjectName) {
	    currentProjectName = getProjectName();
	}
	if (null != currentProjectName) {
	    GrafcetDTO dto = new GrafcetDTO();
	    dto.setProjectName(currentProjectName);
	    // est ce que j'ai un grafcet validé
	    // FIXME comment faire si plusieurs grafcet ???
	    List<GInitialStepUI> grafcets = GrafcetRepository.getInstance().getAllValidGrafcet();
	    if (!grafcets.isEmpty()) {
		dto.setValidateGrafcet(grafcets.get(0));
	    }
	    // save liste grafcet non validé
	    List<GElementUI> nonValidateElement = GrafcetRepository.getInstance().getAllNonValidElement();
	    if (!nonValidateElement.isEmpty()) {
		dto.getNonValidateElements().addAll(nonValidateElement);
	    }
	    try {
		GrafcetBD.getInstance().saveGrafcet(dto, saveDirectory);
	    } catch (IOException e) {
		Dialogs.showErrorDialog(stage, e.getMessage(), "Une erreur est survenue à l'enregistrement d'éléments non validé de grafcet.", "Erreur d'enregistrement", e);
	    }
	}
	Dialogs.showInformationDialog(stage, "Enregistremet éffectué.");
    }

    public void saveTo() {
	DirectoryChooser chooser = new DirectoryChooser();
	chooser.setTitle("Emplacement du projet");
	chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
	saveDirectory = chooser.showDialog(stage);
	save();
    }

    public void initProject(String projectName) {
	if (null != projectName) {
	    currentProjectName = projectName;
	} else {
	    currentProjectName = getProjectName();
	}
    }

    private String getProjectName() {
	return Dialogs.showInputDialog(stage, "Nom du projet ?", "Entrer un nom pour votre projet", "Nommer son projet", "Grafcet-1");
    }

    private ExistingGrafcetDTO showListGrafcetDialog() throws IOException {

	ObservableList<ExistingGrafcetDTO> grafcets = FXCollections.observableList(GrafcetBD.getInstance().getExistingGrafcet());
	final ListView<ExistingGrafcetDTO> listView = new ListView<ExistingGrafcetDTO>(grafcets);
	StackPane root = new StackPane();
	root.getChildren().add(listView);
	Dialogs.showCustomDialog(stage, root, "Liste des grafcets existants:", "Grafcet enregistré", DialogOptions.OK, null);
	return listView.getSelectionModel().getSelectedItem();
    }
}
