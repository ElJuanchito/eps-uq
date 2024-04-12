package co.edu.uniquindio.eps_uq.viewcontrollers;

import co.edu.uniquindio.eps_uq.model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class RequestCardController {

	@FXML
	private Label lblNombre, lblPrior, lblTiempo;

	@FXML
	void cardClickedEvent(MouseEvent event) {
	}

	@FXML
	void deleteRequestEvent(ActionEvent event) {
	}

	public void setRequest(Request r) {
		lblNombre.setText(r.getUser().getName());
		lblPrior.setText(r.getPriorityLevel().getValue() + "");
		int mins = r.getDuration().toMinutesPart();
		long hours = r.getDuration().toHours();
		lblTiempo.setText(String.format("Duración: %s:%s h", hours < 10 ? "0" + hours : hours, mins < 10 ? "0" + mins : mins));
	}

}
