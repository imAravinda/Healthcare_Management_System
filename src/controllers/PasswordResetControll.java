package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PasswordResetControll {
	@FXML
    private AnchorPane pane1;

    @FXML
    private PasswordField OTP;

    @FXML
    private PasswordField newPWD;

    @FXML
    private PasswordField confirmpwd;

    @FXML
    private JFXButton newpwd;

    @FXML
    private ImageView back;

    @FXML
    private TextField Email;

    @FXML
    private JFXButton Send;
    
    @FXML
    private TextField User_ID;
    
    private PreparedStatement ps;
    DataBase_Connection conOBJ = new DataBase_Connection();
    Connection con;
    ResultSet resultSet=null;
    ResultSet resultSet1=null;
    private PreparedStatement psCheckEmailExists=null;
    private PreparedStatement psCheckUserExists=null;
    Message msg = new Message();
    public static String generateOTP() 
    {  
        int randomPin   =(int) (Math.random()*9000)+1000;
        String otp  = String.valueOf(randomPin);
        return otp;
    }
    String G_OTP;
    @FXML
    void OTPSend(ActionEvent event) throws AddressException, MessagingException, IOException {
    	String to = Email.getText();
    	String host = "smtp.gmail.com";
    	
    	Properties props = System.getProperties();
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.port", "587");
    	
    	Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
    		@Override
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication("ruhunahealthcare@gmail.com", "Ruhhealth123@");
    		}
    	});
    	try {
    		G_OTP=generateOTP();
    		MimeMessage m = new MimeMessage(session);
    		m.setFrom(new InternetAddress("ruhunahealthcare@gmail.com"));
    		m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
    		m.setSubject("OTP Code is");
    		m.setText(G_OTP);
    		System.out.println(G_OTP);
    		
    		Transport.send(m);
    		msg.setMessage("OTP Send");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void backword(MouseEvent event) {
    	back.getScene().getWindow().hide();
    	Stage backword = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/LoginFxml.fxml"));
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
    void SubmitNewPassword(ActionEvent event) throws ClassNotFoundException, SQLException {
    	String OTP1 = OTP.getText();
    	String PWD = newPWD.getText();
    	String Mail = Email.getText();
    	String UserID = User_ID.getText();
    	
    	if(!G_OTP.equals(OTP1)) {
    		msg.setMessage("Invalid OTP!");
    	}
    	else if(PWD.length() != 8) {
    		msg.setMessage("Password must contain 8 characters!");
    	}
    	else if(!PWD.equals(confirmpwd.getText())) {
    		msg.setMessage("Password dosen't match!");
    	}
    	else{
    		con = conOBJ.getConnection();
    		if(UserID.matches("([A-Z]||[a-z]){2}\\/[2][0-9]{3}\\/[1][0-9]{4}")) {
    			psCheckEmailExists=con.prepareStatement("SELECT * FROM patient WHERE Email=?");
    			psCheckUserExists=con.prepareStatement("SELECT * FROM patient WHERE Patient_ID=?");
    			psCheckEmailExists.setString(1,Mail);
    			psCheckUserExists.setString(1, UserID);
    			resultSet1 = psCheckUserExists.executeQuery();
    	        resultSet= psCheckEmailExists.executeQuery();
    	        if(!resultSet.isBeforeFirst()) {
    	        	msg.setMessage("Invalid Email");
    	        }
    	        else if(!resultSet.isBeforeFirst()) {
    	        	msg.setMessage("Invalid User ID");
    	        }
    	        else {
    	        	ps=con.prepareStatement("UPDATE patient SET Password = ? WHERE Email = ?");
                	ps.setString(1,PWD);
                	ps.setString(2,Mail);
                	ps.executeUpdate();
                	msg.setMessage("Password changed successfully!");
    	        }
    			
    		}
    		else if(UserID.matches("(DC)\\/[0-9]{7}")) {
    			psCheckEmailExists=con.prepareStatement("SELECT * FROM doctor WHERE Email=?");
    			psCheckUserExists=con.prepareStatement("SELECT * FROM doctor WHERE doc_id=?");
    			psCheckEmailExists.setString(1,Mail);
    			psCheckUserExists.setString(1, UserID);
    			resultSet1 = psCheckUserExists.executeQuery();
    	        resultSet= psCheckEmailExists.executeQuery();
    	        if(!resultSet.isBeforeFirst()) {
    	        	msg.setMessage("Invalid Email");
    	        }
    	        else if(!resultSet.isBeforeFirst()) {
    	        	msg.setMessage("Invalid User ID");
    	        }
    	        else {
    	        	ps=con.prepareStatement("UPDATE patient SET Password = ? WHERE Email = ?");
                	ps.setString(1,PWD);
                	ps.setString(2,Mail);
                	ps.executeUpdate();
                	msg.setMessage("Password changed successfully!");
    	        }
    		}
    		else if(UserID.matches("(PH)\\/[0-9]{7}")) {
    			psCheckEmailExists=con.prepareStatement("SELECT * FROM pharmacist WHERE Email=?");
    			psCheckUserExists=con.prepareStatement("SELECT * FROM pharmacist WHERE Phar_ID=?");
    			psCheckEmailExists.setString(1,Mail);
    			psCheckUserExists.setString(1, UserID);
    			resultSet1 = psCheckUserExists.executeQuery();
    	        resultSet= psCheckEmailExists.executeQuery();
    	        if(!resultSet.isBeforeFirst()) {
    	        	msg.setMessage("Invalid Email");
    	        }
    	        else if(!resultSet.isBeforeFirst()) {
    	        	msg.setMessage("Invalid User ID");
    	        }
    	        else {
    	        	ps=con.prepareStatement("UPDATE patient SET Password = ? WHERE Email = ?");
                	ps.setString(1,PWD);
                	ps.setString(2,Mail);
                	ps.executeUpdate();
                	msg.setMessage("Password changed successfully!");
    	        }
    		}
    	}
    }
}

