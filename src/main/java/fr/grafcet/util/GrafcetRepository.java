package fr.grafcet.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.grafcet.ui.elements.GInitialStepUI;

/** Cache pour les grafcet courant */
public final class GrafcetRepository {

    private static GrafcetRepository instance;
    private Map<String, GInitialStepUI> currentGraph;

    private GrafcetRepository() {
	currentGraph = new HashMap<String, GInitialStepUI>();
    }

    public static final GrafcetRepository getInstance() {
	if (null == instance) {
	    instance = new GrafcetRepository();
	}
	return instance;
    }

    public void addNewGrafcet(GInitialStepUI initialStep) {
	currentGraph.put(initialStep.getModel().getName(), initialStep);
    }

    public GInitialStepUI getGrafcet(String grafcetKey) {
	return currentGraph.get(grafcetKey);
    }

    public List<GInitialStepUI> getAll() {
	return new ArrayList<GInitialStepUI>(currentGraph.values());
    }
}
