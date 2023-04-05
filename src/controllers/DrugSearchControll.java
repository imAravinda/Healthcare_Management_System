package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import Models.Drugs;
import Models.DrugsSearch;
import Models.PrescriptionTable;
import Models.RecordsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    private TableView<DrugsSearch> DrugsTable;
    
    @FXML
    private TableColumn<DrugsSearch, String> date;

    @FXML
    private TableColumn<DrugsSearch, String> existingDrugBrand;

    @FXML
    private TableColumn<DrugsSearch, String> existingDrug;

    @FXML
    private TableColumn<DrugsSearch, String> existingDrugQty;

    @FXML
    private TableColumn<DrugsSearch, String> existingDrugSupplier;

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

    private PreparedStatement ps;
    Connection con;
    DataBase_Connection conOBJ = new DataBase_Connection();
    ResultSet resultSet=null;
    Message msg = new Message();
    private PreparedStatement psCheckUserExists=null;
    @FXML
    void AddDrugDetails(ActionEvent event) throws ClassNotFoundException, SQLException {
    	con = conOBJ.getConnection();
    	psCheckUserExists=con.prepareStatement("SELECT * FROM Drugs WHERE Drug_Name=? AND Brand = ?");
        psCheckUserExists.setString(1,drugName.getText());
        psCheckUserExists.setString(2,brandName.getText());
        resultSet= psCheckUserExists.executeQuery();
    	String DrugName = drugName.getText();
    	String Brand = brandName.getText();
    	String Supplier = supplierName.getText();
    	int Quantity = Integer.parseInt(drugQuantity.getText());
    	if(resultSet.isBeforeFirst()) {
    		msg.setInformationMessage("This Drug already exist!");
    	}else {
    			String insert = "INSERT INTO Drugs (Brand,Supplier,Drug_Name,Quantity) " + "VALUES (?,?,?,?)";
	    		ps = con.prepareStatement(insert);
		    	ps.setString(1, Brand);
		    	ps.setString(2, Supplier);
		    	ps.setInt(4, Quantity);
		    	ps.setString(3, DrugName);
		    	ps.executeUpdate();
		    	msg.setSuccessMessage("Drug is Added!");
    		}
    }
    ObservableList<DrugsSearch> oblist2 = FXCollections.observableArrayList();
    @FXML
    void SearchByBrand(KeyEvent event) throws SQLException, ClassNotFoundException {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		con = conOBJ.getConnection();
        	if(!searchByBrand.getText().equals(null)) {
        		String PatientID = searchByBrand.getText();
        		String sql1 = "SELECT Drug_Name,Date,Brand,Supplier,Quantity from drugs WHERE Brand = ?";
    	    	ps = con.prepareStatement(sql1);
    	    	ps.setString(1, PatientID);
    	    	ResultSet rs = ps.executeQuery();
    	    	while(rs.next()) {
    	    		oblist2.add(
    	    					new DrugsSearch(
    	    								rs.getString("Date"),
    	    								rs.getString("Drug_Name"),
    	    								rs.getString("Brand"),
    	    								rs.getString("Quantity"),
    	    								rs.getString("Supplier")
    	    							)
    	    				);
    	    		existingDrug.setCellValueFactory(cellData -> cellData.getValue().getDrugName());
    	    		existingDrugBrand.setCellValueFactory(cellData -> cellData.getValue().getBrand());
    	    		existingDrugQty.setCellValueFactory(cellData -> cellData.getValue().getQty());
    	    		existingDrugSupplier.setCellValueFactory(cellData -> cellData.getValue().getSupplier());
    	        	date.setCellValueFactory(cellData -> cellData.getValue().getDate());
    	        	DrugsTable.setItems(oblist2);
    	    	}
    	    	
        	}
    	}
    }

    @FXML
    void SearchByDrugName(KeyEvent event) throws ClassNotFoundException, SQLException {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		con = conOBJ.getConnection();
        	
        	if(!searchByDrug.getText().equals(null)) {
        		String PatientID = searchByDrug.getText();
        		String sql1 = "SELECT Drug_Name,Date,Brand,Supplier,Quantity from drugs WHERE Drug_Name = ?";
    	    	ps = con.prepareStatement(sql1);
    	    	ps.setString(1, PatientID);
    	    	ResultSet rs = ps.executeQuery();
    	    	while(rs.next()) {
    	    		oblist2.add(
    	    					new DrugsSearch(
    	    								rs.getString("Date"),
    	    								rs.getString("Drug_Name"),
    	    								rs.getString("Brand"),
    	    								rs.getString("Quantity"),
    	    								rs.getString("Supplier")
    	    							)
    	    				);
    	    		existingDrug.setCellValueFactory(cellData -> cellData.getValue().getDrugName());
    	    		existingDrugBrand.setCellValueFactory(cellData -> cellData.getValue().getBrand());
    	    		existingDrugQty.setCellValueFactory(cellData -> cellData.getValue().getQty());
    	    		existingDrugSupplier.setCellValueFactory(cellData -> cellData.getValue().getSupplier());
    	        	date.setCellValueFactory(cellData -> cellData.getValue().getDate());
    	        	DrugsTable.setItems(oblist2);
    	    	}
    	    	
        	}
    	}
    }

    @FXML
    void searchBySupplier(KeyEvent event) throws ClassNotFoundException, SQLException {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		con = conOBJ.getConnection();
        	if(!searchBySupplier.getText().equals(null)) {
        		String PatientID = searchBySupplier.getText();
        		String sql1 = "SELECT Drug_Name,Date,Brand,Supplier,Quantity from drugs WHERE Drug_Name = ?";
    	    	ps = con.prepareStatement(sql1);
    	    	ps.setString(1, PatientID);
    	    	ResultSet rs = ps.executeQuery();
    	    	while(rs.next()) {
    	    		oblist2.add(
    	    					new DrugsSearch(
    	    								rs.getString("Date"),
    	    								rs.getString("Drug_Name"),
    	    								rs.getString("Brand"),
    	    								rs.getString("Quantity"),
    	    								rs.getString("Supplier")
    	    							)
    	    				);
    	    		existingDrug.setCellValueFactory(cellData -> cellData.getValue().getDrugName());
    	    		existingDrugBrand.setCellValueFactory(cellData -> cellData.getValue().getBrand());
    	    		existingDrugQty.setCellValueFactory(cellData -> cellData.getValue().getQty());
    	    		existingDrugSupplier.setCellValueFactory(cellData -> cellData.getValue().getSupplier());
    	        	date.setCellValueFactory(cellData -> cellData.getValue().getDate());
    	        	DrugsTable.setItems(oblist2);
    	    	}
    	    	
        	}
    	}
    }
    @FXML
    void searchDrugs(ActionEvent event) {

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

//    @FXML
//    void EditPatientInfo(ActionEvent event) {
//
//    }

    @FXML
    void RemoveExistingDrug(ActionEvent event) {

    }

//    @FXML
//    void SearchPatient(KeyEvent event) {
//
//    }
}
