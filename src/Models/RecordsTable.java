package Models;

import javafx.beans.property.SimpleStringProperty;

public class RecordsTable {
	private SimpleStringProperty date;
	private SimpleStringProperty Drugs;
	private SimpleStringProperty Disease;
	private SimpleStringProperty patient_ID;
	private SimpleStringProperty patient_Name;
	public RecordsTable(String Date,String Drugs,String Disease,String Patient_ID,String Patient_Name) {
		this.date = new SimpleStringProperty(Date);
		this.Drugs = new SimpleStringProperty(Drugs);
		this.Disease = new SimpleStringProperty(Disease);
		this.patient_ID = new SimpleStringProperty(Patient_ID);
		this.patient_Name = new SimpleStringProperty(Patient_Name);
	}
	public SimpleStringProperty getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date.set(date);;
	}
	public SimpleStringProperty getDrugs() {
		return Drugs;
	}
	public void setDrugs(String drugs) {
		Drugs.set(drugs);
	}
	public SimpleStringProperty getDisease() {
		return Disease;
	}
	public void setDisease(String disease) {
		Disease.set(disease);
	}
	public SimpleStringProperty getPatient_ID() {
		return patient_ID;
	}
	public void setPatient_ID(String patient_ID) {
		this.patient_ID.set(patient_ID);
	}
	public SimpleStringProperty getPatient_Name() {
		return patient_Name;
	}
	public void setPatient_Name(String patient_Name) {
		this.patient_Name.set(patient_Name);
	}
	
}
