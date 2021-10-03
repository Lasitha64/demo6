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

public class StcockMngt {

    @FXML
    private Button buttn_view;

    @FXML
    private Button buttn_lub;

    @FXML
    private Button buttn_rep;

    @FXML
    private Button buttn_back;

    @FXML
    private Button buttn_add;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Generate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stock-report.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-report.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void View(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("item-main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/item-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Viewlub(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lubricant-main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/lubricant-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void AddStock(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-new-Stock.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/add-new-Stock.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}

