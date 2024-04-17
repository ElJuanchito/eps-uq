package co.edu.uniquindio.eps_uq.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.eps_uq.controllers.ModelFactoryController;
import co.edu.uniquindio.eps_uq.model.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UsersController implements Initializable {


	@FXML
	private TableColumn<User, String> colAge, colId, colName;

	@FXML
	private TableView<User> tblUsers;

	@FXML
	private TextField txtAge, txtId, txtName;

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

	private void addUserAction() {
		try {
			ModelFactoryController.getInstance().addUser(txtId.getText(), txtName.getText(),
					Integer.parseInt(txtAge.getText()));
			updateTable();
		} catch (NumberFormatException e) {
			new Alert(AlertType.WARNING, "Verifica la edad del usuario").show();
		} catch (IllegalArgumentException e) {
			new Alert(AlertType.WARNING, "El usuario ya existe").show();
		}

	}

	private void removeUserAction() {
		User user = tblUsers.getSelectionModel().getSelectedItem();

		if (user == null) {
			new Alert(AlertType.WARNING, "Recuerda seleccionar el usuario en la tabla").show();
			return;
		}
		ModelFactoryController.getInstance().removeUser(user);
		updateTable();
	}

	private void updateUserAction() {
		User user = tblUsers.getSelectionModel().getSelectedItem();
		if (user == null) {
			new Alert(AlertType.WARNING, "Recuerda seleccionar el usuario en la tabla").show();
			return;
		}
		ModelFactoryController.getInstance().updateUser(user);
		updateTable();
	}

	private void updateTable() {
		listaObservable = FXCollections.observableList(ModelFactoryController.getInstance().getUsers());
		tblUsers.setItems(listaObservable);
		tblUsers.refresh();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		colName.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getName()));
		colAge.setCellValueFactory(e -> {
			final Integer age = e.getValue().getAge();
			return new ReadOnlyStringWrapper(age == null ? "" : age.toString());
		});
		updateTable();
	}

}
