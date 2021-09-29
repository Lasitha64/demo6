//page d

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceGeneralJournalEntry {

    @FXML
    private TextField Description;

    @FXML
    private DatePicker GJDate;

    @FXML
    private TextField VoucherNo;

    @FXML
    private TextField Debit;

    @FXML
    private TextField Credit;

    @FXML
    private Button btn_back;

    @FXML
    private Button add;

    @FXML
    private Button VeiwGeneralJournal;

    @FXML
    void add_to_database(ActionEvent event) {

    }

    @FXML
    void move_to_b(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceEntries.fxml"));
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
    void move_to_o(ActionEvent event) {
        VeiwGeneralJournal.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceGeneralJournalView.fxml"));
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
