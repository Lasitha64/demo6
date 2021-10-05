package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Expenses {

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> month;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn_back;

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Vehicle.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void vre(ActionEvent event) {

    }

    @FXML
    void vse(ActionEvent event) {

    }

    @FXML
    void vte(ActionEvent event) {

    }

}
