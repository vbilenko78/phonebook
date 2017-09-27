package com.javafx.controllers;

import com.javafx.objects.Person;
import com.javafx.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class EditDialogController implements Initializable {

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
    private ResourceBundle resourceBundle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }

    void setPerson(Person person) {
        if (person == null) {
            return;
        }
        this.person = person;
        txtName.setText(person.getName());
        txtPhone.setText(person.getPhone());
        txtEmail.setText(person.getEmail());
    }

    Person getPerson() {
        return person;
    }

    public void actionClose(ActionEvent actionEvent) {
        btnCancel.isCancelButton();
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }


    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()) {
            return;
        }
        person.setName(txtName.getText());
        person.setPhone(txtPhone.getText());
        person.setEmail(txtEmail.getText());
        actionClose(actionEvent);
    }

    private boolean checkValues() {
        if (txtName.getText().trim().length() == 0 || txtPhone.getText().trim().length() == 0 || txtEmail.getText().trim().length() == 0) {
            DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("fill_field"));
            return false;
        }

        return true;
    }

    public void actionDelete(ActionEvent actionEvent) {
        person.setName("");
        person.setPhone("");
        person.setEmail("");
        actionClose(actionEvent);
    }
}
