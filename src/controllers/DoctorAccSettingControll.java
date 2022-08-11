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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorAccSettingControll {
	@FXML
    private AnchorPane pane1;

    @FXML
    private JFXButton Request;

    @FXML
    private ImageView back;

    @FXML
    private Label User_ID;

    @FXML
    private Label Name;

    @FXML
    private Label Hospital;

    @FXML
    private Label DOB;

    @FXML
    private Label Email;

    @FXML
    private Label Gender;

    @FXML
    private TextArea EditRequest;
    
    private PreparedStatement ps;
    DataBase_Connection conOBJ = new DataBase_Connection();
    Connection con;
    ResultSet resultSet=null;
    Message msg = new Message();
    public void LoginDetails(String ID) throws SQLException, ClassNotFoundException {
        con = conOBJ.getConnection();
        String sql = "SELECT doc_id,Name,Email,DOB,Hospital_Name,Gender FROM doctor WHERE doc_id =?";
    	ps = con.prepareStatement(sql);
    	ps.setString(1, ID);
    	ResultSet rs = ps.executeQuery();
        if(rs.next()) { 
//        	System.out.println( "This is rs" + rs.getString(1));
        	String DocID = rs.getString("doc_id");
        	User_ID.setText(DocID);
            String FullName = rs.getString("Name");
            Name.setText(FullName);
            String email = rs.getString("Email");
            Email.setText(email);
            String Birth = rs.getString("DOB");
            DOB.setText(Birth);
            String hospital = rs.getString("Hospital_Name");
            Hospital.setText(hospital);
            String gender = rs.getString("Gender");
            Gender.setText(gender);
        }
    }
    
    @FXML
    void RequestforEdit(ActionEvent event) throws ClassNotFoundException, SQLException {
    	con = conOBJ.getConnection();
    	String UserID = User_ID.getText();
    	String Date = java.time.LocalDate.now().toString();
    	String Message = EditRequest.getText();
    	if(Message.trim().isEmpty()) {
    		msg.setWarningMessage("Please type your request!");
    	}
    	else {
    		String request = "INSERT INTO acc_changes (User_ID,Msg_Content,Date)"+"VALUES(?,?,?)";
        	ps = con.prepareStatement(request);
        	ps.setString(1, UserID);
        	ps.setString(2, Message);
        	ps.setString(3, Date);
        	ps.executeUpdate();
        	msg.setSuccessMessage("Request Submited!");
    	}
    }
    
    @FXML
    void backword(MouseEvent event) {
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
