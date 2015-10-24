package fr.grafcet.bd;

import java.io.IOException;
import java.util.List;

import fr.grafcet.ui.elements.GInitialStepUI;

/** gestion de la persistence d'un grafcet */
public abstract class GrafcetBD {

    private static GrafcetBD instance;

    public static final GrafcetBD getInstance() {
	if (null == instance) {
	    instance = new XMLGrafcetPersister();
	}
	return instance;
    }

    /** lecture de grafcet */
    public abstract List<GInitialStepUI> getExistingGrafcet() throws IOException;

    /** sauvegarde de grafcet */
    public abstract void saveGrafcet(GInitialStepUI grafcet, String projectName) throws IOException;
}
