package controllers;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MessagesControll {
	@FXML
    private AnchorPane pane1;

    @FXML
    private ImageView back;

    @FXML
    private TableColumn<?, ?> UserM;

    @FXML
    private TableColumn<?, ?> MDescription;

    @FXML
    private TextArea Message;

    @FXML
    private JFXButton ClearMessages;

    @FXML
    void ClearMessages(ActionEvent event) {

    }

    @FXML
    void backword(MouseEvent event) {

    }

}
