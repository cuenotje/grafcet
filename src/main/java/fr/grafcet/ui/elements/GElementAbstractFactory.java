package fr.grafcet.ui.elements;

import fr.grafcet.data.models.GTransitionModel;
import fr.grafcet.data.models.InitialStepModel;
import fr.grafcet.data.models.StepModel;

public final class GElementAbstractFactory {

    private GElementAbstractFactory() {
    }

    public static GInitialStepUI createGInitialStep(InitialStepModel model, int gridRowIndex, int gridColumnIndex) {
	return new GInitialStepUI(model, gridRowIndex, gridColumnIndex);
    }

    public static GStepUI createGStep(StepModel model, int gridRowIndex, int gridColumnIndex) {
	return new GStepUI(model, gridRowIndex, gridColumnIndex);
    }

    public static GTransitionUI createGTransition(GTransitionModel model, int gridRowIndex, int gridColumnIndex) {
	return new GTransitionUI(model, gridRowIndex, gridColumnIndex);
    }
}
