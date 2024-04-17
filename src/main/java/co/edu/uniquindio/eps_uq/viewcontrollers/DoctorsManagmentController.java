package co.edu.uniquindio.eps_uq.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.eps_uq.controllers.ModelFactoryController;
import co.edu.uniquindio.eps_uq.model.Doctor;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DoctorsManagmentController implements Initializable {

	@FXML
	private Button btnAgregarDoctor;

	@FXML
	private TableColumn<Doctor, String> colIdDoctor;

	@FXML
	private TableColumn<Doctor, String> colNombreDoctor;

	@FXML
	private TableView<Doctor> tblDoctores;

	@FXML
	private TextField txtIdDoctor;

	@FXML
	private TextField txtNombreDoctor;

	@FXML
	void agregarDoctorEvent(ActionEvent event) {
		addDoctorAction();

	}

	private void addDoctorAction() {
		try {
			ModelFactoryController.getInstance().addDoctor(txtIdDoctor.getText().trim(),
					txtNombreDoctor.getText().trim());
			updateTable();
			ModelFactoryController.getInstance().getDoctors().print();
		} catch (Exception e) {
			new Alert(AlertType.WARNING, "El doctor con esa identificacion ya existe").show();
		}
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colIdDoctor.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		colNombreDoctor.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		updateTable();
	}

	private void updateTable() {
		ObservableList<Doctor> arrayList = FXCollections
				.observableArrayList(ModelFactoryController.getInstance().getDoctors().toList());
		tblDoctores.setItems(arrayList);
		tblDoctores.refresh();
	}

}
