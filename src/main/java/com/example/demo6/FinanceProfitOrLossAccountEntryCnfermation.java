//page h

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceProfitOrLossAccountEntryCnfermation {

    @FXML
    private DatePicker date;

    @FXML
    private Label Sales;

    @FXML
    private Label CostOfSales;

    @FXML
    private Label GrossProfit;

    @FXML
    private Label otherincome;

    @FXML
    private Label otherExpenses;

    @FXML
    private Label NetProfit;

    @FXML
    private Button btn_back;

    @FXML
    private Button Add;

    @FXML
    private Button POLview;

    @FXML
    void Add_to_db(ActionEvent event) {

    }

    @FXML
    void back_to_e(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountEntry.fxml"));
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
    void get_date(ActionEvent event) {

    }

    @FXML
    void move_to_i(ActionEvent event) {
        POLview.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountView.fxml"));
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

