package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

	@Override
	public void start(Stage login) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/LoginFxml.fxml"));
		Scene scene = new Scene(root,882,575);
		login.setResizable(false);
		login.setScene(scene);
		login.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
