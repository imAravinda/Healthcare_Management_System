package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Management extends Application {
	@Override
	public void start(Stage Management) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Management.fxml"));
		Scene scene = new Scene(root,803,475);
		Management.setResizable(false);
		Management.setScene(scene);
		Management.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
