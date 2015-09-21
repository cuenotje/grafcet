package fr.grafcet.data.models;

/** Donn�e commune � un grafcet */
public abstract class GElementModel {

	/** Chaine definissent l'element du grafcet */
	private String name;

	/** Renvoi la chaine d�finissant l'element */
	public String getName() {
		return name;
	}

	/** Positione la chaine d�finissant l'element */
	public void setName(String name) {
		this.name = name;
	}
}
