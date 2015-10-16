package fr.grafcet;

import javafx.stage.Stage;
import javafx.scene.Scene;
import fr.grafcet.util.IGrafcetController;

public abstract class AbstractController implements IGrafcetController {

    private Stage stage;
    private Scene view;

    @Override
    public void setScene(Scene scene) {
	this.view = scene;
    }

    protected Scene getScene() {
	return view;
    }

    protected Stage getStage() {
	return stage;
    }

    @Override
    public void setStage(Stage stage) {
	this.stage = stage;
    }
}
