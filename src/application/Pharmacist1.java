package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pharmacist1 extends Application {

	@Override
	public void start(Stage Pharmacist) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Pharmacist1.fxml"));
		Scene scene = new Scene(root,1000,500);
		Pharmacist.setResizable(false);
		Pharmacist.setScene(scene);
		Pharmacist.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
