package co.edu.uniquindio.eps_uq.viewcontrollers;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import co.edu.uniquindio.eps_uq.controllers.ModelFactoryController;
import co.edu.uniquindio.eps_uq.model.Doctor;
import co.edu.uniquindio.eps_uq.model.PriorityLevel;
import co.edu.uniquindio.eps_uq.model.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddRequestController implements Initializable {

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
	private ComboBox<String> comboPrioridad;

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
	}

	private void agregarAction() {
		String requestMsg = createRequest();
		if (requestMsg == null) {
			new Alert(AlertType.INFORMATION, "La cita ha sido agregada con éxito").show();
			ShowRequestsController.getInstance().updateRequests();
		} else
			new Alert(AlertType.INFORMATION, requestMsg).show();
	}

	private String createRequest() {
		Doctor doctor = tblDoctor.getSelectionModel().getSelectedItem();
		User user = tblUsuarios.getSelectionModel().getSelectedItem();
		if (doctor == null)
			return "Selecciona un Doctor";
		if (user == null)
			return "Selecciona un usuario";
		int horas;
		try {
			horas = Integer.parseInt(tfHours.getText().trim());
			if (horas < 0)
				return "Las horas no pueden ser negativas";
		} catch (NumberFormatException e) {
			return "Escribe bien las horas";
		}
		int mins;
		try {
			mins = Integer.parseInt(tfMins.getText().trim());
			if (mins < 0)
				return "Los minutos no pueden ser negativos";
			if (mins == 0 && horas == 0)
				return "El tiempo no puede ser 0";
		} catch (NumberFormatException e) {
			return "Escribe bien los minutos";
		}
		String details = tfDetails.getText().trim();
		if (details.isEmpty())
			return "Escribe los detalles";
		PriorityLevel priorityLevel = PriorityLevel.valueByText(comboPrioridad.getValue());
		if (priorityLevel == null)
			return "Elige un nivel de prioridad";
		ModelFactoryController.getInstance().addRequest(user, priorityLevel, doctor, details,
				Duration.ofMinutes(mins + horas * 60));
		return null;
	}

	@FXML
	void cancelarEvent(ActionEvent event) {
		MainMenuController.getInstance().ocultarCapa();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colIdentificacionDoctor.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		colNombreDoctor.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		colIdentificacionUsuario.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		colNombreUsuario.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getName()));
		comboPrioridad.setItems(FXCollections.observableArrayList(PriorityLevel.textValues()));
		updateDoctorsTable();
		updateUsersTable();
	}

	private void updateDoctorsTable() {
		tblDoctor.setItems(
				FXCollections.observableArrayList(ModelFactoryController.getInstance().getDoctors().toList()));
		tblDoctor.refresh();
	}

	private void updateUsersTable() {
		tblUsuarios.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance().getUsers()));
		tblUsuarios.refresh();
	}

}
