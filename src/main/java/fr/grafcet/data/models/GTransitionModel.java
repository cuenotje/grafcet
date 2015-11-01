package fr.grafcet.data.models;

/** Définition d'une transition */
// FIXME renomer pour supprimer le G du debut de classe
public class GTransitionModel implements IGElementModel {

    private String condition;

    public String getCondition() {
	return condition;
    }

    public void setCondition(String condition) {
	this.condition = condition;
    }
}
