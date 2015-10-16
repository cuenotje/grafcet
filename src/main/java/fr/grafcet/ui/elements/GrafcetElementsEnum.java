package fr.grafcet.ui.elements;

/**
 * Elements pouvant composer un grafcet
 */
public enum GrafcetElementsEnum {

    /** etape initiale */
    INITIAL_STEP("initialStepButton");

    private String fxmlId;

    /**
     * @param fxmlId
     *            id de l'element referencer dans le fxml des ecrans.
     */
    private GrafcetElementsEnum(String fxmlId) {
	this.fxmlId = fxmlId;
    }

    public String getFxmlId() {
	return fxmlId;
    }

    public GrafcetElementsEnum getEnum(String fxmlId) {
	GrafcetElementsEnum[] values = values();
	for (GrafcetElementsEnum grafcetElementsEnum : values) {
	    if (grafcetElementsEnum.getFxmlId().equalsIgnoreCase(fxmlId)) {
		return grafcetElementsEnum;
	    }
	}
	return null;
    }
}