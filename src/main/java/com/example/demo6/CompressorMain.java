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


public class CompressorMain {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button com_D;

    @FXML
    private Button com_S;

    @FXML
    private Button com_Ex;
    @FXML
    private Button comBK;

    @FXML
    void comdId(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CompressorDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void comexId(ActionEvent event) {

    }

    @FXML
    void comsId(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("compressor-service.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void comBCK(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("h&cMain.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }



}
