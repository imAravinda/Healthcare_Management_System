package Models;

import javafx.beans.property.SimpleStringProperty;
import java.sql.Date;
public class ModelTable {
	String userID;
	String MsgContent;
	Date date;
	public ModelTable(String userID,String MsgContent,Date date) {
		this.userID= userID;
		this.MsgContent =MsgContent;
		this.date = date ;
	}
	public void setID(String userID) {
		this.userID= userID;
	}
	public String getID() {
		return userID;
	}
	public void setMsg(String MsgContent) {
		this.MsgContent= MsgContent;
	}
	public String getMsg() {
		return MsgContent;
	}
	public void setdate(Date date) {
		this.date= date;
	}
	public Date getdate() {
		return date;
	}
}
