package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import Models.ModelTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MessagesControll implements Initializable{
	@FXML
    private TableView<ModelTable> RequestTable;
	
	@FXML
    private AnchorPane pane1;

    @FXML
    private ImageView back;

    @FXML
    private TableColumn<ModelTable, String> date;

    @FXML
    private TableColumn<ModelTable, String> userID;

    @FXML
    private TableColumn<ModelTable, String> Msg;


    @FXML
    private Button clear;

    private PreparedStatement ps;
    Connection con;
    DataBase_Connection conOBJ = new DataBase_Connection();
    ResultSet resultSet;
    Message msg = new Message();
    ModelTable modeltable = null;
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    @FXML
    void backword(MouseEvent event) {
    	back.getScene().getWindow().hide();
    	Stage backword = new Stage();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Admin.fxml"));
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
    void clearRequests(ActionEvent event) throws SQLException, ClassNotFoundException {
    	if(!userID.equals(null) && !Msg.equals(null)) {
    		userID.setCellValueFactory(null);;
        	Msg.setCellFactory(null);
        	con = conOBJ.getConnection();
        	String delete = "DELETE from acc_changes WHERE Patient_ID = ?";
        	ps = con.prepareStatement(delete);
        	ps.setString(1, userID.getText());
        	int rs = ps.executeUpdate();
    	}
    	else {
    		msg.setInformationMessage("Table is Empty");
    	}
    }
    void showRequests() throws ClassNotFoundException, SQLException {
    	oblist.clear();
    	con = conOBJ.getConnection();
    	String sql = "SELECT * FROM acc_changes";
    	ps = con.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
            oblist.add(new ModelTable(
            		rs.getString("User_ID"),
            		rs.getString("MSG_Content"), 
            		rs.getString("Date")));
            RequestTable.setItems(oblist);
        }
    	
    		
    }
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			loadData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    private void loadData() throws ClassNotFoundException, SQLException {
    	
    	
    	showRequests();
    	con = conOBJ.getConnection();
    	userID.setCellValueFactory(cellData -> cellData.getValue().getID());
    	Msg.setCellValueFactory(cellData -> cellData.getValue().getMsg());
    	date.setCellValueFactory(cellData -> cellData.getValue().getdate());
    }

}
