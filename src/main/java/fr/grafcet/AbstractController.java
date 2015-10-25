package fr.grafcet;

import fr.grafcet.persistence.GrafcetPersistenceController;
import fr.grafcet.util.IGrafcetController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController implements IGrafcetController {

    private Stage stage;
    private Scene view;
    private IGrafcetController parent;
    private GrafcetPersistenceController persistenceController;

    @Override
    public void initController(Stage stage, Scene scene, IGrafcetController parent) {
	this.stage = stage;
	this.view = scene;
	this.parent = parent;
    }

    @Override
    public Scene getScene() {
	return view;
    }

    protected Stage getStage() {
	return stage;
    }

    protected void closeView() {
	parent.handleChildClosing();
    }

    @Override
    public void handleChildClosing() {
    }

    protected GrafcetPersistenceController getPersistenceController() {
	if (null == persistenceController) {
	    persistenceController = new GrafcetPersistenceController(getStage());
	}
	return persistenceController;
    }
}
