package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    private List<Payment> paymentList = new ArrayList<>();
    @FXML
    private TextField idTxt,titleTxt,priceTxt,amounttxt,descriptionTxt;
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
    private Button calculateBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        calculateBtn.setOnAction(event -> {
            Payment payment =
                    Payment
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .title(titleTxt.getText())
                            .price(Integer.parseInt(priceTxt.getText()))
                            .amount(Integer.parseInt(amounttxt.getText()))
                            .dateTime(String.valueOf(expireDate.getValue()))
                            .description(descriptionTxt.getText())
                            .paymentType(PaymentType.valueOf(paymentTypeCmb.getSelectionModel().getSelectedItem()))
                            .build();
            paymentList.add(payment);
            resetForm();
//            System.out.println(payment);
        });
    }
    private void resetForm(){
        for (PaymentType value : PaymentType.values()) {
            paymentTypeCmb.getItems().add(value.toString());
        }
        paymentTypeCmb.getSelectionModel().select(2);
        expireDate.setValue(LocalDate.now());
        titleTxt.clear();
        priceTxt.clear();
        amounttxt.clear();
        descriptionTxt.clear();
        refreshTbl(paymentList);
    }

    private void refreshTbl(List<Payment>paymentList){
        ObservableList<Payment> observableList= FXCollections.observableList(paymentList);

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        paymentTbl.setItems(observableList);
    }
}
