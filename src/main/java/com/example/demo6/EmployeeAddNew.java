package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.io.IOException;

public class EmployeeAddNew {

    @FXML
    private Button employeeaddnewbutton;

    @FXML
    private TextField employeeinputname;

    @FXML
    private TextField employeeinputaddress;

    @FXML
    private TextField employeeinputnic;

    @FXML
    private TextField employeeinputmobile;

    @FXML
    private Button backbutton;

    @FXML
    private Button mainmenu;
    private MongoClient database;

    @FXML
    void addnewemployeebutton(ActionEvent event) {



    }


    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeDetails.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void mainmenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

}

