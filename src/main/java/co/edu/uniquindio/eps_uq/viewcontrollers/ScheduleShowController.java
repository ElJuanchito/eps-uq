package co.edu.uniquindio.eps_uq.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.eps_uq.controllers.ModelFactoryController;
import co.edu.uniquindio.eps_uq.model.Appointment;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ScheduleShowController implements Initializable {

	@FXML
	private TableColumn<Appointment, String> colDateAppointment;

	@FXML
	private TableColumn<Appointment, String> colDetailAppointment;

	@FXML
	private TableColumn<Appointment, String> colDoctorAppointment;

	@FXML
	private TableColumn<Appointment, String> colIDurationAppointment;

	@FXML
	private TableColumn<Appointment, String> colIdAppointment;

	@FXML
	private TableColumn<Appointment, String> colNombreUserAppointment;

	@FXML
	private TableColumn<Appointment, String> colPriorityAppointment;

	@FXML
	private TableView<Appointment> tblAppointments;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		updateTable();
		

	}

	public void updateTable() {
		tblAppointments
				.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance().getAppointments()));

		colIdAppointment.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getId()));
		colNombreUserAppointment
				.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getUser().getName()));
		colDateAppointment.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getDate().toString()));
		colPriorityAppointment
				.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getPriorityLevel().toString()));
		colDoctorAppointment
				.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getDoctor().getNombre()));
		colIDurationAppointment.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.format("Duración: %s:%s h",
				cell.getValue().getDuration().toHours() < 10 ? "0" + cell.getValue().getDuration().toHours()
						: cell.getValue().getDuration().toHours(),
				cell.getValue().getDuration().toMinutesPart() < 10 ? "0" + cell.getValue().getDuration().toMinutesPart()
						: cell.getValue().getDuration().toMinutesPart())));
		colDetailAppointment.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getDetail()));

		tblAppointments.refresh();
	}

}
