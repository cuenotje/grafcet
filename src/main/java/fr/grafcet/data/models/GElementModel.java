package fr.grafcet.data.models;

/** Donnée commune à un grafcet */
public abstract class GElementModel {

	/** Chaine definissent l'element du grafcet */
	private String name;

	/** Renvoi la chaine définissant l'element */
	public String getName() {
		return name;
	}

	/** Positione la chaine définissant l'element */
	public void setName(String name) {
		this.name = name;
	}
}
