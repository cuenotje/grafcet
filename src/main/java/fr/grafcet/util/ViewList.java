package fr.grafcet.util;

/** Enumeration listant les vues de l'application */
public enum ViewList {

	/** Vue principale */
	MAIN_VIEW("view/menuBar.fxml", "bundles/main"),
	/** Vue de construction d'un grafcet */
	GRAFCET_BUILDER_VIEW("view/builderView.fxml", "bundles/builder"),
	/** Vue de simulation d'un grafcet */
	GRAFCET_SIMULATOR_VIEW("view/simulatorView.fxml", "bundles/simulator");

	private String viewUrl, bundleUrl;

	private ViewList(String viewUrl, String bundleUrl) {
		this.viewUrl = viewUrl;
		this.bundleUrl = bundleUrl;
	}

	/** Chemin vers la vue */
	public String getViewUrl() {
		return viewUrl;
	}

	/** Chemin vers le bundle associé */
	public String getBundleUrl() {
		return bundleUrl;
	}
}
