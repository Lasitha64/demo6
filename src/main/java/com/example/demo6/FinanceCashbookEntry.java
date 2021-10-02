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
import org.bson.types.ObjectId;

import java.io.IOException;

public class FinanceCashbookEntry {
    int n = 0;
    int m = 0;

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

    MongoCollection<Document> CBCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        CBCollection = database.getCollection("Finance Cash Book");
    }


    MongoDatabase database;

    //************************************************************************************************************

    @FXML
    int give_typeDr(ActionEvent event) {
        return n = 1;
    }

    @FXML
    int give_typeCr(ActionEvent event) {
        return m = 1;
    }

    @FXML
    void Add_to_the_database(ActionEvent event) {
        try {
            //Get the values from the UI
            //Enter the id
            String enter_idText = enter_id.getText() ,
                    CBDateText = CBDate.getValue().toString() ,
                    CBdiscriptionText = CBdiscription.getText() ,
                    Ledger_forlioText  = Ledger_forlio.getText();
            double valueText = Double.parseDouble(value.getText());

            if(n == 1 && m == 0) {
              //  String DrCrText = Cr.getText();
                insertCB_Dr(CBCollection, enter_idText, CBDateText, CBdiscriptionText, Ledger_forlioText, valueText/*, DrCrText*/);
            }
            else if (n == 0 && m == 1){
               // String DrCrText = Cr.getText();
                insertCB_Cr(CBCollection, enter_idText, CBDateText, CBdiscriptionText, Ledger_forlioText, valueText /*, DrCrText*/);
            }
            else {System.out.println("please enter suitable values");}


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void insertCB_Dr(MongoCollection<Document> CBCollection, String enter_idText, String CBDateText, String CBdiscriptionText, String Ledger_forlioText, double valueText/*, String DrCrText*/) {
        Document generator = new Document("_id", new ObjectId()).append("CB ID", enter_idText)
                .append("Dr CBDate", CBDateText)
                .append("Dr CBdiscription", CBdiscriptionText)
                .append("Dr Ledger_forlio", Ledger_forlioText)
                .append("Dr value", valueText);
            //    .append("Type", DrCrText);
        CBCollection.insertOne(generator);
        System.out.println("Connection S3");
    }

    private void insertCB_Cr(MongoCollection<Document> CBCollection, String enter_idText, String CBDateText, String CBdiscriptionText, String Ledger_forlioText, double valueText/*, String DrCrText*/) {
        Document generator = new Document("_id", new ObjectId()).append("CB ID", enter_idText)
                .append("Cr CBDate", CBDateText)
                .append("Cr CBdiscription", CBdiscriptionText)
                .append("Cr Ledger_forlio", Ledger_forlioText)
                .append("Cr value", valueText);
              //  .append("Type", DrCrText);
        CBCollection.insertOne(generator);
        System.out.println("Connection S3");
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

