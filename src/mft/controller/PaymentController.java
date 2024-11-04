package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import mft.model.entity.Payment;
import mft.model.entity.PaymentType;
import mft.model.service.PaymentService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class PaymentController implements Initializable {

    @FXML
    private TextField idTxt,titleTxt,amountTxt,descriptionTxt,findNameTxt;
    @FXML
    private DatePicker datePick;
    @FXML
    private ComboBox<String> paymentCmb;
    @FXML
    private Button saveBtn,editBtn,removeBtn,newBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug ("Payment Form Loaded");

        resetForm();

        newBtn.setOnAction(event -> {
            resetForm();
        });

        saveBtn.setOnAction(event -> {
            try {
                Payment payment =
                        Payment
                                .builder()
                                .title(titleTxt.getText())
                                .amount(Integer.parseInt(amountTxt.getText()))
                                .dateTime(LocalDate.parse(String.valueOf(datePick.getValue())))
                                .description(descriptionTxt.getText())
                                .paymentType(PaymentType.valueOf(paymentCmb.getSelectionModel().getSelectedItem()))
                                .build();

                payment.setAmount(Integer.parseInt(amountTxt.getText()));

                PaymentService.save(payment);
                log.info("Product Saved " + payment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();}
        });

        editBtn.setOnAction(event -> {
            try {
                Payment payment =
                        Payment
                                .builder()
                                .title(titleTxt.getText())
                                .amount(Integer.parseInt(amountTxt.getText()))
                                .dateTime(LocalDate.parse(String.valueOf(datePick.getValue())))
                                .description(descriptionTxt.getText())
                                .paymentType(PaymentType.valueOf(paymentCmb.getSelectionModel().getSelectedItem()))
                                .build();

                PaymentService.edit(payment);
                log.info("Product Edited " + payment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();}
        });


        removeBtn.setOnAction(event -> {
            try {
                PaymentService.remove(Integer.parseInt(idTxt.getText()));
                log.info("Product Removed By ID =" + idTxt.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Removed Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


    }

    private void resetForm() {
            try {
                for (PaymentType value : PaymentType.values()) {
                    paymentCmb.getItems().add(value.toString());
                }
                paymentCmb.getSelectionModel().select(2);

                datePick.setValue(LocalDate.now());

                idTxt.clear();
                titleTxt.clear();
                amountTxt.clear();
                descriptionTxt.clear();
                //refreshTable(PaymentService.findAll());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();}
        }

        /**private void refreshTable(List<Payment> paymentList) {
         ObservableList<Payment> observableList = FXCollections.observableList(paymentList);

         idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
         titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
         amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
         paymentTbl.setItems(observableList);
         }
         **/
    }

