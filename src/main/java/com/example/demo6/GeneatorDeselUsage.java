//page i

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneatorDeselUsage {

    @FXML
    private Spinner<?> g_issue_desel;

    @FXML
    private Spinner<?> g_remain_desel;

    @FXML
    private Button Back;

    @FXML
    private Button UsageChart;

    @FXML
    void back_to_b(ActionEvent event) {
        Back.getScene().getWindow().hide();
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
    void go_to_j(ActionEvent event) {
        UsageChart.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneratorDeselUsageChart.fxml"));
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
