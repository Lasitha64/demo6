package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLogin {

    @FXML
    private TextField inputusername;

    @FXML
    private TextField inputpassword;

    @FXML
    private Button loginbutton;

    @FXML
    private Button alterbutton;

    @FXML
    private Button createaccountbutton;

    @FXML
    private Button backbutton;

    @FXML
    private Button mainmenu;

    @FXML
    void alter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminUpdateDelete.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminUpdateDelete.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void createaccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminSignUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminSignUp.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void login(ActionEvent event) {

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

