package com.javafx.controllers;

import com.javafx.interfaces.impl.CollectionAddressBook;
import com.javafx.objects.Person;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    private Stage mainStage;

    @FXML
    private Button btnNewRecord;

    @FXML
    private Button btnEdit;

    @FXML
    private TextField barSearch;

    @FXML
    private Button btnFind;

    @FXML
    private TableView tableAddressBook;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TableColumn<Person, String> colPhone;

    @FXML
    private TableColumn<Person, String> colEmail;

    @FXML
    private Label lblTotalRecs;


    private Parent fxmlEdit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private EditDialogController editDialogController;

    private Stage editDialogStage;

    private ResourceBundle resourceBundle;


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        initListeners();
        fillData();
        initLoader();
    }

    private void fillData() {
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());
    }

    private void initListeners() {
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });


        tableAddressBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });


    }

    private void initLoader() {
        try {

            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("com.javafx.bundles.Locale", new Locale("ru")));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCountLabel() {
        lblTotalRecs.setText(resourceBundle.getString("total_records_lbl") + ": " + addressBookImpl.getPersonList().size());
    }

    public MainController actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return null;
        }

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnNewRecord":
                editDialogController.setPerson(new Person());
                showDialog();
                addressBookImpl.add(editDialogController.getPerson());
                break;

            case "btnEdit":
                editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                showDialog();
                break;

            case "btnDeleteRec":
                addressBookImpl.delete((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                break;
        }

        return null;
    }


    private void showDialog() {

        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle(resourceBundle.getString("edit_rec_title"));
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait(); // для ожидания закрытия окна

    }

}
