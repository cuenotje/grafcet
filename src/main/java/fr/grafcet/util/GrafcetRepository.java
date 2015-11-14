package fr.grafcet.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GInitialStepUI;

/** Cache pour les grafcet courant */
public final class GrafcetRepository {

    private static GrafcetRepository instance;
    private Map<String, GInitialStepUI> validGrafcet;
    private Set<GElementUI> nonValidElement;

    private GrafcetRepository() {
	validGrafcet = new HashMap<String, GInitialStepUI>();
	nonValidElement = new HashSet<GElementUI>();
    }

    public static final GrafcetRepository getInstance() {
	if (null == instance) {
	    instance = new GrafcetRepository();
	}
	return instance;
    }

    public void addValidGrafcet(GInitialStepUI initialStep) {
	validGrafcet.put(initialStep.getName(), initialStep);
    }

    public GInitialStepUI getValidGrafcet(String grafcetKey) {
	return validGrafcet.get(grafcetKey);
    }

    public List<GInitialStepUI> getAllValidGrafcet() {
	return new ArrayList<GInitialStepUI>(validGrafcet.values());
    }

    public void addNonValidElement(GElementUI element) {
	nonValidElement.add(element);
    }

    public boolean removeNonValidElement(GElementUI element) {
	return nonValidElement.remove(element);
    }

    public List<GElementUI> getAllNonValidElement() {
	return Arrays.<GElementUI> asList(nonValidElement.toArray(new GElementUI[nonValidElement.size()]));
    }
}
