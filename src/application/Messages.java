package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Messages extends Application {

	@Override
	public void start(Stage Messages) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Messages.fxml"));
		Scene scene = new Scene(root,913,562);
		Messages.setResizable(false);
		Messages.setScene(scene);
		Messages.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
