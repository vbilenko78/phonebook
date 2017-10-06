package com.javafx.controllers;

import com.javafx.interfaces.impl.DBAddressBook;
import com.javafx.objects.Person;
import com.javafx.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class EditDialogController implements Initializable {

    private DBAddressBook addressBookImpl = new DBAddressBook();
    private static final String FXML_MAIN = "../fxml/main.fxml";

    @FXML
    private TableView tableAddressBook;

    @FXML
    private Button btnDeleteRec;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    private Person person;
    private boolean editMode;
    private ResourceBundle resourceBundle;
    private boolean saveClicked = false;
    private boolean deleteClicked = false;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;

        if (editMode){
            btnDeleteRec.setVisible(true);
        }else{
            btnDeleteRec.setVisible(false);
        }
    }

    void setPerson(Person person) {
        if (person == null) {
            return;
        }
        saveClicked = false;
        this.person = person;
        txtName.setText(person.getName());
        txtPhone.setText(person.getPhone());
        txtEmail.setText(person.getEmail());
    }

    Person getPerson() {
        return person;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionDelete(ActionEvent actionEvent){
        deleteClicked = true;
        actionClose(actionEvent);
    }


    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()) {
            return;
        }
        person.setName(txtName.getText());
        person.setPhone(txtPhone.getText());
        person.setEmail(txtEmail.getText());
        saveClicked = true;
        actionClose(actionEvent);
    }



    private boolean checkValues() {
        if (txtName.getText().trim().length() == 0 || txtPhone.getText().trim().length() == 0 || txtEmail.getText().trim().length() == 0) {
            DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("fill_field"));
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        fxmlLoader.setLocation(getClass().getResource(FXML_MAIN));


    }

    public boolean isSaveClicked() {
        return saveClicked;
    }


    public boolean isDeleteClicked(){
        return deleteClicked;
    }


    //delete record from the modal window
    public void actionButtonPressedModal(ActionEvent actionEvent) {
        actionClose(actionEvent);

    }
}

