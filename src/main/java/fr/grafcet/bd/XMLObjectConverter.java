package fr.grafcet.bd;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBElement;

import fr.grafcet.persistence.xml.Grafcet;
import fr.grafcet.persistence.xml.InitialStep;
import fr.grafcet.persistence.xml.NonValidGrafcet;
import fr.grafcet.persistence.xml.Step;
import fr.grafcet.persistence.xml.StepAction;
import fr.grafcet.persistence.xml.Transition;
import fr.grafcet.persistence.xml.ValidGrafcet;
import fr.grafcet.ui.elements.GElementUI;
import fr.grafcet.ui.elements.GInitialStepUI;
import fr.grafcet.ui.elements.GStepActionUI;
import fr.grafcet.ui.elements.GStepUI;
import fr.grafcet.ui.elements.GTransitionUI;

public final class XMLObjectConverter {

    private XMLObjectConverter() {
    }

    public final static Grafcet convertToXmlObject(GrafcetDTO grafcetDTO) {
	Grafcet g = XMLObjectFactory.getInstance().createGrafcet();
	g.setProjectName(grafcetDTO.getProjectName());
	if (null != grafcetDTO.getValidateGrafcet()) {
	    g.setValidGrafcet(convertToXmlObject(grafcetDTO.getValidateGrafcet()));
	}
	if (!grafcetDTO.getNonValidateElements().isEmpty()) {
	    g.setNonValidGrafcet(convertToXmlObject(grafcetDTO.getNonValidateElements()));
	}
	return g;
    }

    private final static ValidGrafcet convertToXmlObject(GInitialStepUI validGrafcet) {
	ValidGrafcet vGrafcet = XMLObjectFactory.getInstance().createValidGrafcet();
	InitialStep initialStep = convertFromInitialeStep(validGrafcet).getValue();
	// FIXME gerer le parsing de tout le grafcet
	vGrafcet.setInitialStep(initialStep);
	return vGrafcet;
    }

    private final static NonValidGrafcet convertToXmlObject(List<GElementUI> elements) {
	NonValidGrafcet nonValidGrafcet = XMLObjectFactory.getInstance().createNonValidGrafcet();
	for (GElementUI gElementUI : elements) {
	    JAXBElement<? extends Serializable> element = convert(gElementUI);
	    if (null != element) {
		nonValidGrafcet.getInitialStepOrTransitionOrOrTransition().add(element);
	    }
	}
	return nonValidGrafcet;
    }

    private final static JAXBElement<? extends Serializable> convert(GElementUI element) {
	JAXBElement<? extends Serializable> xmlObject = null;
	if (element instanceof GInitialStepUI) {
	    xmlObject = convertFromInitialeStep((GInitialStepUI) element);
	} else if (element instanceof GStepUI) {
	    xmlObject = convertFromStep((GStepUI) element);
	} else if (element instanceof GTransitionUI) {
	    xmlObject = convertFromTransition((GTransitionUI) element);
	} else if (element instanceof GStepActionUI) {
	    xmlObject = convertFromStepAction((GStepActionUI) element);
	}
	return xmlObject;
    }

    private final static JAXBElement<StepAction> convertFromStepAction(GStepActionUI stepActionUI) {
	StepAction action = XMLObjectFactory.getInstance().createStepAction();
	action.setGridRowIndex(stepActionUI.getGridRowIndex());
	action.setGridColumnIndex(stepActionUI.getGridColumnIndex());
	action.setAction(stepActionUI.getAction());
	return XMLObjectFactory.getInstance().createNonValidGrafcetAction(action);
    }

    private final static JAXBElement<Transition> convertFromTransition(GTransitionUI transitionUI) {
	Transition transition = XMLObjectFactory.getInstance().createTransition();
	transition.setGridRowIndex(transitionUI.getGridRowIndex());
	transition.setGridColumnIndex(transitionUI.getGridColumnIndex());
	transition.setCondition(transitionUI.getCondition());
	return XMLObjectFactory.getInstance().createNonValidGrafcetTransition(transition);
    }

    private final static JAXBElement<Step> convertFromStep(GStepUI stepUI) {
	Step step = XMLObjectFactory.getInstance().createStep();
	step.setGridRowIndex(stepUI.getGridRowIndex());
	step.setGridColumnIndex(stepUI.getGridColumnIndex());
	step.setName(stepUI.getName());
	return XMLObjectFactory.getInstance().createNonValidGrafcetStep(step);
    }

    private final static JAXBElement<InitialStep> convertFromInitialeStep(GInitialStepUI initialStepUI) {
	InitialStep istep = XMLObjectFactory.getInstance().createInitialStep();
	istep.setGridRowIndex(initialStepUI.getGridRowIndex());
	istep.setGridColumnIndex(initialStepUI.getGridColumnIndex());
	istep.setName(initialStepUI.getName());
	return XMLObjectFactory.getInstance().createNonValidGrafcetInitialStep(istep);
    }

    public static GrafcetDTO convertToObject(Grafcet grafcet) {
	GrafcetDTO result = new GrafcetDTO();
	result.setProjectName(grafcet.getProjectName());
	if (null != grafcet.getNonValidGrafcet()) {
	    for (JAXBElement<?> element : grafcet.getNonValidGrafcet().getInitialStepOrTransitionOrOrTransition()) {
		result.getNonValidateElements().add(convert(element.getValue()));
	    }
	}
	if (null != grafcet.getValidGrafcet()) {
	    InitialStep initialStep = grafcet.getValidGrafcet().getInitialStep();
	    GInitialStepUI initStepUI = convertFromInitialeStep(initialStep);
	    // FIXME construire les chainages d'element pour un grafcet validé
	    result.setValidateGrafcet(initStepUI);
	}
	return result;
    }

    private static GElementUI convert(Object element) {
	GElementUI elementUI = null;
	if (element instanceof InitialStep) {
	    elementUI = convertFromInitialeStep((InitialStep) element);
	} else if (element instanceof Step) {
	    elementUI = convertFromStep((Step) element);
	} else if (element instanceof Transition) {
	    elementUI = convertFromTransition((Transition) element);
	} else if (element instanceof StepAction) {
	    elementUI = convertFromStepAction((StepAction) element);
	}
	elementUI.initShape();
	return elementUI;
    }

    private final static GInitialStepUI convertFromInitialeStep(InitialStep initialStep) {
	GInitialStepUI istep = new GInitialStepUI(initialStep.getGridRowIndex(), initialStep.getGridColumnIndex());
	istep.setName(initialStep.getName());
	return istep;
    }

    private final static GStepUI convertFromStep(Step step) {
	GStepUI stepui = new GStepUI(step.getGridRowIndex(), step.getGridColumnIndex());
	// istep.setProjectName();
	stepui.setName(step.getName());
	return stepui;
    }

    private final static GStepActionUI convertFromStepAction(StepAction stepAction) {
	GStepActionUI actionui = new GStepActionUI(stepAction.getGridRowIndex(), stepAction.getGridColumnIndex());
	actionui.setAction(stepAction.getAction());
	return actionui;
    }

    private final static GTransitionUI convertFromTransition(Transition transition) {
	GTransitionUI transitionui = new GTransitionUI(transition.getGridRowIndex(), transition.getGridColumnIndex());
	transitionui.setCondition(transition.getCondition());
	return transitionui;
    }
}
