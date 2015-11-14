package fr.grafcet.bd;

import java.io.File;
import java.io.IOException;
import java.util.List;

/** gestion de la persistence d'un grafcet */
public abstract class GrafcetBD {

    private static GrafcetBD instance;

    public static final GrafcetBD getInstance() {
	if (null == instance) {
	    instance = new XMLGrafcetPersister();
	}
	return instance;
    }

    /** liste les grafcets sauvegardé */
    public abstract List<ExistingGrafcetDTO> getExistingGrafcet() throws IOException;

    /** Charge le grafcet */
    public abstract GrafcetDTO loadGrafcet(ExistingGrafcetDTO grafcetToLoad) throws IOException;

    /** sauvegarde de grafcet */
    public abstract void saveGrafcet(GrafcetDTO grafcetProject, File saveDirectory) throws IOException;
}
