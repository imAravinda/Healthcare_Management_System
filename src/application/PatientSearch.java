package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PatientSearch extends Application {

	@Override
	public void start(Stage PatientSearch) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/PatientSearch.fxml"));
		Scene scene = new Scene(root,735,750);
		PatientSearch.setResizable(false);
		PatientSearch.setScene(scene);
		PatientSearch.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
