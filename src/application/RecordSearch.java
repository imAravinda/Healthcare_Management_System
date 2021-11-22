package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RecordSearch extends Application {

	@Override
	public void start(Stage RecordSearch) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/RecordSearch.fxml"));
		Scene scene = new Scene(root,850,790);
		RecordSearch.setResizable(false);
		RecordSearch.setScene(scene);
		RecordSearch.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
