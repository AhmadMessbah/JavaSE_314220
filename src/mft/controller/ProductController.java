package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.model.entity.Product;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;
import mft.model.service.ProductService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    private static int id = 0;
    private List<Product> productList = new ArrayList<>();

    @FXML
    private TextField idTxt, nameTxt, priceTxt, quantityTxt, discountTxt;

    @FXML
    private Button addBtn;

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
        resetForm();

        addBtn.setOnAction(event -> {
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
                productList.add(product);
                ProductService.saveAll(productList);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
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
            id++;
            for (Category value : Category.values()) {
                categoryCmb.getItems().add(value.toString());
            }
            categoryCmb.getSelectionModel().select(2);

            expireDate.setValue(LocalDate.now());

            imageChk.setSelected(true);

            idTxt.setText(String.valueOf(id));
            nameTxt.clear();
            priceTxt.clear();
            quantityTxt.clear();
            discountTxt.setText("0");
            productList = ProductService.findAll();
            refreshTable(productList);
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
