package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class PatientControll {
	@FXML
	private ChoiceBox<String> Blood_Group;

	@FXML
    private AnchorPane pane1;

    @FXML
    private TextField userID;
    
    @FXML
    private ToggleGroup Gender;
    
    @FXML
    private TextField patientName;

    @FXML
    private TextField patientEmail;

    @FXML
    private PasswordField PWD;
    
    @FXML
    private DatePicker DOB;
    
    @FXML
    private TextField patientWeight;

    @FXML
    private TextField patientHeight;

    @FXML
    private RadioButton pMale;

    @FXML
    private RadioButton pFemale;

    @FXML
    private PasswordField confirmPWD;

    @FXML
    private TextArea Others;

    @FXML
    private JFXButton editRequest;

    @FXML
    private JFXButton patientreg;
    	
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
    	if(pMale.isSelected()) {
    		return "Male";
    	}
    	else if(pFemale.isSelected()){
    		return "Female";
    	}
    	else
    	{
    		return null;
    	}
    }
    
    @FXML
    public String Select(MouseEvent event) {
    	String BloodGroup = Blood_Group.getSelectionModel().getSelectedItem().toString();
    	return BloodGroup;
    }
    @FXML
    public void initialize() {
    	ObservableList<String> list = FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O+","O-");
    	Blood_Group.setItems(list);
    }

    @FXML
    void Backword(MouseEvent event) {
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
    String setDate() {
    	LocalDate date = DOB.getValue();
		return date.toString();
    }
    @FXML
    void RegisterPatient(ActionEvent event) throws ClassNotFoundException, SQLException {
    	con = conOBJ.getConnection();
    	psCheckUserExists=con.prepareStatement("SELECT * FROM patient WHERE Patient_ID=?");
        psCheckUserExists.setString(1,userID.getText());
        resultSet= psCheckUserExists.executeQuery();
    	String Patient_ID = userID.getText();
    	String FullName = patientName.getText();
    	String Email = patientEmail.getText();
    	String Date = setDate();
    	String Password = PWD.getText();
    	String Weight = patientWeight.getText();
    	String Height = patientHeight.getText();
    	String Blood_Group = Select(null);
    	String Gender = selectGender(null);
    	String Other = Others.getText();
    	if(resultSet.isBeforeFirst()){
    		msg.setInformationMessage("This user already exist!");
    	}
    	else {
    		if(!Password.equals(confirmPWD.getText())) {
    			msg.setMessage("Password dosen't match!");
        	}else if(!Patient_ID.matches("([A-Z]||[a-z]){2}\\/[2][0-9]{3}\\/[1][0-9]{4}")) {
    			msg.setMessage("Invalid User ID type!");
    		}
        	else if(Password.length() != 8 ) {
    			msg.setInformationMessage("Password must contain 8 characters!");
    		}
    		else if(!Patient_ID.trim().isEmpty() && !FullName.trim().isEmpty() && !Email.trim().isEmpty() && !Date.trim().isEmpty() && !Weight.trim().isEmpty() && !Height.trim().isEmpty() && !Blood_Group.trim().isEmpty() && !Gender.trim().isEmpty()){
        		String insert = "INSERT INTO Patient (Patient_ID,Name,Email,Password,DOB,Weight,Height,Blood_Group,Gender,Others)"+"VALUES(?,?,?,?,?,?,?,?,?,?)";
            	ps = con.prepareStatement(insert);
            	ps.setString(1, Patient_ID);
            	ps.setString(2, FullName);
            	ps.setString(3, Email);
            	ps.setString(4, Password);
            	ps.setString(5, Date);
            	ps.setString(6, Weight);
            	ps.setString(7, Height);
            	ps.setString(8, Blood_Group);
            	ps.setString(9, Gender);
            	ps.setString(10, Other);
            	
            	ps.executeUpdate();
            	msg.setSuccessMessage("Registration Successed!");
        	}
        	else
        	{
        		msg.setWarningMessage("Please fill all required fields!");
        	}
    	}
    	
    }
}


