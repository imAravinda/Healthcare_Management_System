package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrugSearch extends Application {

	@Override
	public void start(Stage DrugSearch) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/DrugSearch.fxml"));
		Scene scene = new Scene(root,735,800);
		DrugSearch.setResizable(false);
		DrugSearch.setScene(scene);
		DrugSearch.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
