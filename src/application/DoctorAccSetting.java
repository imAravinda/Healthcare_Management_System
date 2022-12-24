package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoctorAccSetting extends Application {

	@Override
	public void start(Stage DoctorAccSetting) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/DoctorAccSetting.fxml"));
		Scene scene = new Scene(root,726,717);
		DoctorAccSetting.setResizable(false);
		DoctorAccSetting.setScene(scene);
		DoctorAccSetting.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
