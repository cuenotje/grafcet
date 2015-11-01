package fr.grafcet.data.models;

/** Etape initiale d'un grafcet */
public class InitialStepModel extends AbstractStepModel {

    private String projectName;

    public String getProjectName() {
	return projectName;
    }

    public void setProjectName(String projectName) {
	this.projectName = projectName;
    }
}
