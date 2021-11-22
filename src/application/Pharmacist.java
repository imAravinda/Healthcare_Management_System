package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pharmacist extends Application {

	@Override
	public void start(Stage pharmacist) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Pharmacist.fxml"));
		Scene scene = new Scene(root,726,750);
		pharmacist.setResizable(false);
		pharmacist.setScene(scene);
		pharmacist.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
