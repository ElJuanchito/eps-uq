package co.edu.uniquindio.eps_uq.viewcontrollers;

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
    private TableColumn<?, ?> colIdDoctor;

    @FXML
    private TableColumn<?, ?> colNombreDoctor;

    @FXML
    private TableView<?> tblDoctores;

    @FXML
    private TextField txtIdDoctor;

    @FXML
    private TextField txtNombreDoctor;

    @FXML
    void agregarDoctorEvent(ActionEvent event) {

    }

}
