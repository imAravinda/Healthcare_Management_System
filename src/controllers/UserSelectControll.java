package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserSelectControll {
	@FXML
    private AnchorPane pane1;

    @FXML
    private Pane user2;

    @FXML
    private ImageView pane11;

    @FXML
    private Pane user1;

    @FXML
    private Pane user3;
    
    @FXML
    private ImageView back;

    @FXML
    void RegDoctor(MouseEvent event) {
    	user2.getScene().getWindow().hide();
    	Stage userselect = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/DoctorReg.fxml"));
			Scene scene = new Scene(root);
			userselect.setResizable(false);
			userselect.setScene(scene);
			userselect.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void RegPatient(MouseEvent event) {
    	user1.getScene().getWindow().hide();
    	Stage userselect = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Patient.fxml"));
			Scene scene = new Scene(root);
			userselect.setResizable(false);
			userselect.setScene(scene);
			userselect.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void RegPharmacist(MouseEvent event) {
    	user3.getScene().getWindow().hide();
    	Stage userselect = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Pharmacist.fxml"));
			Scene scene = new Scene(root);
			userselect.setResizable(false);
			userselect.setScene(scene);
			userselect.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void backword(MouseEvent event) {
    	back.getScene().getWindow().hide();
    	Stage backword = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/LoginFxml.fxml"));
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
