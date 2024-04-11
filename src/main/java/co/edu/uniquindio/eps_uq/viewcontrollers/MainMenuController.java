package co.edu.uniquindio.eps_uq.viewcontrollers;

import java.io.IOException;

import co.edu.uniquindio.eps_uq.fxmlutils.FxmlPerspective;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainMenuController {

	@FXML
	private BorderPane centerPane;

	@FXML
	void citasEvent(ActionEvent event) {
		citasAction();
	}

	private void citasAction() {
		try {
			centerPane.setCenter(FxmlPerspective.loadPerspective("showappointment").getPerspective());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void cronogramaEvent(ActionEvent event) {

	}

	@FXML
	void usuariosEvent(ActionEvent event) {

	}

}
