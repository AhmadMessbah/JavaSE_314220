package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.model.entity.Customer;
import mft.model.entity.Product;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;
import mft.model.service.CustomerService;
import mft.model.service.ProductService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private TextField idTxt, nameTxt, familyNameTxt, usernameTxt, passwordTxt, phoneTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn;

    @FXML
    private CheckBox activeChk;

    @FXML
    private TableView<Customer> customerTbl;

    @FXML
    private TableColumn<Customer, Integer> idCol;

    @FXML
    private TableColumn<Customer, String> nameCol, familyNameCol, usernameCol;

    @FXML
    private TableColumn<Customer, Boolean> activeCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        saveBtn.setOnAction(event -> {
            try {
                Customer customer =
                        Customer
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .name(nameTxt.getText())
                                .familyName(familyNameTxt.getText())
                                .username(usernameTxt.getText())
                                .password(passwordTxt.getText())
                                .phone(phoneTxt.getText())
                                .active(activeChk.isSelected())
                                .build();
                CustomerService.save(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        editBtn.setOnAction(event -> {
            try {
                Customer customer =
                        Customer
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .name(nameTxt.getText())
                                .familyName(familyNameTxt.getText())
                                .username(usernameTxt.getText())
                                .password(passwordTxt.getText())
                                .phone(phoneTxt.getText())
                                .active(activeChk.isSelected())
                                .build();
                CustomerService.edit(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction(event -> {
            try {
                ProductService.remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Removed Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


        customerTbl.setOnMouseClicked(event -> {
            Customer customer = customerTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(customer.getId()));
            nameTxt.setText(customer.getName());
            familyNameTxt.setText(customer.getFamilyName());
            usernameTxt.setText(customer.getUsername());
            passwordTxt.setText(customer.getPassword());
            phoneTxt.setText(customer.getPhone());
            activeChk.setSelected(customer.isActive());
        });
    }

    private void resetForm() {
        try {
            activeChk.setSelected(false);
//            idTxt.setText(String.valueOf(id));
            nameTxt.clear();
            familyNameTxt.clear();
            usernameTxt.clear();
            passwordTxt.clear();
            phoneTxt.clear();
            activeChk.setSelected(false);
//            refreshTable(customerList);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    private void refreshTable(List<Customer> customerList) {
        ObservableList<Customer> observableList = FXCollections.observableList(customerList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyNameCol.setCellValueFactory(new PropertyValueFactory<>("familyName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));

        customerTbl.setItems(observableList);
    }
}
