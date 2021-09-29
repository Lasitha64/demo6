//page i

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceProfitOrLossAccountView {

    @FXML
    private TableColumn<?, ?> Date;

    @FXML
    private TableColumn<?, ?> Sales;

    @FXML
    private TableColumn<?, ?> GrossProfit;

    @FXML
    private TableColumn<?, ?> NetProfit;

    @FXML
    private Button btn_back;

    @FXML
    private Button graphs;

    @FXML
    void back_to_a(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("XXX.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/Finance.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void move_to_j(ActionEvent event) {
        graphs.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitGraphs.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/Finance.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}

