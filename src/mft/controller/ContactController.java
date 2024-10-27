package mft.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactController implements Initializable {


    @FXML
    private TextField idTxt, stateTxt, cityTxt ,regionTxt ,addressTxt,postalcodeTxt,phoneTxt,descriptionTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

