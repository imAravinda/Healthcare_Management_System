package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Doctor extends Application {

	@Override
	public void start(Stage doctorReg) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/DoctorReg.fxml"));
		Scene scene = new Scene(root,726,750);
		doctorReg.setResizable(false);
		doctorReg.setScene(scene);
		doctorReg.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

