//page j

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceProfitGraphs {

    @FXML
    private Button Home;

    @FXML
    private Button btn_back;

    @FXML
    private Button Sales;

    @FXML
    private Button GrossProfit;

    @FXML
    private Button NetProfit;

    @FXML
    void back_to_i(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void move_to_a(ActionEvent event) {
        Home.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceMain.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void move_to_k(ActionEvent event) {
        Sales.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceZsalesgraph.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void move_to_l(ActionEvent event) {
        GrossProfit.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceZgrossprofitgraph.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void move_to_m(ActionEvent event) {
        NetProfit.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceZnetProfitgraph.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}

