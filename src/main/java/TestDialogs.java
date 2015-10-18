import fr.grafcet.ui.dialogs.Dialogs;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestDialogs extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	Dialogs.showInformationDialog(primaryStage, "message");
    }

    public static void main(String[] args) {
	Application.launch(args);
    }
}
