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

public class ManagementControll {

	@FXML
    private AnchorPane pane1;

    @FXML
    private Pane MedicalRecords;

    @FXML
    private Pane PatientRecords;

    @FXML
    private Pane DrugRecords;

    @FXML
    private ImageView back;

    @FXML
    void LinkToDrugRecords(MouseEvent event) {
    	DrugRecords.getScene().getWindow().hide();
    	Stage selectManagement = new Stage();
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/DrugSearch.fxml"));
    		Scene scene = new Scene(root);
    		selectManagement.setResizable(false);
    		selectManagement.setScene(scene);
    		selectManagement.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void LinkToMedicalRecords(MouseEvent event) {
    	MedicalRecords.getScene().getWindow().hide();
    	Stage selectManagement = new Stage();
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/RecordSearch.fxml"));
    		Scene scene = new Scene(root);
    		selectManagement.setResizable(false);
    		selectManagement.setScene(scene);
    		selectManagement.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void LinkToPatientRecords(MouseEvent event) {
    	PatientRecords.getScene().getWindow().hide();
    	Stage selectManagement = new Stage();
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/PatientSearch.fxml"));
    		Scene scene = new Scene(root);
    		selectManagement.setResizable(false);
    		selectManagement.setScene(scene);
    		selectManagement.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void backword(MouseEvent event) {

    }

}
