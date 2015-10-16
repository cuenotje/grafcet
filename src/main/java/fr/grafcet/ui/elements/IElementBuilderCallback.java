package fr.grafcet.ui.elements;

/**
 * Callback pour construire l'element graphique à ajouter au grafcet.
 */
public interface IElementBuilderCallback {

    GElementUI handleBuild(GrafcetElementsEnum elementType);
}
