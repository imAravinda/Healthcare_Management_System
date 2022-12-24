package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PatientSearchControll {
	@FXML
    private AnchorPane pane1;

    @FXML
    private TextField userID;

    @FXML
    private TextField patientName;

    @FXML
    private TextField patientEmail;

    @FXML
    private TextField patientWeight;

    @FXML
    private TextField patientHeight;

    @FXML
    private TextArea Others;

    @FXML
    private JFXButton removePatient;

    @FXML
    private ImageView back;

    @FXML
    private TextField SearchPatient;

    @FXML
    private JFXButton updatePatient;

    @FXML
    private JFXButton editPatientInfo;

    @FXML
    private TextField gender;
    
    @FXML
    private TextField DOB;
    
    @FXML
    private TextField Blood_Group;
    
    private Stage stage;
    private Parent root;
    private PreparedStatement ps,ps1,ps2;
    DataBase_Connection conOBJ = new DataBase_Connection();
    Connection con;
    ResultSet resultSet=null;
    Message msg = new Message();
    private String UserID;
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
    void EditPatientInfo(ActionEvent event) {
    	if(!editPatientInfo.isPressed()) {
    		userID.setEditable(true);
        	patientName.setEditable(true);
        	patientEmail.setEditable(true);
        	DOB.setEditable(true);
        	patientWeight.setEditable(true);
        	patientHeight.setEditable(true);
        	Blood_Group.setEditable(true);
        	gender.setEditable(true);
        	Others.setEditable(true);
    	}
    }

    @FXML
    void RemovePatient(ActionEvent event) throws ClassNotFoundException, SQLException {
    	con = conOBJ.getConnection();
    	String sql = "SET foreign_key_checks = 0";
    	String delete = "DELETE from Patient WHERE Patient_ID = ?";
    	String sql1 = "SET foreign_key_checks = 1";
    	ps1 = con.prepareStatement(sql);
    	ps = con.prepareStatement(delete);
    	ps2 = con.prepareStatement(sql1);
    	ps.setString(1, userID.getText());
    	ResultSet rs1 = ps1.executeQuery();
    	int rs = ps.executeUpdate();
    	ResultSet rs2 = ps2.executeQuery();
    	msg.setSuccessMessage("Patient is Removed!");
//    	userID.setText(null);
//        patientName.setText(null);        
//        DOB.setText(null);        
//        patientWeight.setText(null);        
//        patientHeight.setText(null);    
//        Blood_Group.setText(null);
//        patientEmail.setText(null);
//        gender.setText(null);
    }

    @FXML
    void SearchPatient(KeyEvent event) throws ClassNotFoundException, SQLException {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		String ID = SearchPatient.getText();
            con = conOBJ.getConnection();
            String sql = "SELECT Patient_ID,Name,DOB,Weight,Height,Blood_Group,Email,Gender FROM Patient WHERE Patient_ID =?";
        	ps = con.prepareStatement(sql);
        	ps.setString(1, ID);
        	ResultSet rs = ps.executeQuery();
            if(rs.next()) { 
//            	System.out.println( "This is rs" + rs.getString(1));
            	String PatientID = rs.getString("Patient_ID");
            	userID.setText(PatientID);
                String FullName = rs.getString("Name");
                patientName.setText(FullName);
                String Birth = rs.getString("DOB");
                DOB.setText(Birth);
                String Weight = rs.getString("Weight");
                patientWeight.setText(Weight);
                String Height = rs.getString("Height");
                patientHeight.setText(Height);
                String BloodGroup = rs.getString("Blood_Group");
                Blood_Group.setText(BloodGroup);
                String Email = rs.getString("Email");
                patientEmail.setText(Email);
                String Gender = rs.getString("Gender");
                gender.setText(Gender);
            }
            else {
            	msg.setInformationMessage("Patient doesn't exits!");
            }
    	}
    	
        if(!editPatientInfo.isPressed()) {
        	userID.setEditable(false);
        	patientName.setEditable(false);
        	patientEmail.setEditable(false);
        	DOB.setEditable(false);
        	patientWeight.setEditable(false);
        	patientHeight.setEditable(false);
        	Blood_Group.setEditable(false);
        	gender.setEditable(false);
        	Others.setEditable(false);
    	}
    }

    @FXML
    void UpdatePatient(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
    	con = conOBJ.getConnection();
    	String Patient_ID = userID.getText();
    	String FullName = patientName.getText();
    	String Email = patientEmail.getText();
    	String Date = DOB.getText();
//    	Date date1 = new SimpleDateFormat("yyyy/mm//dd").parse(Date);
    	String Weight = patientWeight.getText();
    	String Height = patientHeight.getText();
    	String BloodGroup = Blood_Group.getText();
    	String Gender = gender.getText();
    	String Other = Others.getText();
    	
    	String insert = "UPDATE Patient SET Patient_ID =?,Name =?,Email =?,DOB =?,Weight =?,Height =?,Blood_Group =?,Gender =?,Others =? where Patient_ID = ?";
    	ps = con.prepareStatement(insert);
    	ps.setString(1, Patient_ID);
    	ps.setString(2, FullName);
    	ps.setString(3, Email);
    	ps.setString(4, Date);
    	ps.setString(5, Weight);
    	ps.setString(6, Height);
    	ps.setString(7, BloodGroup);
    	ps.setString(8, Gender);
    	ps.setString(9, Other);
    	ps.setString(10, Patient_ID);
    	ps.executeUpdate();
    	msg.setSuccessMessage("Successfully Updated!");
    }
}
