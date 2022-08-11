package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Patient1Controll{
	@FXML
    private AnchorPane pane1;

	 @FXML
	 private Label Patient_ID;

	 @FXML
	 private Label Weight;

	 @FXML
	 private Label Name;

	 @FXML
	 private Label Height;

	 @FXML
	 private Label Email;

	 @FXML
	 private Label Blood_Group;

	 @FXML
	 private Label DOB;

	 @FXML
	 private Label Gender;


    @FXML
    private TableColumn<?, ?> Date;

    @FXML
    private TableColumn<?, ?> Disease;

    @FXML
    private TableColumn<?, ?> drug;

    @FXML
    private TextArea EditRequest;

    @FXML
    private JFXButton RequestForEdit;

    @FXML
    private ImageView back;

    @FXML
    private JFXButton logout;
    private PreparedStatement ps;
    DataBase_Connection conOBJ = new DataBase_Connection();
    Connection con;
    ResultSet resultSet=null;
    Message msg = new Message();
    private PreparedStatement psCheckUserExists=null;
    public void LoginDetails(String ID) throws SQLException, ClassNotFoundException {
        con = conOBJ.getConnection();
        String sql = "SELECT Patient_ID,Name,Email,DOB,Weight,Height,Blood_Group,Gender FROM Patient WHERE Patient_ID =?";
    	ps = con.prepareStatement(sql);
    	ps.setString(1, ID);
    	ResultSet rs = ps.executeQuery();
        if(rs.next()) { 
//        	System.out.println( "This is rs" + rs.getString(1));
        	String PatientID = rs.getString("Patient_ID");
            Patient_ID.setText(PatientID);
            String FullName = rs.getString("Name");
            Name.setText(FullName);
            String email = rs.getString("Email");
            Email.setText(email);
            String Birth = rs.getString("DOB");
            DOB.setText(Birth);
            String weight = rs.getString("Weight");
            Weight.setText(weight);
            String height = rs.getString("Height");
            Height.setText(height);
            String BloodGroup = rs.getString("Blood_Group");
            Blood_Group.setText(BloodGroup);
            String gender = rs.getString("Gender");
            Gender.setText(gender);
        }
    }
    @FXML
    void LogOut(ActionEvent event) {
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
    void Request(ActionEvent event) throws ClassNotFoundException, SQLException {
    	con = conOBJ.getConnection();
    	String User_ID = Patient_ID.getText();
    	String Date = java.time.LocalDate.now().toString();
    	String Message = EditRequest.getText();
    	if(Message.trim().isEmpty()) {
    		msg.setWarningMessage("Please type your request!");
    	}
    	else {
    		String request = "INSERT INTO acc_changes (User_ID,Msg_Content,Date)"+"VALUES(?,?,?)";
        	ps = con.prepareStatement(request);
        	ps.setString(1, User_ID);
        	ps.setString(2, Message);
        	ps.setString(3, Date);
        	ps.executeUpdate();
        	msg.setSuccessMessage("Request Submited!");
    	}
    	
    }

    @FXML
    void backword(MouseEvent event) {
    	
    }
}
