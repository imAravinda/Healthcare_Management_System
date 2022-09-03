package Models;


public class Drugs {
	private String DrugName;
	public Drugs(String DrugName) {
		this.DrugName= DrugName;
	}
	public void setName(String DrugName) {
		this.DrugName=DrugName;
	}
	public String getName() {
		return DrugName;
	}
}
