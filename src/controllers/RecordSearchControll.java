package controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RecordSearchControll {
	 @FXML
	    private AnchorPane pane1;

	    @FXML
	    private ImageView back;

	    @FXML
	    private TextField searchByPatient;

	    @FXML
	    private TextField searchByDrug;

	    @FXML
	    private TextField searchByDate;

	    @FXML
	    private JFXButton searchRecords;

	    @FXML
	    private TableView<?> RecordSearchTable;
	    
	    @FXML
	    private TableColumn<?, ?> date;

	    @FXML
	    private TableColumn<?, ?> patientID;

	    @FXML
	    private TableColumn<?, ?> patientName;

	    @FXML
	    private TableColumn<?, ?> drugName;

	    @FXML
	    private TableColumn<?, ?> disease;

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
	    void SearchDate(KeyEvent event) {

	    }

	    @FXML
	    void SearchDrug(KeyEvent event) {

	    }

	    @FXML
	    void SearchPatient(KeyEvent event) {

	    }

	    @FXML
	    void SearchRecords(ActionEvent event) {

	    }
}
