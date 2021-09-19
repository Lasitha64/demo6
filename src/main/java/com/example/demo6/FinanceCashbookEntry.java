package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    private Button Next;

    @FXML
    private Button ViewCahBook;

    @FXML
    void Add_to_the_database(ActionEvent event) {

    }

    @FXML
    void move_to_b(ActionEvent event) {

    }

    @FXML
    void move_to_r(ActionEvent event) {

    }

}

