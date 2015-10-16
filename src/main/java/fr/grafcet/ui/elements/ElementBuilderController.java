package fr.grafcet.ui.elements;

import fr.grafcet.data.models.GInitialStepModel;
import fr.grafcet.ui.dialogs.Dialogs;
import javafx.stage.Stage;

/**
 * Controleur de gestion de construction d'element du grafcet.
 * <p>
 * Gere l'interaction avec l'utilisateur pour avoir les données nécessaire.
 */
public class ElementBuilderController implements IElementBuilderCallback {

    private Stage stage;

    public ElementBuilderController(Stage stage) {
	this.stage = stage;
    }

    @Override
    public GElementUI handleBuild(GrafcetElementsEnum elementType) {
	GElementUI result = null;
	switch (elementType) {
	case INITIAL_STEP:
	    result = buildInitialStep();
	    break;

	default:
	    break;
	}
	return result;
    }

    private GElementUI buildInitialStep() {
	String name = Dialogs.showInputDialog(stage, "Saisir le numero de l'étape.");
	GInitialStepModel model = new GInitialStepModel();
	model.setName(name);
	return GElementAbstractFactory.createGInitialStep(model);
    }
}
