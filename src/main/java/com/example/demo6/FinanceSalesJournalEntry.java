//page f

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

public class FinanceSalesJournalEntry {

    @FXML
    private TextField SJ_ID;

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


    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> SJCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        SJCollection = database.getCollection("Finance Sales Journal");
    }


    MongoDatabase database;

    //************************************************************************************************************



    @FXML
    void add_to_db(ActionEvent event) {
        try {

            //Get the values from the UI
            //Enter the id
            String SalesJournal_IDText = SJ_ID.getText() ,
                    SJDateText = SJDate.getValue().toString() ,
                    DisText = Description.getText() ,
                    invoice_noText  = invoice_no.getText(),
                    customerText  = customer.getText();
            double total_valueText = Double.parseDouble(total_value.getText());
            double ledger_pageText = Double.parseDouble(ledger_page.getText());
            insertGeneralJournal(SJCollection, SalesJournal_IDText, SJDateText, DisText, invoice_noText, customerText, total_valueText, ledger_pageText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertGeneralJournal(MongoCollection<Document> SJCollection, String SalesJournal_IDText, String SJDateText, String DisText, String invoice_noText, String customerText, double total_valueText, double ledger_pageText) {
        Document generator = new Document("_id", new ObjectId()).append("SalesJournal_ID", SalesJournal_IDText)
                .append("SJDate", SJDateText)
                .append("ledger_page", ledger_pageText)
                .append("Dis", DisText)
                .append("invoice_no", invoice_noText)
                .append("customer", customerText)
                .append("total_value", total_valueText);
        SJCollection.insertOne(generator);
        System.out.println("Connection S3");
    }

    @FXML
    void back_to_b(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
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
    void move_to_p(ActionEvent event) {
        VeiwSalesJournal.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceSalesJournalView.fxml"));
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
