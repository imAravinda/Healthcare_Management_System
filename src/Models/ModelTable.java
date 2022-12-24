package Models;

import java.sql.Date;
import java.time.LocalDate;

import com.mysql.cj.conf.StringProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class ModelTable {
	private SimpleStringProperty userID;
	private SimpleStringProperty MsgContent;
	private SimpleStringProperty date;
	public ModelTable(String userID,String MsgContent,String date) {
		this.userID= new SimpleStringProperty(userID);
		this.MsgContent =new SimpleStringProperty(MsgContent);;
		this.date = new SimpleStringProperty(date) ;
	}
	public void setID(String userID) {
		this.userID.set(userID);
	}
	public SimpleStringProperty getID() {
		return userID;
	}
	public void setMsg(String MsgContent) {
		this.MsgContent.set(MsgContent);
	}
	public SimpleStringProperty getMsg() {
		return MsgContent;
	}
	public void setdate(String date) {
		this.date.set(date);;
	}
	public SimpleStringProperty getdate() {
		return date;
	}
}
