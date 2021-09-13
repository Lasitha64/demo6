/*
@author..kisaja
 */

//page a
package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneratorMainPage {

    @FXML
    private TextField GenIDEnter;

    @FXML
    private Button Back;

    @FXML
    private Button Parts;

    @FXML
    private Button Add_new_generator;

    @FXML
    private Button Search;

    @FXML
    void go_to_b(ActionEvent event) {
        Search.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ViewGenerator.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/main.css");
        scene.getStylesheets().add("stylesheet/login.css");
        scene.getStylesheets().add("stylesheet/GeneratorA.css");
        scene.getStylesheets().add("stylesheet/GeneratorB.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void go_to_e(ActionEvent event) {
        Parts.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneratorParts.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/main.css");
        scene.getStylesheets().add("stylesheet/login.css");
        scene.getStylesheets().add("stylesheet/GeneratorA.css");
        scene.getStylesheets().add("stylesheet/GeneratorB.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void go_to_g(ActionEvent event) {
        Add_new_generator.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddNewGenerator.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/main.css");
        scene.getStylesheets().add("stylesheet/login.css");
        scene.getStylesheets().add("stylesheet/GeneratorA.css");
        scene.getStylesheets().add("stylesheet/GeneratorB.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void go_to_home(ActionEvent event) {
        Back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/main.css");
        scene.getStylesheets().add("stylesheet/login.css");
        scene.getStylesheets().add("stylesheet/GeneratorA.css");
        scene.getStylesheets().add("stylesheet/GeneratorB.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}

