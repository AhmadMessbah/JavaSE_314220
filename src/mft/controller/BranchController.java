package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import mft.model.entity.Branch;
import mft.model.service.BranchService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class BranchController implements Initializable {

    @FXML
    private TextField addressTxt, areaTxt,  idTxt, phoneTxt, searchBar, titleTxt;

    @FXML
    private Button btnEdit, btnNew, btnRemove, btnSave, searchBtn;

    @FXML
    private CheckBox activeCheckBox;

    @FXML
    private TableColumn<Branch, String> addressC;

    @FXML
    private TableView<Branch> branchTable;

    @FXML
    private TableColumn<Branch, Integer> idC;

    @FXML
    private TableColumn<Branch, String> phoneC;

    @FXML
    private TableColumn<Branch, String> titleC;

    public void resetForm() {
        try {
            idTxt.clear();
            titleTxt.clear();
            phoneTxt.clear();
            areaTxt.clear();
            addressTxt.clear();
            searchBar.clear();
            activeCheckBox.setSelected(false);
            updateTable(BranchService.getAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    public void updateTable(List<Branch> branchList) {
        ObservableList<Branch> branchObservableList = FXCollections.observableList(branchList);
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleC.setCellValueFactory(new PropertyValueFactory<>("title"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneC.setCellValueFactory(new PropertyValueFactory<>("phone"));
        branchTable.setItems(branchObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("Form Loaded");

        resetForm();

        btnNew.setOnAction(event -> {
            resetForm();
        });

        btnSave.setOnAction(event -> {
            try {
                Branch branch = Branch.builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .title(titleTxt.getText())
                        .address(addressTxt.getText())
                        .phone(phoneTxt.getText())
                        .active(activeCheckBox.isSelected())
                        .build();
                BranchService.create(branch);
                log.info("New Branch Added: " + branch);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        btnEdit.setOnAction(event -> {
            try {
                Branch branch = Branch.builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .title(titleTxt.getText())
                        .address(addressTxt.getText())
                        .phone(phoneTxt.getText())
                        .active(activeCheckBox.isSelected())
                        .build();
                BranchService.update(branch);
                log.info("Branch Updated: " + branch);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        btnRemove.setOnAction(event -> {
            try {
                BranchService.delete(Integer.parseInt(idTxt.getText()));
                log.info("Branch Deleted: " + idTxt.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Removed Successful");
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        branchTable.setOnMouseClicked(
                event -> {
                    Branch branch = branchTable.getSelectionModel().getSelectedItem();
                    idTxt.setText(String.valueOf(branch.getId()));
                    titleTxt.setText(branch.getTitle());
                    areaTxt.setText(branch.getAddress());
                    phoneTxt.setText(branch.getPhone());
                    activeCheckBox.setSelected(branch.isActive());
                }
        );

        searchBtn.setOnAction(event -> {
            try {
                List<Branch> branchList = BranchService.getByTitle(searchBar.getText() + "%");
                updateTable(branchList);
                log.info("Search with Query: " + searchBar.getText());
                searchBar.clear();
            } catch (Exception e) {
                log.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });
    }
}
