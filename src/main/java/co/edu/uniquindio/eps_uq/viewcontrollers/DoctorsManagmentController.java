package co.edu.uniquindio.eps_uq.viewcontrollers;

import co.edu.uniquindio.eps_uq.controllers.ModelFactoryController;
import co.edu.uniquindio.eps_uq.model.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DoctorsManagmentController {

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
    	ModelFactoryController.getInstance().addDoctor(txtIdDoctor.getText(),txtNombreDoctor.getText());
    	
    }

}
