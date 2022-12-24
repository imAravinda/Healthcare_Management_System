package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import Models.DrugsTable;
import Models.ModelTable;
import Models.Pharmacist_PatientTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Pharmcist1Controll implements Initializable{

	@FXML
    private AnchorPane pane1;

	@FXML
    private TableView<Pharmacist_PatientTable> Patient_Info;
    
    @FXML
    private TableView<DrugsTable> Drug_Info;
	  
    @FXML
    private TableColumn<Pharmacist_PatientTable, String> PatientNo;

    @FXML
    private TableColumn<Pharmacist_PatientTable, String> PatientName;

    @FXML
    private TableColumn<Pharmacist_PatientTable, String> PatientID;

    @FXML
    private JFXButton done;

    @FXML
    private JFXButton editRequiest;

    @FXML
    private TableColumn<DrugsTable, String> Drug;

    @FXML
    private TableColumn<DrugsTable, String> Description;

    @FXML
    private JFXButton Logout;
    
    private Stage stage;
    private Parent root;
    private PreparedStatement ps,ps1;
    DataBase_Connection conOBJ = new DataBase_Connection();
    Connection con;
    ResultSet resultSet=null;
    Message msg = new Message();
    private String UserID;
    
    ObservableList<DrugsTable> oblist1 = FXCollections.observableArrayList(); 
    ObservableList<Pharmacist_PatientTable> oblist2 = FXCollections.observableArrayList(); 
    public void setID(String ID) throws SQLException, ClassNotFoundException {
        con = conOBJ.getConnection();
        String sql = "SELECT Phar_ID FROM pharmacist WHERE Phar_ID =?";
    	ps = con.prepareStatement(sql);
    	ps.setString(1, ID);
    	ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            UserID = rs.getString("Phar_ID");
        }
    }
    
    
    
    @FXML
    void AccountSetting(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	System.out.println(UserID);
    	editRequiest.getScene().getWindow().hide();
    	Stage PatientLogin = new Stage();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/PharmacistAccSetting.fxml"));
    	root = loader.load();
    	PharmacistAccSettingControll LC = loader.getController();
        LC.LoginDetails(UserID);
		stage  = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		PatientLogin.setResizable(false);
		PatientLogin.setScene(scene);
		PatientLogin.show();
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

    void showDrugsandPatients() throws ClassNotFoundException, SQLException {
    	oblist1.clear();
    	oblist1.clear();
    	con = conOBJ.getConnection();
    	String sql = "SELECT Patient_ID,Patient_Name,Disease,Drugs,Description FROM PrescriptionCopy";
    	ps = con.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		oblist1.add(new DrugsTable(
    				rs.getString("Drugs"),
    				rs.getString("Description")));
            Drug_Info.setItems(oblist1);
            oblist2.add(new Pharmacist_PatientTable(
            			rs.getString("Patient_ID"),
            			rs.getString("Patient_Name"),
            			"1"
            		));
            Patient_Info.setItems(oblist2); 
    	}	
    }
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			loadData();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    private void loadData() throws ClassNotFoundException, SQLException {
    	showDrugsandPatients();
    	con = conOBJ.getConnection();
    	Drug.setCellValueFactory(cellData -> cellData.getValue().getDrugs());
    	Description.setCellValueFactory(cellData -> cellData.getValue().getDescription());
    	PatientNo.setCellValueFactory(cellData -> cellData.getValue().getNO());
    	PatientName.setCellValueFactory(cellData -> cellData.getValue().getName());
    	PatientID.setCellValueFactory(cellData -> cellData.getValue().getID());
    }
    @FXML
    void issueMedicines(ActionEvent event) throws ClassNotFoundException, SQLException {
    	con = conOBJ.getConnection();
    	String sql = "DELETE FROM PrescriptionCopy WHERE Patient_ID = 'SC/2019/11130' ";
    	ps = con.prepareStatement(sql);
    	boolean rs = ps.execute();
    	Drug.setCellValueFactory(null);
    	Description.setCellValueFactory(null);
    	PatientNo.setCellValueFactory(null);
    	PatientName.setCellValueFactory(null);
    	PatientID.setCellValueFactory(null);
    	msg.setSuccessMessage("Isuued Medicines Successfully");
    }
}
