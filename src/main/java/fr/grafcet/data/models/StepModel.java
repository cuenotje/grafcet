package fr.grafcet.data.models;

/** Etape d'un grafcet */
public class StepModel extends AbstractStepModel {

    private GActionModel action;

    public GActionModel getAction() {
	return action;
    }

    public void setAction(GActionModel action) {
	this.action = action;
    }
}
