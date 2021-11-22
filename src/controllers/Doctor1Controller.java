package controllers;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Doctor1Controller {
	@FXML
    private AnchorPane pane1;

    @FXML
    private JFXButton editRequest;

    @FXML
    private ImageView back;

    @FXML
    private TextField docID1;

    @FXML
    private TableColumn<?, ?> Date;

    @FXML
    private TableColumn<?, ?> Drugs;

    @FXML
    private TableColumn<?, ?> Diseases;

    @FXML
    private TextField disease;

    @FXML
    private TextField drugSearch;

    @FXML
    private TextField drugDuration;

    @FXML
    private JFXButton addDrug;

    @FXML
    private JFXButton clearDrug;

    @FXML
    private TableColumn<?, ?> Drugs1;

    @FXML
    private TableColumn<?, ?> Diseases1;

    @FXML
    private JFXButton resetPrescription;

    @FXML
    private JFXButton removePrescription;

    @FXML
    private JFXButton SubmitPrescription;

    @FXML
    private JFXButton logout;

    @FXML
    private Label patient_ID;

    @FXML
    private Label Name;

    @FXML
    private Label Blood_Group;

    @FXML
    private Label height;

    @FXML
    private Label weight;

    @FXML
    private Label DOB;

    @FXML
    void AccountSetting(ActionEvent event) {

    }

    @FXML
    void AddDrugs(ActionEvent event) {

    }

    @FXML
    void ClearDrugs(ActionEvent event) {

    }

    @FXML
    void Logout(ActionEvent event) {

    }

    @FXML
    void RemovePrescription(ActionEvent event) {

    }

    @FXML
    void ResetPrescription(ActionEvent event) {

    }

    @FXML
    void SubmitPrescription(ActionEvent event) {

    }

    @FXML
    void backword(MouseEvent event) {

    }

}
