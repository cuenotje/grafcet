package fr.grafcet.ui.elements;

import fr.grafcet.data.models.InitialStepModel;
import fr.grafcet.data.models.StepModel;
import fr.grafcet.data.models.GTransitionModel;
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
    public GElementUI handleBuild(GrafcetElementsEnum elementType, int gridRowIndex, int gridColumnIndex) {
	GElementUI result = null;
	switch (elementType) {
	case INITIAL_STEP:
	    result = buildInitialStep(gridRowIndex, gridColumnIndex);
	    break;
	case STEP:
	    result = buildStep(gridRowIndex, gridColumnIndex);
	    break;
	case TRANSITION:
	    result = buildTransition(gridRowIndex, gridColumnIndex);
	    break;
	default:
	    break;
	}
	return result;
    }

    private GElementUI buildInitialStep(int gridRowIndex, int gridColumnIndex) {
	String name = Dialogs.showInputDialog(stage, "Saisir le numero de l'étape.");
	InitialStepModel model = new InitialStepModel();
	model.setName(name);
	return GElementAbstractFactory.createGInitialStep(model, gridRowIndex, gridColumnIndex);
    }
    private GElementUI buildStep(int gridRowIndex, int gridColumnIndex) {
	String name = Dialogs.showInputDialog(stage, "Saisir le numero de l'étape.");
	StepModel model = new StepModel();
	model.setName(name);
	return GElementAbstractFactory.createGStep(model, gridRowIndex, gridColumnIndex);
    }
    private GElementUI buildTransition(int gridRowIndex, int gridColumnIndex) {
	String condition = Dialogs.showInputDialog(stage, "Saisir la condition booleenne.");
	GTransitionModel model = new GTransitionModel();
	model.setCondition(condition);
	return GElementAbstractFactory.createGTransition(model, gridRowIndex, gridColumnIndex);
    }
}
