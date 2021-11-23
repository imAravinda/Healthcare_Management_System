package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PasswordReset extends Application {

	@Override
	public void start(Stage frogotpassword) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/PasswordReset.fxml"));
		Scene scene = new Scene(root,525,670);
		frogotpassword.setResizable(false);
		frogotpassword.setScene(scene);
		frogotpassword.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

