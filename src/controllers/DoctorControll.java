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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorControll {
	@FXML
    private AnchorPane pane1;

    @FXML
    private TextField docID;
    
    @FXML
    private DatePicker DOB;

    @FXML
    private ToggleGroup DGender;
    
    @FXML
    private TextField doctorName;

    @FXML
    private TextField doctorEmail;

    @FXML
    private PasswordField PWD;

    @FXML
    private RadioButton dMale;

    @FXML
    private RadioButton dFemale;

    @FXML
    private PasswordField confirmPWD;

    @FXML
    private JFXButton editRequest;

    @FXML
    private TextField hospital;

    @FXML
    private JFXButton doctorreg;

    @FXML
    private ImageView back;

    private PreparedStatement ps;
    DataBase_Connection conOBJ = new DataBase_Connection();
    Connection con;
    ResultSet resultSet=null;
    Message msg = new Message();
    private PreparedStatement psCheckUserExists=null;
    @FXML
    public String selectGender(ActionEvent event) {
    	if(dMale.isSelected()) {
    		return "Male";
    	}
    	else if(dFemale.isSelected()){
    		return "Female";
    	}
    	else
    	{
    		return null;
    	}
    }
    
    @FXML
    String setDate() {
    	LocalDate date = DOB.getValue();
		return date.toString();
    }
    @FXML
    void backword(MouseEvent event) {
    	back.getScene().getWindow().hide();
    	Stage backword = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/UserSelect.fxml"));
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
    void RegisterDoctor(ActionEvent event) throws ClassNotFoundException, SQLException {
    	con = conOBJ.getConnection();
    	psCheckUserExists=con.prepareStatement("SELECT * FROM doctor WHERE doc_id=?");
        psCheckUserExists.setString(1,docID.getText());
        resultSet= psCheckUserExists.executeQuery();
    	String Doctor_ID = docID.getText();
    	String FullName = doctorName.getText();
    	String Email = doctorEmail.getText();
    	String Date = setDate();
    	String Hospital = hospital.getText();
    	String Password = PWD.getText();
    	String Gender = selectGender(null);
    	
    	if(resultSet.isBeforeFirst()) {
    		msg.setInformationMessage("This user already exist!");
    	}else{
    		if(!Password.equals(confirmPWD.getText())) {
    			msg.setMessage("Password dosen't match!");
    		}else if(!Doctor_ID.matches("(DC)\\/[0-9]{7}")) {
    			msg.setMessage("Invalid User ID type!");
    		}
    		else if(Password.length() != 8 ) {
    			msg.setWarningMessage("Password must contain 8 characters!");
    		}
    		else if(!Doctor_ID.trim().isEmpty() && !FullName.trim().isEmpty() && !Email.trim().isEmpty() && !Date.trim().isEmpty() && !Hospital.trim().isEmpty() && !Gender.trim().isEmpty()) {
    			String insert = "INSERT INTO Doctor (doc_id,Name,DOB,Hospital_Name,Email,Password,Gender)"+"VALUES(?,?,?,?,?,?,?)";
            	ps = con.prepareStatement(insert);
            	ps.setString(1, Doctor_ID);
            	ps.setString(2, FullName);
            	ps.setString(3, Date);
            	ps.setString(4, Hospital);
            	ps.setString(5, Email);
            	ps.setString(6, Password);
            	ps.setString(7, Gender);
            	
            	ps.executeUpdate();
            	msg.setSuccessMessage("Registration Successed!");
    		}
    		else {
    			msg.setWarningMessage("Please fill all required fields!");
    		}
    	}
    }
}
