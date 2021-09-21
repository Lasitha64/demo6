package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button btn_lg;

    @FXML
    private Button ex;

    @FXML
    void login(ActionEvent event) throws IOException {

        btn_lg.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    public void ex(ActionEvent actionEvent) {
        ex.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountEntry.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Finance/main.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
}