package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Doctor1 extends Application {

	@Override
	public void start(Stage doctor) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Doctor1.fxml"));
		Scene scene = new Scene(root,1300,700);
		doctor.setResizable(false);
		doctor.setScene(scene);
		doctor.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
