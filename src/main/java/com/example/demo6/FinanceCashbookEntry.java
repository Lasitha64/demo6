//page c

package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
import org.bson.Document;

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


    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> POLCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        POLCollection = database.getCollection("Finance Cash Book");
    }


    MongoDatabase database;

    //************************************************************************************************************

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
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
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
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}

