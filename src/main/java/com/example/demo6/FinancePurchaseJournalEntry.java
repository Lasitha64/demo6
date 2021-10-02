//page g

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

public class FinancePurchaseJournalEntry {
    @FXML
    private TextField PJ_ID;

    @FXML
    private Label Description;

    @FXML
    private DatePicker PJDate;

    @FXML
    private TextField invoice_no;

    @FXML
    private TextField Supplier;

    @FXML
    private TextField total_value;

    @FXML
    private TextField ledger_page;

    @FXML
    private Button btn_back;

    @FXML
    private Button add;

    @FXML
    private Button viewPJ;

    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> PJCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        PJCollection = database.getCollection("Finance Purchase Journal");
    }


    MongoDatabase database;

    //************************************************************************************************************


    @FXML
    void add_to_db(ActionEvent event) {
        try {

            //Auto increment
//
//            String GJ_id;
//
//            if (count==0){
//                GJ_id = "1";
//            }else{
//                GJ_id = String.valueOf(count+1);
//            }

            //Get the values from the UI
            //Enter the id
            String PurchaseJournal_IDText = PJ_ID.getText() ,
                    PJDateText = PJDate.getValue().toString() ,
                    DisText = Description.getText() ,
                    invoice_noText  = invoice_no.getText(),
                    SupplierText  = Supplier.getText();
            double total_valueText = Double.parseDouble(total_value.getText());
            double ledger_pageText = Double.parseDouble(ledger_page.getText());
            insertGeneralJournal(PJCollection, PurchaseJournal_IDText, PJDateText, DisText, invoice_noText, SupplierText, total_valueText, ledger_pageText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertGeneralJournal(MongoCollection<Document> PJCollection, String PurchaseJournal_IDText, String PJDateText, String DisText, String invoice_noText, String SupplierText, double total_valueText, double ledger_pageText) {
        Document generator = new Document("_id", new ObjectId()).append("GeneralJournal_ID", PurchaseJournal_IDText)
                .append("PJDate", PJDateText)
                .append("ledger_page", ledger_pageText)
                .append("Dis", DisText)
                .append("invoice_no", invoice_noText)
                .append("Supplier", SupplierText)
                .append("total_value", total_valueText);
        PJCollection.insertOne(generator);
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
    void back_to_q(ActionEvent event) {
        viewPJ.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinancePurchaseJournalView.fxml"));
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

