package fr.grafcet.bd;

import java.io.Serializable;

/**
 * Données permettant de lister les grafcets sauvegardés.
 */
public class ExistingGrafcetDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Nom permettant d'identifier un grafcet */
    private String identifier;

    /** Nom permettant d'identifier un grafcet */
    public String getIdentifier() {
	return identifier;
    }

    /** Nom permettant d'identifier un grafcet */
    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    @Override
    public String toString() {
	return getIdentifier();
    }
}
