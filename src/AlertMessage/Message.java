package AlertMessage;

import javafx.scene.control.Alert;

public class Message {
	public void setMessage(String str) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(str);
		alert.show();
	}
	public void setSuccessMessage(String str) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(str);
		alert.show();
	}
	public void setWarningMessage(String str) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(str);
		alert.show();
	}
	public void setInformationMessage(String str) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(str);
		alert.show();
	}
}
