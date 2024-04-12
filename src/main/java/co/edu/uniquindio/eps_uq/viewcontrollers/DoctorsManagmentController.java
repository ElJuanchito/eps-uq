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
		ModelFactoryController.getInstance().addDoctor(txtIdDoctor.getText(), txtNombreDoctor.getText());
		ModelFactoryController.getInstance().getDoctors().print();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colIdDoctor.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		colNombreDoctor.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		ObservableList<Doctor> arrayList = FXCollections.observableArrayList();
		for (Doctor doctor : ModelFactoryController.getInstance().getDoctors())
			arrayList.add(doctor);
		tblDoctores.setItems(arrayList);
	}

}
