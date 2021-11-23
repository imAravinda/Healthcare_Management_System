package controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorAccSettingControll {
	@FXML
    private AnchorPane pane1;

    @FXML
    private JFXButton Request;

    @FXML
    private ImageView back;

    @FXML
    private Label User_ID;

    @FXML
    private Label Name;

    @FXML
    private Label Hospital;

    @FXML
    private Label DOB;

    @FXML
    private Label Email;

    @FXML
    private Label Gender;

    @FXML
    private TextArea EditRequest;

    @FXML
    void RequestforEdit(ActionEvent event) {

    }

    @FXML
    void backword(MouseEvent event) {
    	back.getScene().getWindow().hide();
    	Stage backword = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Doctor1.fxml"));
			Scene scene = new Scene(root);
			backword.setResizable(false);
			backword.setScene(scene);
			backword.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
