package controllers;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    }

    @FXML
    void issueMedicines(ActionEvent event) {

    }

}
