//page j

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneratorDeselUsageChart {

    @FXML
    private LineChart<?, ?> GeneDeselUsage;

    @FXML
    private Button Back;

    @FXML
    void back_to_i(ActionEvent event) {
        Back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneatorDeselUsage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Gene.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}

