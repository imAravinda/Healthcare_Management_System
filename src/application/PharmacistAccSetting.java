package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PharmacistAccSetting extends Application {

	@Override
	public void start(Stage PharmacistAccSetting) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/PharmacistAccSetting.fxml"));
		Scene scene = new Scene(root,726,717);
		PharmacistAccSetting.setResizable(false);
		PharmacistAccSetting.setScene(scene);
		PharmacistAccSetting.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
