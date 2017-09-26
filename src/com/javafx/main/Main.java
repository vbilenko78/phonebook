package com.javafx.main;

import com.javafx.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/main.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("com.javafx.bundles.Locale", new Locale("en")));

        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle(fxmlLoader.getResources().getString("title"));
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(670);
        primaryStage.setScene(new Scene(fxmlMain, 450, 475));
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