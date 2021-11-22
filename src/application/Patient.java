package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Patient extends Application {

	@Override
	public void start(Stage patient) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Patient.fxml"));
		Scene scene = new Scene(root,735,790);
		patient.setResizable(false);
		patient.setScene(scene);
		patient.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}