package fr.grafcet.util;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface IGrafcetController {

    void initController(Stage stage, Scene scene, IGrafcetController parent);

    void handleChildClosing();
    
    Scene getScene();
}
