package com.javafx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        primaryStage.setTitle("Phone Book");
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(640);
        primaryStage.setScene(new Scene(root, 300, 475));
        primaryStage.show();
    }

    private static void testData() {
//        CollectionAddressBook addressBook = new CollectionAddressBook();
//        addressBook.fillTestData();
//        addressBook.print();
    }

    public static void main(String[] args) {
        launch(args);
    }
}