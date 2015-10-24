import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestDessin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	Pane pane = new Pane();
	pane.setMinHeight(40);
	pane.setMinWidth(40);

	Polyline pl = new Polyline(2, 2, 38, 2, 38, 38, 2, 38, 2, 2);
	pane.getChildren().add(pl);
	Polyline pl2 = new Polyline(8, 8, 32, 8, 32, 32, 8, 32, 8, 8);
	pane.getChildren().add(pl2);

	Line l = new Line(20, 38, 20, 40);
	pane.getChildren().add(l);

	Text t = new Text(12, 25, "10");
	pane.getChildren().add(t);

	Scene s = new Scene(pane);
	primaryStage.setScene(s);
	primaryStage.show();
    }

    public static void main(String[] args) {
	Application.launch(args);
    }
}
