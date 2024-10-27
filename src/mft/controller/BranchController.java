package mft.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.model.entity.Branch;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import mft.model.service.BranchService;


public class BranchController implements Initializable {

    private static int id = 0;

    private List<Branch> branchList = new ArrayList<>();

    @FXML
    private TextField idTxt, titleTxt, addressTxt, phoneTxt, areaTxt;

    @FXML
    private CheckBox activeCHBX;

    @FXML
    private TableView<Branch> branchTable;

    @FXML
    private TableColumn<Branch, Integer> idC;

    @FXML
    private TableColumn<Branch, String> titleC, addressC, phoneC;

    @FXML
    private Button addBtn;

    private void tableUpdate(List<Branch> branchList) {
        ObservableList<Branch> branchObservableList = FXCollections.observableList(branchList);

        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleC.setCellValueFactory(new PropertyValueFactory<>("title"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneC.setCellValueFactory(new PropertyValueFactory<>("phone"));

        branchTable.setItems(branchObservableList);
    }

    private void formUpdate() {
        try {
            id++;
            idTxt.setText(String.valueOf(id));
            titleTxt.clear();
            addressTxt.clear();
            phoneTxt.clear();
            areaTxt.clear();
            activeCHBX.setSelected(false);
            tableUpdate(this.branchList);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong:\n" + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.formUpdate();

        addBtn.setOnAction(event -> {
            try {
                Branch branch = Branch.builder()
                        .id(Integer.parseInt(this.idTxt.getText()))
                        .title(titleTxt.getText())
                        .address(addressTxt.getText())
                        .phone(phoneTxt.getText())
                        .area(areaTxt.getText())
                        .active(activeCHBX.isSelected())
                        .build();
                this.branchList.add(branch);
                BranchService.saveAll(this.branchList);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully", ButtonType.OK);
                alert.showAndWait();
                this.formUpdate();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong:\n" + e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        });

        branchTable.setOnMouseClicked(event -> {
             Branch branch = branchTable.getSelectionModel().getSelectedItem();
             idTxt.setText(String.valueOf(branch.getId()));
             titleTxt.setText(branch.getTitle());
             addressTxt.setText(branch.getAddress());
             phoneTxt.setText(branch.getPhone());
             areaTxt.setText(branch.getArea());
             activeCHBX.setSelected(branch.isActive());
        });
    }
}
