package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
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

@Log4j
public class CustomerController implements Initializable {
    @FXML
    private TextField idTxt, nameTxt, familyNameTxt, usernameTxt, passwordTxt, phoneTxt, findNameTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn, newBtn;

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
        log.debug("Customer Form Loaded");

        resetForm();

        newBtn.setOnAction(event -> {
            resetForm();
        });

        saveBtn.setOnAction(event -> {
            try {
                Customer customer =
                        Customer
                                .builder()
                                .name(nameTxt.getText())
                                .familyName(familyNameTxt.getText())
                                .username(usernameTxt.getText())
                                .password(passwordTxt.getText())
                                .phone(phoneTxt.getText())
                                .active(activeChk.isSelected())
                                .build();

                CustomerService.save(customer);
                log.info("Customer Saved " + customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
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
                log.info("Customer Edited " + customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction(event -> {
            try {
                CustomerService.remove(Integer.parseInt(idTxt.getText()));
                log.info("Customer Removed By ID" + idTxt.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Removed Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findNameTxt.setOnKeyReleased(event -> {
            try {
                List<Customer> customerList = CustomerService.findByName(findNameTxt.getText() + "%");
                refreshTable(customerList);
                log.info("Find Customers By Name = " + findNameTxt.getText());
            } catch (Exception e) {
                log.error(e.getMessage());
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
            idTxt.clear();
            nameTxt.clear();
            familyNameTxt.clear();
            usernameTxt.clear();
            passwordTxt.clear();
            phoneTxt.clear();
            activeChk.setSelected(false);
            refreshTable(CustomerService.findAll());
            log.info("Customer Form Cleared");
        } catch (Exception e) {
            log.error(e.getMessage());
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
