package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserSelect extends Application {

	@Override
	public void start(Stage selectUser) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/UserSelect.fxml"));
		Scene scene = new Scene(root,803,475);
		selectUser.setResizable(false);
		selectUser.setScene(scene);
		selectUser.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
