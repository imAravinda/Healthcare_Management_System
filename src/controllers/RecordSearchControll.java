package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Data_Base.DataBase_Connection;
import Models.DrugsTable;
import Models.RecordsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class RecordSearchControll implements Initializable{
	 @FXML
	    private AnchorPane pane1;

	    @FXML
	    private ImageView back;

	    @FXML
	    private TextField searchByDate;

	    @FXML
	    private TextField searchByDrug;

	    @FXML
	    private TextField searchByPatient;
	    
	    @FXML
	    private JFXButton searchRecords;

	    @FXML
	    private TableView<RecordsTable> RecordSearchTable;
	    
	    @FXML
	    private TableColumn<RecordsTable, String> Date;

	    @FXML
	    private TableColumn<RecordsTable, String> patientID;

	    @FXML
	    private TableColumn<RecordsTable, String> patientName;

	    @FXML
	    private TableColumn<RecordsTable, String> drugName;

	    @FXML
	    private TableColumn<RecordsTable, String> Disease;

	    DataBase_Connection conOBJ = new DataBase_Connection();
	    Connection con;
	    ResultSet resultSet=null;
	    private PreparedStatement ps,ps1,ps2;
	    
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
	    
	    ObservableList<RecordsTable> oblist = FXCollections.observableArrayList();
	    @FXML
	    void SearchRecords(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	con = conOBJ.getConnection();
	    	System.out.print(searchByDrug.getText().equals(null));
	    	if(!searchByPatient.getText().equals(null)) {
	    		String PatientID = searchByPatient.getText();
	    		String sql1 = "SELECT Patient_ID,Patient_Name,Date,Disease,Drugs from prescription WHERE Patient_ID = ?";
		    	ps = con.prepareStatement(sql1);
		    	ps.setString(1, PatientID);
		    	ResultSet rs = ps.executeQuery();
		    	while(rs.next()) {
		    		oblist.add(
		    					new RecordsTable(
		    								rs.getString("Date"),
		    								rs.getString("Drugs"),
		    								rs.getString("Disease"),
		    								rs.getString("Patient_ID"),
		    								rs.getString("Patient_Name")
		    							)
		    				);
		    		drugName.setCellValueFactory(cellData -> cellData.getValue().getDrugs());
		        	Disease.setCellValueFactory(cellData -> cellData.getValue().getDisease());
		        	Date.setCellValueFactory(cellData -> cellData.getValue().getDate());
		        	patientID.setCellValueFactory(cellData -> cellData.getValue().getPatient_ID());
		        	patientName.setCellValueFactory(cellData -> cellData.getValue().getPatient_Name());
		        	RecordSearchTable.setItems(oblist);
		        	
		    	}
		    	
	    	}
	    	else if(!searchByDate.getText().equals(null)){
	    		String date = searchByDate.getText();
	    		System.out.println(date);
	    		String sql2 = "SELECT Patient_ID,Patient_Name,Date,Disease,Drugs from prescription WHERE Date = ?";
		    	ps1 = con.prepareStatement(sql2);
		    	ps1.setString(1, date);
		    	ResultSet rs1 = ps1.executeQuery();
		    	while(rs1.next()) {
		    		oblist.add(
		    					new RecordsTable(
		    								rs1.getString("Date"),
		    								rs1.getString("Drugs"),
		    								rs1.getString("Disease"),
		    								rs1.getString("Patient_ID"),
		    								rs1.getString("Patient_Name")
		    							)
		    				);
		    		drugName.setCellValueFactory(cellData -> cellData.getValue().getDrugs());
		        	Disease.setCellValueFactory(cellData -> cellData.getValue().getDisease());
		        	Date.setCellValueFactory(cellData -> cellData.getValue().getDate());
		        	patientID.setCellValueFactory(cellData -> cellData.getValue().getPatient_ID());
		        	patientName.setCellValueFactory(cellData -> cellData.getValue().getPatient_Name());
		        	RecordSearchTable.setItems(oblist);
		        	
		    	}
	    	}
	    	else if(!searchByDrug.getText().equals(null)) {
	    		String drug = searchByDrug.getText();
	    		System.out.println("pakaya");
	    		String sql3 = "SELECT Patient_ID,Patient_Name,Date,Disease,Drugs from prescription WHERE Drugs = ?";
		    	ps2 = con.prepareStatement(sql3);
		    	ps2.setString(1, drug);
		    	ResultSet rs2 = ps2.executeQuery();
		    	while(rs2.next()) {
		    		oblist.add(
		    					new RecordsTable(
		    								rs2.getString("Date"),
		    								rs2.getString("Drugs"),
		    								rs2.getString("Disease"),
		    								rs2.getString("Patient_ID"),
		    								rs2.getString("Patient_Name")
		    							)
		    				);
		    		drugName.setCellValueFactory(cellData -> cellData.getValue().getDrugs());
		        	Disease.setCellValueFactory(cellData -> cellData.getValue().getDisease());
		        	Date.setCellValueFactory(cellData -> cellData.getValue().getDate());
		        	patientID.setCellValueFactory(cellData -> cellData.getValue().getPatient_ID());
		        	patientName.setCellValueFactory(cellData -> cellData.getValue().getPatient_Name());
		        	RecordSearchTable.setItems(oblist);
		    		
		    	}
	    	}
	    	
	    	
	    	
	    }


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
		
}
