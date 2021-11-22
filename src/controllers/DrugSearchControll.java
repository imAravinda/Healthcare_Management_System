package controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DrugSearchControll {
	@FXML
    private AnchorPane pane1;

    @FXML
    private JFXButton addDrugDetails;

    @FXML
    private ImageView back;

    @FXML
    private TextField searchByBrand;

    @FXML
    private TextField brandName;

    @FXML
    private TextField searchByDrug;

    @FXML
    private TextField searchBySupplier;

    @FXML
    private JFXButton searchDrug;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> existingDrugBrand;

    @FXML
    private TableColumn<?, ?> existingDrug;

    @FXML
    private TableColumn<?, ?> existingDrugQty;

    @FXML
    private TableColumn<?, ?> existingDrugSupplier;

    @FXML
    private JFXButton editExistingdrug;

    @FXML
    private JFXButton removeExistingDrug;

    @FXML
    private TextField supplierName;

    @FXML
    private TextField drugName;

    @FXML
    private TextField drugQuantity;

    @FXML
    void AddDrugDetails(ActionEvent event) {

    }

    @FXML
    void Backword(MouseEvent event) {
    	back.getScene().getWindow().hide();
    	Stage backword = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Management.fxml"));
			Scene scene = new Scene(root);
			backword.setResizable(false);
			backword.setScene(scene);
			backword.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void EditExistingdrug(ActionEvent event) {

    }

    @FXML
    void EditPatientInfo(ActionEvent event) {

    }

    @FXML
    void RemoveExistingDrug(ActionEvent event) {

    }

    @FXML
    void SearchPatient(KeyEvent event) {

    }
}
