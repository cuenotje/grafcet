package fr.grafcet.bd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GInitialStepUI;

/**
 * Dto de chargement d'un grafcet sauvegardé.
 */
public class GrafcetDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String projectName;
    private List<GElementUI> nonValidateElements;
    private GInitialStepUI validateGrafcet;

    public String getProjectName() {
	return projectName;
    }

    public void setProjectName(String projectName) {
	this.projectName = projectName;
    }

    public List<GElementUI> getNonValidateElements() {
	if (null == nonValidateElements) {
	    nonValidateElements = new ArrayList<GElementUI>();
	}
	return nonValidateElements;
    }

    public GInitialStepUI getValidateGrafcet() {
	return validateGrafcet;
    }

    public void setValidateGrafcet(GInitialStepUI validateGrafcet) {
	this.validateGrafcet = validateGrafcet;
    }
}
