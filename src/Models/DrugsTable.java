package Models;

import javafx.beans.property.SimpleStringProperty;

public class DrugsTable {
	private SimpleStringProperty Drugs;
	private SimpleStringProperty Description;
	public DrugsTable(String Drugs,String Description) {
		this.Drugs= new SimpleStringProperty(Drugs);
		this.Description =new SimpleStringProperty(Description);;
	}
	public void setDrugs(String Drugs) {
		this.Drugs.set(Drugs);
	}
	public SimpleStringProperty getDrugs() {
		return Drugs;
	}
	public void setDescription(String Description) {
		this.Description.set(Description);
	}
	public SimpleStringProperty getDescription() {
		return Description;
	}
}
