package Data_Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase_Connection extends Config{
	Connection con;
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String str = "jdbc:mysql://"+Config.dbhost+":"+Config.dbport+"/"+Config.dbname;
		con = DriverManager.getConnection(str,Config.dbuser,Config.dbpassword);
		return con;
	}
}
