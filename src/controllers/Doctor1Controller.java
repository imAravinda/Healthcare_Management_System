package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;

import AlertMessage.Message;
import Data_Base.DataBase_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Models.Drugs;
import Models.DrugsTable;
import Models.ModelTable;
import Models.PrescriptionTable;
public class Doctor1Controller implements Initializable{
	 @FXML
	    private AnchorPane pane1;

	    @FXML
	    private JFXButton editRequest;

	    @FXML
	    private ImageView back;

	    @FXML
	    private TextField patientSearch;

	    @FXML
	    private TableView<DrugsTable> DrugTable;
	    
	    @FXML
	    private TableView<PrescriptionTable> prescriptionTable;
	    
	    @FXML
	    private TableColumn<PrescriptionTable, String> Date;

	    @FXML
	    private TableColumn<PrescriptionTable,String> Drugs;

	    @FXML
	    private TableColumn<PrescriptionTable,String> Diseases;

	    @FXML
	    private TextField disease;

	    @FXML
	    private TextField drugSearch;

	    @FXML
	    private TextField drugDuration;

	    @FXML
	    private JFXButton addDrug;

	    @FXML
	    private JFXButton clearDrug;

	    @FXML
	    private TableColumn<DrugsTable, String> Drugs1;

	    @FXML
	    private TableColumn<DrugsTable, String> Diseases1;

	    @FXML
	    private JFXButton resetPrescription;

	    @FXML
	    private JFXButton removePrescription;

	    @FXML
	    private JFXButton SubmitPrescription;

	    @FXML
	    private JFXButton logout;

	    @FXML
	    private Label patient_ID;

	    @FXML
	    private Label Name;

	    @FXML
	    private Label Blood_Group;

	    @FXML
	    private Label height;

	    @FXML
	    private Label weight;

	    @FXML
	    private Label DOB;
	    
	    @FXML
	    private Label docName;

	    private Stage stage;
	    private Parent root;
	    private PreparedStatement ps;
	    private PreparedStatement ps1;
	    DataBase_Connection conOBJ = new DataBase_Connection();
	    Connection con;
	    Message msg = new Message();
	    DrugsTable drugsTable = null;
	    
	    private String UserID;
	    public void setID(String ID) throws SQLException, ClassNotFoundException {
	        con = conOBJ.getConnection();
	        String sql = "SELECT Name,doc_id FROM doctor WHERE doc_id =?";
	    	ps = con.prepareStatement(sql);
	    	ps.setString(1, ID);
	    	ResultSet rs = ps.executeQuery();
	        if(rs.next()) { 
	            String FullName = rs.getString("Name");
	            docName.setText("Dr." + FullName);
	            UserID = rs.getString("doc_id");
	        }
	    }
	    @FXML
	    void AccountSetting(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
	    	editRequest.getScene().getWindow().hide();
        	Stage PatientLogin = new Stage();
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/DoctorAccSetting.fxml"));
        	root = loader.load();
        	DoctorAccSettingControll LC = loader.getController();
	        LC.LoginDetails(UserID);
			stage  = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			PatientLogin.setResizable(false);
			PatientLogin.setScene(scene);
			PatientLogin.show();
	    	
	    }
	    public String[] retreivedrugs() throws ClassNotFoundException, SQLException {
	    	con = conOBJ.getConnection();
	        String sql = "SELECT Drug_Name from drugs;";
	        ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        ArrayList<String> drugList = new ArrayList<>();
	        while (rs.next()) {
	            Drugs customer = new Drugs(rs.getString("Drug_Name"));
	            
	            drugList.add(customer.getName());
	        }
	        String[] Drug = new String[drugList.size()];
	        for(int i=0;i<drugList.size();i++) {
	        	Drug[i] = drugList.get(i);
	        }
	        return Drug;
	    }
	    public AutoCompletionBinding<String> AutoCompleteDrugNames;
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				String[] drugs = retreivedrugs(); 
				TextFields.bindAutoCompletion(drugSearch,drugs);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    ObservableList<DrugsTable> oblist = FXCollections.observableArrayList();
	    @FXML
	    void AddDrugs(ActionEvent event) {
	    	oblist.add(new DrugsTable(drugSearch.getText(),drugDuration.getText()));
	    	Drugs1.setCellValueFactory(cellData -> cellData.getValue().getDrugs());
	    	Diseases1.setCellValueFactory(cellData -> cellData.getValue().getDescription());
	    	DrugTable.setItems(oblist);
	    }

