package fr.grafcet.data.models;

/** Définition d'une action attaché à une étape de grafcet */
public class GActionModel implements IGElementModel {

    private String action;

    public String getAction() {
	return action;
    }

    public void setAction(String action) {
	this.action = action;
    }
}
