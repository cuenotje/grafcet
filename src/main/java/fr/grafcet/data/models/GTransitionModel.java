package fr.grafcet.data.models;

/** Définition d'une transition */
public class GTransitionModel implements IGElementModel {

    private String condition;

    public String getCondition() {
	return condition;
    }

    public void setCondition(String condition) {
	this.condition = condition;
    }
}
