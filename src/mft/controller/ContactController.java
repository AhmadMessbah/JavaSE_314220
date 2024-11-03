package mft.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.model.entity.Contact;
import mft.model.entity.Product;
import mft.model.service.ContactServer;
import mft.model.service.ProductService;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactController implements Initializable {


    @FXML
    private TextField idTxt, stateTxt, cityTxt ,regionTxt ,addressTxt,postalcodeTxt,phoneTxt,descriptionTxt,findNameTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn,addBtn;

    @FXML
    private TableView<Contact> contactTbl;

    @FXML
    private TableColumn<Contact, Integer> idCol;

    @FXML
    private TableColumn<Contact, String> citycol,regioncol,addresscol,postallcol,phonecol,descriptioncol;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

saveBtn.setOnAction(event -> {
    Contact contact =
            Contact
                    .builder()
                    .id(1)
                    .state("Tehran")
                    .city("Tehran")
                    .region("1")
                    .address("andarzgo")
                    .postalCode("777")
                    .phone("0912")
                    .description("moshakhasat")
                    .build();



});


 editBtn.setOnAction(event -> {
     Contact contact =
             Contact
                     .builder()
                     .id(1)
                     .state("Tehran")
                     .city("Tehran")
                     .region("1")
                     .address("andarzgo")
                     .postalCode("777")
                     .phone("0912")
                     .description("moshakhasat")
                     .build();


 });
 removeBtn.setOnAction(event -> {
     Contact contact =
             Contact
                     .builder()
                     .id(1)
                     .state("Tehran")
                     .city("Tehran")
                     .region("1")
                     .address("andarzgo")
                     .postalCode("777")
                     .phone("0912")
                     .description("moshakhasat")
                     .build();

 });}}


