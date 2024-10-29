package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;
import mft.model.service.PaymentService;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML
    private TextField idTxt,titleTxt,priceTxt,amountTxt,descriptionTxt;
    @FXML
    private DatePicker expireDate;
    @FXML
    private ComboBox<String> paymentTypeCmb;
    @FXML
    private TableView<Payment> paymentTbl;
    @FXML
    private TableColumn<Payment,String> titleCol;
    @FXML
    private TableColumn<Payment,Integer> priceCol,amountCol,AmountPayableCol;
    @FXML
    private Button calculateBtn,editBtn,deleteBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        calculateBtn.setOnAction(event -> {
            try {
                Payment payment =
                        Payment
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .title(titleTxt.getText())
                                .price(Integer.parseInt(priceTxt.getText()))
                                .amount(Integer.parseInt(amountTxt.getText()))
                                .dateTime(String.valueOf(expireDate.getValue()))
                                .description(descriptionTxt.getText())
                                .paymentType(PaymentType.valueOf(paymentTypeCmb.getSelectionModel().getSelectedItem()))
                                .build();
                PaymentService.save(payment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        editBtn.setOnAction(event -> {
            try {
                Payment payment =
                        Payment
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .title(titleTxt.getText())
                                .price(Integer.parseInt(priceTxt.getText()))
                                .amount(Integer.parseInt(amountTxt.getText()))
                                .dateTime(String.valueOf(expireDate.getValue()))
                                .description(descriptionTxt.getText())
                                .paymentType(PaymentType.valueOf(paymentTypeCmb.getSelectionModel().getSelectedItem()))
                                .build();
                PaymentService.edit(payment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "edited Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


        deleteBtn.setOnAction(event -> {
            try {

                PaymentService.delete(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "deleted Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


        paymentTbl.setOnMouseClicked(event -> {
            Payment payment=paymentTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(payment.getId()));
            titleTxt.setText(payment.getTitle());
            priceTxt.setText(String.valueOf(payment.getPrice()));
            amountTxt.setText(String.valueOf(payment.getAmount()));
            descriptionTxt.setText(payment.getDescription());

        });


    }
    private void resetForm(){
        try {
        for (PaymentType value : PaymentType.values()) {
            paymentTypeCmb.getItems().add(value.toString());
        }
        paymentTypeCmb.getSelectionModel().select(2);
        expireDate.setValue(LocalDate.now());
        titleTxt.clear();
        priceTxt.clear();
        amountTxt.clear();
        descriptionTxt.clear();
    }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }}

    private void refreshTbl(List<Payment>paymentList){
        ObservableList<Payment> observableList= FXCollections.observableList(paymentList);

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentTbl.setItems(observableList);
    }
}
