package blackjack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {

	@Override
	public void start(Stage stage) {
		Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 800, 600);
        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();
	}
}
