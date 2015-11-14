package fr.grafcet.exception;

/**
 * Exception indiquant que le grafcet sur la grille n'est pas valide.
 */
public class NonValideGrafcet extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorRootCause;

    public NonValideGrafcet(String rootCause, String message) {
	super(message);
	errorRootCause = rootCause;
    }

    public String getErrorRootCause() {
	return errorRootCause;
    }

    public void setErrorRootCause(String errorRootCause) {
	this.errorRootCause = errorRootCause;
    }
}
