package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Patient1 extends Application {

	@Override
	public void start(Stage Patient) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Patient1.fxml"));
		Scene scene = new Scene(root,735,775);
		Patient.setResizable(false);
		Patient.setScene(scene);
		Patient.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
