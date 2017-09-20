package com.javafx.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends VBox {

    @FXML
    private Button newRecordBtn;

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
