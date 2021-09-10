package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {

    @FXML
    private Button btn_ve;

    @FXML
    private Button btn_cr;

    @FXML
    private Button btn_hm;

    @FXML
    private Button btn_ot;

    @FXML
    private Button btn_lo;

    @FXML
    private Button btn_ex;

    @FXML
    private Button btn_sl;

    @FXML
    private Button btn_em;

    @FXML
    private Button adminbutton;

    @FXML
    void admin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminDetails.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void crusheraction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("crusher-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/crusher.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void employeeaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeDetails.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void excavatoraction(ActionEvent event) {

    }

    @FXML
    void hammeraction(ActionEvent event) {

    }

    @FXML
    void loaderaction(ActionEvent event) {

    }

    @FXML
    void otheraction(ActionEvent event) {

    }

    @FXML
    void salesaction(ActionEvent event) {

    }

    @FXML
    void vechicleaction(ActionEvent event) {

    }

}
