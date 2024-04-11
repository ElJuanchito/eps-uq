package co.edu.uniquindio.eps_uq.fxmlutils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FxmlPerspective {
	private Object controller;
	private Parent perspective;

	public static FxmlPerspective loadPerspective(String fxml) throws IOException {
		System.out.println(fxml);
		FXMLLoader fxmlLoader = new FXMLLoader(FxmlPerspective.class.getResource("/fxml/" + fxml + ".fxml"));
		Parent load = fxmlLoader.load();
		return new FxmlPerspective(fxmlLoader.getController(), load);
	}
}
