package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Admin extends Application {

	@Override
	public void start(Stage Admin) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Admin.fxml"));
		Scene scene = new Scene(root,707,475);
		Admin.setResizable(false);
		Admin.setScene(scene);
		Admin.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