	    @FXML
	    void ClearDrugs(ActionEvent event) {
	    	drugSearch.setText(null);
	    	
	    }

	    @FXML
	    void Logout(ActionEvent event) {
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
	    void RemovePrescription(ActionEvent event) {
	    	
	    }

	    @FXML
	    void ResetPrescription(ActionEvent event) {
	    	if(!Drugs1.equals(null) || Diseases1.equals(null)) {
	    		Drugs1.setCellValueFactory(null);
	    		Diseases1.setCellValueFactory(null);
	    	}
	    	else {
	    		msg.setInformationMessage("Table is Empty");
	    	}
	    }

	    ObservableList<PrescriptionTable> oblist2 = FXCollections.observableArrayList();
	    @FXML
	    void SearchPatient(KeyEvent event) throws ClassNotFoundException, SQLException {
	    	if(event.getCode().equals(KeyCode.ENTER)) {
		    	String ID = patientSearch.getText();
		        con = conOBJ.getConnection();
		        String sql = "SELECT u.Patient_ID,u.Name,u.DOB,u.Weight,u.Height,u.Blood_Group FROM Patient as u WHERE U.Patient_ID =  ?";
		        String sql1 = "SELECT Date,Drugs,Disease FROM prescription WHERE Patient_ID = ?";
		        
		        ps1 = con.prepareStatement(sql1);
		    	ps = con.prepareStatement(sql);
		    	ps1.setString(1, ID);
		    	ps.setString(1, ID);
		    	ResultSet rs = ps.executeQuery();
		    	ResultSet rs1 = ps1.executeQuery();
		        if(rs.next() ) { 
		        	if(rs1.next()) {
		        		oblist2.add(
		        				new PrescriptionTable(
		        						rs1.getString("Date"),
		        						rs1.getString("Drugs"),
		        						rs1.getString("Disease")
		        				)
		        			);
			        	Drugs.setCellValueFactory(cellData -> cellData.getValue().getDrugs());
			        	Diseases.setCellValueFactory(cellData -> cellData.getValue().getDisease());
			        	Date.setCellValueFactory(cellData -> cellData.getValue().getdate());
			        	prescriptionTable.setItems(oblist2);
		        	}
		        	
		        	String PatientID = rs.getString("Patient_ID");
		            patient_ID.setText(PatientID);
		            String FullName = rs.getString("Name");
		            Name.setText(FullName);
		            String Birth = rs.getString("DOB");
		            DOB.setText(Birth);
		            String Weight = rs.getString("Weight");
		            weight.setText(Weight);
		            String Height = rs.getString("Height");
		            height.setText(Height);
		            String BloodGroup = rs.getString("Blood_Group");
		            Blood_Group.setText(BloodGroup);
		        }
		        else {
	            	msg.setInformationMessage("Patient doesn't exits!");
	            }	
	    	}
	    }

	    @FXML
	    void SubmitPrescription(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	con = conOBJ.getConnection();
	    	if(!patient_ID.equals(null)) {
	    		String insert = "INSERT INTO Prescription (Patient_ID,Patient_Name,Date,Disease,Drugs,Description)"+"VALUES(?,?,?,?,?,?)";
	        	ps = con.prepareStatement(insert);
	        	ps.setString(1, patient_ID.getText());
	        	ps.setString(2, Name.getText());
	        	ps.setString(3, java.time.LocalDate.now().toString());
	        	ps.setString(4, disease.getText());
	        	ps.setString(5, drugSearch.getText());
	        	ps.setString(6, drugDuration.getText());
	        	ps.executeUpdate();
	        	String copy = "INSERT INTO PrescriptionCopy (Patient_ID,Patient_Name,Date,Disease,Drugs,Description)"+"VALUES(?,?,?,?,?,?)";
	        	PreparedStatement ps2 = con.prepareStatement(copy);
	        	ps2.setString(1, patient_ID.getText());
	        	ps2.setString(2, Name.getText());
	        	ps2.setString(3, java.time.LocalDate.now().toString());
	        	ps2.setString(4, disease.getText());
	        	ps2.setString(5, drugSearch.getText());
	        	ps2.setString(6, drugDuration.getText());
	        	ps2.executeUpdate();
	        	msg.setSuccessMessage("Prescription Submited!");
	    	}
	    	else {
	    		msg.setWarningMessage("Please Select the patient!");
	    	}
	    	
	    }
	    @FXML
	    void backword(MouseEvent event) throws ClassNotFoundException, SQLException {
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
