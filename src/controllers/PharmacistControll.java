package controllers;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.sql.Blob;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PharmacistControll {
	 @FXML
	    private AnchorPane pane1;

	    @FXML
	    private TextField pharmacistID;

	    @FXML
	    private TextField pharmacistName;

	    @FXML
	    private TextField pharmacistEmail;

	    @FXML
	    private PasswordField PWD;

	    @FXML
	    private RadioButton pharmacistMale;

	    @FXML
	    private RadioButton pharmacistFemale;
	    
	    @FXML
	    private ToggleGroup Gender;

	    @FXML
	    private PasswordField confirmPWD;

	    @FXML
	    private JFXButton editRequest;

	    @FXML
	    private JFXButton pharmacistreg;
	    
	    @FXML
	    private DatePicker DOB;
	    
	    @FXML
	    private ImageView back;
	    
	    @FXML
	    private JFXButton fileupload;
	    
	    byte[] LicensePDF;
	    
	    private PreparedStatement ps;
	    DataBase_Connection conOBJ = new DataBase_Connection();
	    Connection con;
	    ResultSet resultSet=null;
	    Message msg = new Message();
	    private PreparedStatement psCheckUserExists=null;
	    
	    @FXML
	    public String selectGender(ActionEvent event) {
	    	if(pharmacistMale.isSelected()) {
	    		return "Male";
	    	}
	    	else if(pharmacistFemale.isSelected()){
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
	    void uploadFile() throws IOException {
	    	FileChooser FC = new FileChooser();
	    	FC.setTitle("Open File Dialog");
	    	FC.getExtensionFilters().add(new ExtensionFilter("PDF Files","*.pdf"));
	    	Stage stage = (Stage)pane1.getScene().getWindow();
	    	File file = FC.showOpenDialog(stage);
	    	fileupload.setText(file.getAbsolutePath().toString());
			try {
				File pdf =  new File(file.getAbsolutePath());
				FileInputStream FIS = new FileInputStream(pdf); 
				ByteArrayOutputStream BOS = new ByteArrayOutputStream();
				byte[] PDF = new byte[1048576];
				for(int readNum ; (readNum = FIS.read(PDF)) != 1;) {
					BOS.write(PDF, 0 , readNum);
				}
				LicensePDF = BOS.toByteArray();
				
			}catch(Exception e) {
				e.printStackTrace();
			}	
	    }
	    
	    @FXML
	    void RegisterPharmacist(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
	    	con = conOBJ.getConnection();
	    	psCheckUserExists=con.prepareStatement("SELECT * FROM pharmacist WHERE Phar_ID=?");
	        psCheckUserExists.setString(1,pharmacistID.getText());
	        resultSet= psCheckUserExists.executeQuery();
	    	String Phar_ID = pharmacistID.getText();
	    	String Name = pharmacistName.getText();
	    	String Email = pharmacistEmail.getText();
	    	String Birth = setDate();
	    	String Gender = selectGender(event);
	    	String Password = PWD.getText();
	    	if(resultSet.isBeforeFirst()) {
	    		msg.setMessage("This user already exist!");
	    	}else {
	    		if(!Password.equals(confirmPWD.getText())) {
	    			msg.setMessage("Password dosen't match!");
	    		}else if(!Phar_ID.trim().isEmpty() && !Name.trim().isEmpty() && !Email.trim().isEmpty() && !Birth.trim().isEmpty() && !Gender.trim().isEmpty()) {
	    			String insert = "INSERT INTO pharmacist (Phar_ID,Name,Email,DOB,Gender,License,Password) " + "VALUES (?,?,?,?,?,?,?)";
		    		ps = con.prepareStatement(insert);
			    	ps.setString(1, Phar_ID);
			    	ps.setString(2, Name);
			    	ps.setString(3, Email);
			    	ps.setString(4, Birth);
			    	ps.setString(5, Gender);
			    	ps.setBytes(6, LicensePDF);;
			    	ps.setString(7, Password);// byte[] array
			    	ps.executeUpdate();
			    	msg.setMessage("Registration Successed!");
	    		}
	    		else {
	    			msg.setMessage("Please fill all required fields!");
	    		}
	    	}	
	    }
}
