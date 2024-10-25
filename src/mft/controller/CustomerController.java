package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.model.entity.Customer;
import mft.model.service.CustomerService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    private static int id = 0;
    private List<Customer> customerList = new ArrayList<>();

    @FXML
    private TextField idTxt, nameTxt, familyNameTxt, usernameTxt, passwordTxt, phoneTxt;

    @FXML
    private Button addBtn;

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

        addBtn.setOnAction(event -> {
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
                customerList.add(customer);
                CustomerService.saveAll(customerList);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully");
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
            id++;

            activeChk.setSelected(false);
            idTxt.setText(String.valueOf(id));
            nameTxt.clear();
            familyNameTxt.clear();
            usernameTxt.clear();
            passwordTxt.clear();
            phoneTxt.clear();
            activeChk.setSelected(false);
            refreshTable(customerList);
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
