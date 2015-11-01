package fr.grafcet.ui.elements;

/** Donnée commune à un étape de grafcet */
public abstract class GAbstractStepUI extends GElementUI {

    public GAbstractStepUI(int gridRowIndex, int gridColumnIndex) {
	super(gridRowIndex, gridColumnIndex);
    }

    /** Numéro de l'étape */
    private String name;

    /** Numéro de l'étape */
    public String getName() {
	return name;
    }

    /** Numéro de l'étape */
    public void setName(String name) {
	this.name = name;
    }
}
