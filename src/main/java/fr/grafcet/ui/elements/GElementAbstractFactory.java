package fr.grafcet.ui.elements;

public final class GElementAbstractFactory {

    private GElementAbstractFactory() {
    }

    public static GInitialStepUI createGInitialStep(int gridRowIndex, int gridColumnIndex) {
	return new GInitialStepUI(gridRowIndex, gridColumnIndex);
    }

    public static GStepUI createGStep(int gridRowIndex, int gridColumnIndex) {
	return new GStepUI(gridRowIndex, gridColumnIndex);
    }

    public static GTransitionUI createGTransition(int gridRowIndex, int gridColumnIndex) {
	return new GTransitionUI(gridRowIndex, gridColumnIndex);
    }

    public static GStepActionUI createGStepAction(int gridRowIndex, int gridColumnIndex) {
	return new GStepActionUI(gridRowIndex, gridColumnIndex);
    }
}
