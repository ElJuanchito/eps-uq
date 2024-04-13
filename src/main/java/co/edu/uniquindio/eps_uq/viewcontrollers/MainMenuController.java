package co.edu.uniquindio.eps_uq.viewcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.eps_uq.fxmlutils.FxmlPerspective;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class MainMenuController implements Initializable {

	@FXML
	private BorderPane centerPane, secondLayer, secondLayerInner;
	private static MainMenuController instance;
	private FadeTransition fade;

	public MainMenuController() {
		instance = this;
	}

	@FXML
	void citasEvent(ActionEvent event) {
		citasAction();
	}

	@FXML
	void cronogramaEvent(ActionEvent event) {
		cronogramaAction();
	}

	@FXML
	void usuariosEvent(ActionEvent event) {
		usuariosAction();
	}

	private void usuariosAction() {
		cronogramaAction();
	}

	@FXML
	void doctoresEvent(ActionEvent event) {
		doctoresAction();
	}

	private void citasAction() {
		try {
			centerPane.setCenter(FxmlPerspective.loadPerspective("showappointment").getPerspective());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void cronogramaAction() {
		try {
			centerPane.setCenter(FxmlPerspective.loadPerspective("scheduleshow").getPerspective());
		} catch (IOException e) {
		}
	}

	private void doctoresAction() {
		try {
			centerPane.setCenter(FxmlPerspective.loadPerspective("doctormanagement").getPerspective());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MainMenuController getInstance() {
		return instance;
	}

	public void mostrarCapa(Parent persp) {
		if (fade == null)
			fade = new FadeTransition(Duration.millis(100), secondLayer);
		secondLayer.setDisable(false);
		secondLayerInner.setCenter(persp);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.playFromStart();

	}

	public void ocultarCapa() {
		if (fade == null)
			fade = new FadeTransition(Duration.millis(100), secondLayer);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished(e -> {
			secondLayerInner.setCenter(null);
			secondLayer.setDisable(true);
		});
		fade.playFromStart();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		centerPane.widthProperty().addListener((obs, oldV, newV) -> {
			secondLayerInner.setMaxWidth(newV.doubleValue() * 0.75);
		});
	}

}
