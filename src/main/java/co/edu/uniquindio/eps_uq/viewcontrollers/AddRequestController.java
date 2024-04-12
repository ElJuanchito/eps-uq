package co.edu.uniquindio.eps_uq.viewcontrollers;

import co.edu.uniquindio.eps_uq.model.Doctor;
import co.edu.uniquindio.eps_uq.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddRequestController {

	@FXML
	private TableColumn<Doctor, String> colIdentificacionDoctor, colNombreDoctor;

	@FXML
	private TableColumn<User, String> colIdentificacionUsuario, colNombreUsuario;

	@FXML
	private TableView<Doctor> tblDoctor;

	@FXML
	private TableView<User> tblUsuarios;

	@FXML
	private TextArea tfDetails;

	@FXML
	private TextField tfHours, tfMins;

	@FXML
	void agregarEvent(ActionEvent event) {

	}

	@FXML
	void cancelarEvent(ActionEvent event) {
		MainMenuController.getInstance().ocultarCapa();
	}

}
