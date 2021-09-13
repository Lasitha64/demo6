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

public class AdminUpdateDelete {

    @FXML
    private Button updatebutton;

    @FXML
    private Button deletebutton;

    @FXML
    private TextField inputname;

    @FXML
    private TextField inputusername;

    @FXML
    private TextField inputemail;

    @FXML
    private TextField inputnic;

    @FXML
    private TextField inputmobile;

    @FXML
    private TextField inputpassword;

    @FXML
    private Button backbutton;

    @FXML
    private Button mainmenu;

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminLogin.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteaccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminLogin.css").toExternalForm());
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

    @FXML
    void updateaccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminLogin.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

}

