package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FinanceSalesJournalEntry {

    @FXML
    private Label Description;

    @FXML
    private DatePicker SJDate;

    @FXML
    private TextField invoice_no;

    @FXML
    private TextField customer;

    @FXML
    private TextField total_value;

    @FXML
    private TextField ledger_page;

    @FXML
    private Button btn_back;

    @FXML
    private Button add;

    @FXML
    private Button VeiwSalesJournal;

    @FXML
    void add_to_db(ActionEvent event) {

    }

    @FXML
    void back_to_b(ActionEvent event) {

    }

    @FXML
    void move_to_p(ActionEvent event) {

    }

}
