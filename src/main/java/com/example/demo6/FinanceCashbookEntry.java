//page c

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceCashbookEntry {

    @FXML
    private TextField enter_id;

    @FXML
    private DatePicker CBDate;

    @FXML
    private TextField CBdiscription;

    @FXML
    private TextField Ledger_forlio;

    @FXML
    private TextField value;

    @FXML
    private RadioButton Dr;

    @FXML
    private ToggleGroup Mtype;

    @FXML
    private RadioButton Cr;

    @FXML
    private Button Back;

    @FXML
    private Button Next;//Add button

    @FXML
    private Button ViewCahBook;

    @FXML
    void Add_to_the_database(ActionEvent event) {

    }

    @FXML
    void move_to_b(ActionEvent event) {

        Back.getScene().getWindow().hide();
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
    void move_to_r(ActionEvent event) {
        ViewCahBook.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceCashBookView.fxml"));
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

