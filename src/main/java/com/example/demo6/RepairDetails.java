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

public class RepairDetails {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_gen;

    @FXML
    private Button btn_add1;

    @FXML
    private Button btn_gen1;

    @FXML
    void Add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addRepairDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/addServiceDetails.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Vehicle.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Generate(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("viewRepairDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/addServiceDetails.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


    }

}
