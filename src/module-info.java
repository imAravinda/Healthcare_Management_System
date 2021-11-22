module Healthcare_Management_System {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml, javafx.controls;
}
