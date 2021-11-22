package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginControll {
    @FXML
    private AnchorPane ExPane;

    @FXML
    private Pane pane;

    @FXML
    private Label logLable;

    @FXML
    protected TextField user;

    @FXML
    private PasswordField PWD;

    @FXML
    public JFXButton btn;

    @FXML
    private Label newAcc;
    
    @FXML
    private Label pwdreset;
    
    private Stage stage;
    private Parent root;
    private PreparedStatement ps;
    DataBase_Connection conOBJ = new DataBase_Connection();
    Connection con;
    Message msg = new Message();
    ResultSet resultSet=null;
    private PreparedStatement psCheckUserExists=null;
    @FXML
    void CreateAcc(MouseEvent event) {
    	newAcc.getScene().getWindow().hide();
    	Stage userselect = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/UserSelect.fxml"));
			Scene scene = new Scene(root);
			userselect.setResizable(false);
			userselect.setScene(scene);
			userselect.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @FXML
    void pwdreset(MouseEvent event) {
    	pwdreset.getScene().getWindow().hide();
    	Stage userselect = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/PasswordReset.fxml"));
			Scene scene = new Scene(root);
			userselect.setResizable(false);
			userselect.setScene(scene);
			userselect.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    String ID;
    @FXML
    void Login(ActionEvent event) throws ClassNotFoundException, SQLException, IOException  {
    	ID=user.getText();
    	con = conOBJ.getConnection();
    	
    	psCheckUserExists=con.prepareStatement("SELECT * FROM patient WHERE Patient_ID=?");
        psCheckUserExists.setString(1,user.getText());
        resultSet= psCheckUserExists.executeQuery();
        
    	//Patient login data retrieve
    	
    	String patient = "SELECT *FROM Patient where Patient_ID=? and Password=?";
    	
    	ps = con.prepareStatement(patient);
    	
    	ps.setString(1, ID);
    	ps.setString(2, PWD.getText());
    	
    	ResultSet rs = ps.executeQuery();
    	
    	//Doctor login data retrieve
    	
    	String doc = "SELECT *FROM Doctor where doc_id=? and Password=?";
    	
    	ps = con.prepareStatement(doc);
    	
    	ps.setString(1, ID);
    	ps.setString(2, PWD.getText());
    	
    	ResultSet rs1 = ps.executeQuery();
    	
    	//Pharmacist login data retrieve
    	
    	String pharmacist = "SELECT *FROM Doctor where doc_id=? and Password=?";
    	
    	ps = con.prepareStatement(pharmacist);
    	
    	ps.setString(1, ID);
    	ps.setString(2, PWD.getText());
    	
    	ResultSet rs2 = ps.executeQuery();
    	
    	//Patient Login
    	if(!resultSet.isBeforeFirst()) {
    		msg.setMessage("This user dosen't exist!");
    	}
    	else {
    		if(rs.next()) {
        		btn.getScene().getWindow().hide();
            	Stage PatientLogin = new Stage();
            	
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/Patient1.fxml"));
            	root = loader.load();
            	Patient1Controll LC = loader.getController();
            	LC.LoginDetails(ID);
    			stage  = (Stage)((Node)event.getSource()).getScene().getWindow();
    			Scene scene = new Scene(root);
    			PatientLogin.setResizable(false);
    			PatientLogin.setScene(scene);
    			PatientLogin.show();
    			
    			LC.LoginDetails(ID);
        	}
    		else if(rs1.next()) {
        		btn.getScene().getWindow().hide();
            	Stage DoctorLogin = new Stage();
            	
            	try {
        			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Doctor1.fxml"));
        			Scene scene = new Scene(root);
        			DoctorLogin.setResizable(false);
        			DoctorLogin.setScene(scene);
        			DoctorLogin.show();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        	
        	//Pharmacist Login
        	
        	else if(rs2.next()) {
        		btn.getScene().getWindow().hide();
            	Stage PharmacistLogin = new Stage();
            	
            	try {
        			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Pharmacist1.fxml"));
        			Scene scene = new Scene(root);
        			PharmacistLogin.setResizable(false);
        			PharmacistLogin.setScene(scene);
        			PharmacistLogin.show();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        	else {
        		msg.setMessage("Login Failed!");
        	}
    	}
    	
    	//Doctor Login
    	
    	
    	
    }


}