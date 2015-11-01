package fr.grafcet.data.models;

/** Définition d'une action attaché à une étape de grafcet */
// FIXME a renomer pour supprimer le G en debut de classe
public class GActionModel implements IGElementModel {

    private String action;

    public String getAction() {
	return action;
    }

    public void setAction(String action) {
	this.action = action;
    }
}
