package fr.grafcet.ui.elements;

import fr.grafcet.data.models.GInitialStepModel;

public final class GElementAbstractFactory {

    private GElementAbstractFactory() {
    }

    public static GInitialStep createGInitialStep(GInitialStepModel data) {
	return new GInitialStep(data);
    }
}
