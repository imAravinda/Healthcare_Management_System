module Healthcare_Management_System {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	requires java.mail;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.controls, javafx.fxml;
}
