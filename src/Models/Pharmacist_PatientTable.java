package Models;

import javafx.beans.property.SimpleStringProperty;

public class Pharmacist_PatientTable{
	private SimpleStringProperty patient_ID;
	private SimpleStringProperty patient_Name;
	private SimpleStringProperty patient_NO;
	public Pharmacist_PatientTable(String patient_ID,String patient_Name,String patient_NO) {
		this.patient_ID= new SimpleStringProperty(patient_ID);
		this.patient_Name =new SimpleStringProperty(patient_Name);
		this.patient_NO = new SimpleStringProperty(patient_NO);
	}
	public void setID(String patient_ID) {
		this.patient_ID.set(patient_ID);
	}
	public SimpleStringProperty getID() {
		return patient_ID;
	}
	public void setName(String patient_Name) {
		this.patient_Name.set(patient_Name);
	}
	public SimpleStringProperty getName() {
		return patient_Name;
	}
	public void setNO(String patient_NO) {
		this.patient_NO.set(patient_NO);
	}
	public SimpleStringProperty getNO() {
		return patient_NO;
	}
}
