package Models;

import javafx.beans.property.SimpleStringProperty;

public class PrescriptionTable{
	
	private SimpleStringProperty date;
	private SimpleStringProperty Drugs;
	private SimpleStringProperty Disease;
	public PrescriptionTable(String date,String Drugs,String Disease) {
		this.Drugs= new SimpleStringProperty(Drugs);
		this.Disease =new SimpleStringProperty(Disease);
		this.date = new SimpleStringProperty(date);
	}
	public void setDrugs(String Drugs) {
		this.Drugs.set(Drugs);
	}
	public SimpleStringProperty getDrugs() {
		return Drugs;
	}
	public void setDisease(String Disease) {
		this.Disease.set(Disease);
	}
	public SimpleStringProperty getDisease() {
		return Disease;
	}
	public void setdate(String date) {
		this.date.set(date);
	}
	public SimpleStringProperty getdate() {
		return date;
	}
}
