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

public class StockReportMain {

    @FXML
    private Button buttn_ireport;

    @FXML
    private Button buttn_lreport;

    @FXML
    private Button buttn_back;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stcock-mngt.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-mngt.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void GenarateIt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stock-report.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-report.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void GenarateLub(ActionEvent event) {

    }

}

