package co.edu.uniquindio.eps_uq.viewcontrollers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.eps_uq.controllers.ModelFactoryController;
import co.edu.uniquindio.eps_uq.fxmlutils.FxmlPerspective;
import co.edu.uniquindio.eps_uq.model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ShowRequestsController implements Initializable {
	
	

    @FXML
    private Button btnProgramarRequests;

	@FXML
	private VBox vboxCards;
    
    
    
    @FXML
    void programarEvent(ActionEvent event) {
    	programarAction();
    }

	private void programarAction() {
		List<Request> solicitudes= ModelFactoryController.getInstance().getRequestList();
		LocalDate fechaIniHigh= LocalDate.now();
		LocalDate fechaFinHigh= LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		
		LocalDate fechaIniMed=fechaFinHigh;
		LocalDate fechaFinMed= fechaFinHigh.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		
		LocalDate fechaIniLow=fechaFinMed;
		LocalDate fechaFinLow=fechaFinMed.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		
		
		
		
	}

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
	}

	private void agregarAction() {
		FxmlPerspective perspective;
		try {
			perspective = FxmlPerspective.loadPerspective("addRequestForm");
		} catch (IOException e) {
			return;
		}
		MainMenuController.getInstance().mostrarCapa(perspective.getPerspective());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Iterator<Request> iterator = ModelFactoryController.getInstance().getRequests().iterator();
		while (iterator.hasNext()) {
			Request r = iterator.next();
			FxmlPerspective perspective;
			try {
				perspective = FxmlPerspective.loadPerspective("requestCard");
			} catch (IOException e) {
				continue;
			}
			RequestCardController controller = (RequestCardController) perspective.getController();
			controller.setRequest(r);
			vboxCards.getChildren().add(perspective.getPerspective());
			if (iterator.hasNext())
				agregarSeparador();
		}
	}

	private void agregarSeparador() {
		try {
			vboxCards.getChildren().add(FxmlPerspective.loadPerspective("separador").getPerspective());
		} catch (IOException e) {
		}
	}

}
