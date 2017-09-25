package com.javafx.controllers;

import com.javafx.interfaces.impl.CollectionAddressBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.javafx.objects.Person;
import sun.applet.Main;

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

    @FXML
    private TableView tableAddressBook;

    private Person person;
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

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
        btnSave.isDefaultButton();
        person.setName(txtName.getText());
        person.setPhone(txtPhone.getText());
        person.setEmail(txtEmail.getText());

        actionClose(actionEvent);
    }

    public void actionDelete(ActionEvent actionEvent) {
        person.setName("");
        person.setPhone("");
        person.setEmail("");

        actionClose(actionEvent);
    }

}