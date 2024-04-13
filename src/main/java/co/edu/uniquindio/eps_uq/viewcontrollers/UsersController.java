package co.edu.uniquindio.eps_uq.viewcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.eps_uq.controllers.ModelFactoryController;
import co.edu.uniquindio.eps_uq.model.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<User, String> colAge;

    @FXML
    private TableColumn<User, String> colId;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableView<User> tblUsers;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private ObservableList<User> listaObservable;

    @FXML
    void addUserEvent(ActionEvent event) {
        addUserAction();
    }

    @FXML
    void removeUserEvent(ActionEvent event) {
        removeUserAction();
    }

    @FXML
    void updateUserEvent(ActionEvent event) {
        updateUserAction();
    }

    @FXML
    void initialize() {
        updateTable();
    }

    private void addUserAction() {
        ModelFactoryController.getInstance().addUser(txtId.getText(), txtName.getText(), Integer.getInteger(txtAge.getText()));
        updateTable();
    }

    private void removeUserAction() {
        User user = tblUsers.getSelectionModel().getSelectedItem();
        ModelFactoryController.getInstance().removeUser(user);
        updateTable();
    }

    private void updateUserAction() {
        User user = tblUsers.getSelectionModel().getSelectedItem();
        ModelFactoryController.getInstance().updateUser(user);
        updateTable();
    }

    private void updateTable() {
        listaObservable = FXCollections.observableList(ModelFactoryController.getInstance().getUsers());
        tblUsers.setItems(listaObservable);
        colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
        colName.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getName()));
        colAge.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getAge().toString()));
        tblUsers.refresh();
    }

}
