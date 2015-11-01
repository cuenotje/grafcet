package fr.grafcet.ui.elements;

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
	case ACTION:
	    result = buildAction(gridRowIndex, gridColumnIndex);
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

    private GInitialStepUI buildInitialStep(int gridRowIndex, int gridColumnIndex) {
	GInitialStepUI result = GElementAbstractFactory.createGInitialStep(gridRowIndex, gridColumnIndex);
	String name = Dialogs.showInputDialog(stage, "Saisir le numero de l'étape.");
	result.setName(name);
	result.initShape();
	return result;
    }

    private GStepUI buildStep(int gridRowIndex, int gridColumnIndex) {
	GStepUI result = GElementAbstractFactory.createGStep(gridRowIndex, gridColumnIndex);
	String name = Dialogs.showInputDialog(stage, "Saisir le numero de l'étape.");
	result.setName(name);
	result.initShape();
	return result;
    }

    private GTransitionUI buildTransition(int gridRowIndex, int gridColumnIndex) {
	GTransitionUI result = GElementAbstractFactory.createGTransition(gridRowIndex, gridColumnIndex);
	String condition = Dialogs.showInputDialog(stage, "Saisir la condition booleenne.");
	result.setCondition(condition);
	result.initShape();
	return result;
    }

    private GStepActionUI buildAction(int gridRowIndex, int gridColumnIndex) {
	GStepActionUI result = GElementAbstractFactory.createGStepAction(gridRowIndex, gridColumnIndex);
	String action = Dialogs.showInputDialog(stage, "Saisir la sortie à activer.");
	result.setAction(action);
	result.initShape();
	return result;
    }
}
