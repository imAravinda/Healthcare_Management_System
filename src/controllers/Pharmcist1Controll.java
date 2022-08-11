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
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Pharmcist1Controll {

	@FXML
    private AnchorPane pane1;

    @FXML
    private TableColumn<?, ?> PatientNo;

    @FXML
    private TableColumn<?, ?> PatientName;

    @FXML
    private TableColumn<?, ?> PatientID;

    @FXML
    private JFXButton done;

    @FXML
    private JFXButton editRequiest;

    @FXML
    private TableColumn<?, ?> Drug;

    @FXML
    private TableColumn<?, ?> Description;

    @FXML
    private JFXButton Logout;
    
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

    @FXML
    void issueMedicines(ActionEvent event) {

    }

}
