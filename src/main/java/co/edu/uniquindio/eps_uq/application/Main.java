package co.edu.uniquindio.eps_uq.application;

import co.edu.uniquindio.eps_uq.fxmlutils.FxmlPerspective;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(FxmlPerspective.loadPerspective("mainmenu").getPerspective()));
		primaryStage.show();
	}
}