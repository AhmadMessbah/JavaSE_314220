package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import mft.model.entity.Product;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;
import mft.model.service.ProductService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class ProductController implements Initializable {
    @FXML
    private TextField idTxt, nameTxt, priceTxt, quantityTxt, discountTxt, findNameTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn, newBtn;

    @FXML
    private ComboBox<String> categoryCmb;

    @FXML
    private DatePicker expireDate;

    @FXML
    private CheckBox imageChk, catalogueChk;

    @FXML
    private ToggleGroup typeToggleGroup;

    @FXML
    private TableView<Product> productTbl;

    @FXML
    private TableColumn<Product, Integer> idCol, priceCol, quantityCol;

    @FXML
    private TableColumn<Product, String> nameCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug ("Product Form Loaded");

        resetForm();

        newBtn.setOnAction(event -> {
            resetForm();
        });

        saveBtn.setOnAction(event -> {
            try {
                RadioButton radioButton = (RadioButton) typeToggleGroup.getSelectedToggle();
                Product product =
                        Product
                                .builder()
                                .name(nameTxt.getText())
                                .discount((Integer.parseInt(discountTxt.getText())))
                                .category(Category.valueOf(categoryCmb.getSelectionModel().getSelectedItem()))
                                .transactionType(TransactionType.valueOf(radioButton.getText()))
                                .expireDate(expireDate.getValue())
                                .image(imageChk.isSelected())
                                .catalogue(catalogueChk.isSelected())
                                .build();

                product.setPrice(Integer.parseInt(priceTxt.getText()));
                product.setQuantity(Integer.parseInt(quantityTxt.getText()));

                ProductService.save(product);
                log.info("Product Saved " + product);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successful");
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
                RadioButton radioButton = (RadioButton) typeToggleGroup.getSelectedToggle();
                Product product =
                        Product
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .name(nameTxt.getText())
                                .price(Integer.parseInt(priceTxt.getText()))
                                .quantity(Integer.parseInt(quantityTxt.getText()))
                                .discount((Integer.parseInt(discountTxt.getText())))
                                .category(Category.valueOf(categoryCmb.getSelectionModel().getSelectedItem()))
                                .transactionType(TransactionType.valueOf(radioButton.getText()))
                                .expireDate(expireDate.getValue())
                                .image(imageChk.isSelected())
                                .catalogue(catalogueChk.isSelected())
                                .build();
                ProductService.edit(product);
                log.info("Product Edited " + product);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successful");
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
                ProductService.remove(Integer.parseInt(idTxt.getText()));
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

        findNameTxt.setOnKeyReleased(event -> {
            try {
                List<Product> productList = ProductService.findByName(findNameTxt.getText()+"%");
                refreshTable(productList);
                log.info("Find Products By Name = " + findNameTxt.getText());
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        productTbl.setOnMouseClicked(event -> {
            Product product = productTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(product.getId()));
            nameTxt.setText(product.getName());
            priceTxt.setText(String.valueOf(product.getPrice()));
            quantityTxt.setText(String.valueOf(product.getQuantity()));
            discountTxt.setText(String.valueOf(product.getDiscount()));
            expireDate.setValue(product.getExpireDate());
            imageChk.setSelected(product.isImage());
            catalogueChk.setSelected(product.isCatalogue());
        });
    }

    private void resetForm() {
        try {
            for (Category value : Category.values()) {
                categoryCmb.getItems().add(value.toString());
            }
            categoryCmb.getSelectionModel().select(2);

            expireDate.setValue(LocalDate.now());

            imageChk.setSelected(true);

            idTxt.clear();
            nameTxt.clear();
            priceTxt.clear();
            quantityTxt.clear();
            discountTxt.setText("0");
            refreshTable(ProductService.findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    private void refreshTable(List<Product> productList) {
        ObservableList<Product> observableList = FXCollections.observableList(productList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productTbl.setItems(observableList);
    }
}
