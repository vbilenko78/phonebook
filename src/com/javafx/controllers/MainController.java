package com.javafx.controllers;

import com.javafx.interfaces.impl.CollectionAddressBook;
import com.javafx.objects.Person;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends VBox {

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    @FXML
    private Button newRecordBtn;

    @FXML
    private TextField barSearch;

    @FXML
    private Button btnFind;

    @FXML
    private TableView tblAddressBook;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TableColumn<Person, String> colPhone;

    @FXML
    private TableColumn<Person, String> colEmail;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnNewRecord;

    @FXML
    private Label lblTotalRecs;

    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        addressBookImpl.getPersonList().addListener((ListChangeListener<Person>) c -> updateCountLabel());

        addressBookImpl.fillTestData();
        tblAddressBook.setItems(addressBookImpl.getPersonList());
    }

    private void updateCountLabel() {
        lblTotalRecs.setText("Total Records: " + addressBookImpl.getPersonList().size());
    }


    public void showDialog(ActionEvent actionEvent) {

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
            stage.setTitle("Edit Record");
            stage.setMinWidth(300);
            stage.setMinHeight(150);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
