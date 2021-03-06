package controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Pharmcist1Controll {

	@FXML
    private AnchorPane pane1;

    @FXML
    private TableColumn<?, ?> PatientNo;

    @FXML
    private TableColumn<?, ?> PatientName;

    @FXML
    private TableColumn<?, ?> PatientID;

    @FXML
    private JFXButton done;

    @FXML
    private JFXButton editRequiest;

    @FXML
    private TableColumn<?, ?> Drug;

    @FXML
    private TableColumn<?, ?> Description;

    @FXML
    private JFXButton Logout;

    @FXML
    void AccountSetting(ActionEvent event) {

    }

    @FXML
    void LogOut(ActionEvent event) {
    	Logout.getScene().getWindow().hide();
    	Stage Logout = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/LoginFxml.fxml"));
			Scene scene = new Scene(root);
			Logout.setResizable(false);
			Logout.setScene(scene);
			Logout.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void issueMedicines(ActionEvent event) {

    }

}
