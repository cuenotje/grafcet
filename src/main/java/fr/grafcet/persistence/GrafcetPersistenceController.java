package fr.grafcet.persistence;

import fr.grafcet.ui.elements.GInitialStepUI;
import javafx.stage.Stage;

/**
 * Permet de charger, sauvegarder un grafcet au format xml.
 */
public class GrafcetPersistenceController {

    private Stage stage;

    public GrafcetPersistenceController(Stage currentStage) {
	this.stage = stage;
    }
    
    public GInitialStepUI openGrafcet() {
	// afficher une liste de grafcet sauvegardé précédement (table)
	return null;
    }
}
