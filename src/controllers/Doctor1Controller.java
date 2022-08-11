package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Doctor1Controller {
	 @FXML
	    private AnchorPane pane1;

	    @FXML
	    private JFXButton editRequest;

	    @FXML
	    private ImageView back;

	    @FXML
	    private TextField patientSearch;

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
	    private Label docName;

	    private Stage stage;
	    private Parent root;
	    private PreparedStatement ps;
	    DataBase_Connection conOBJ = new DataBase_Connection();
	    Connection con;
	    ResultSet resultSet=null;
	    Message msg = new Message();
	    private String UserID;
	    public void setID(String ID) throws SQLException, ClassNotFoundException {
	        con = conOBJ.getConnection();
	        String sql = "SELECT Name,doc_id FROM doctor WHERE doc_id =?";
	    	ps = con.prepareStatement(sql);
	    	ps.setString(1, ID);
	    	ResultSet rs = ps.executeQuery();
	        if(rs.next()) { 
	            String FullName = rs.getString("Name");
	            docName.setText("Dr." + FullName);
	            UserID = rs.getString("doc_id");
	        }
	    }
	    @FXML
	    void AccountSetting(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
	    	editRequest.getScene().getWindow().hide();
        	Stage PatientLogin = new Stage();
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/DoctorAccSetting.fxml"));
        	root = loader.load();
        	DoctorAccSettingControll LC = loader.getController();
	        LC.LoginDetails(UserID);
			stage  = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			PatientLogin.setResizable(false);
			PatientLogin.setScene(scene);
			PatientLogin.show();
	    	
	    }

	    @FXML
	    void AddDrugs(ActionEvent event) {

	    }

	    @FXML
	    void ClearDrugs(ActionEvent event) {

	    }

	    @FXML
	    void Logout(ActionEvent event) {
	    	logout.getScene().getWindow().hide();
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

	    @FXML
	    void RemovePrescription(ActionEvent event) {

	    }

	    @FXML
	    void ResetPrescription(ActionEvent event) {

	    }

	    @FXML
	    void SearchPatient(KeyEvent event) throws ClassNotFoundException, SQLException {
	    	String ID = patientSearch.getText();
	        con = conOBJ.getConnection();
	        String sql = "SELECT Patient_ID,Name,DOB,Weight,Height,Blood_Group FROM Patient WHERE Patient_ID =?";
	    	ps = con.prepareStatement(sql);
	    	ps.setString(1, ID);
	    	ResultSet rs = ps.executeQuery();
	        if(rs.next()) { 
//	        	System.out.println( "This is rs" + rs.getString(1));
	        	String PatientID = rs.getString("Patient_ID");
	            patient_ID.setText(PatientID);
	            String FullName = rs.getString("Name");
	            Name.setText(FullName);
	            String Birth = rs.getString("DOB");
	            DOB.setText(Birth);
	            String Weight = rs.getString("Weight");
	            weight.setText(Weight);
	            String Height = rs.getString("Height");
	            height.setText(Height);
	            String BloodGroup = rs.getString("Blood_Group");
	            Blood_Group.setText(BloodGroup);
	        }
	    }

	    @FXML
	    void SubmitPrescription(ActionEvent event) {

	    }

	    @FXML
	    void backword(MouseEvent event) throws ClassNotFoundException, SQLException {
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
