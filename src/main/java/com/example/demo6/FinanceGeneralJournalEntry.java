//page d

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

public class FinanceGeneralJournalEntry {

    private AlertBox ab;

 //   int count;
    @FXML
    private TextField GJ_ID;

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



    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> GJCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        GJCollection = database.getCollection("Finance General Journal");
    }


    MongoDatabase database;

    //************************************************************************************************************


    @FXML
    void add_to_database(ActionEvent event) {

        if(GJ_ID.getText().isEmpty() || Description.getText().isEmpty() || VoucherNo.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else {

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
                String GeneralJournal_IDText = GJ_ID.getText(),
                        GJDateText = GJDate.getValue().toString(),
                        DisText = Description.getText(),
                        VoNoText = VoucherNo.getText();
                double DrText = Double.parseDouble(Debit.getText());
                double CrText = Double.parseDouble(Credit.getText());
                insertGeneralJournal(GJCollection, GeneralJournal_IDText, GJDateText, DisText, VoNoText, DrText, CrText);

                ab.display("OK", "Entry Added Successfully");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void insertGeneralJournal(MongoCollection<Document> GJCollection, String GeneralJournal_IDText, String GJDateText, String DisText, String VoNoText, double DrText, double CrText) {
        Document generator = new Document("_id", new ObjectId()).append("GeneralJournal_ID", GeneralJournal_IDText)
                .append("GJDate", GJDateText)
                .append("VoNo", VoNoText)
                .append("Dis", DisText)
                .append("Dr", DrText)
                .append("Cr", CrText);
        GJCollection.insertOne(generator);
        System.out.println("Connection S3");
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
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
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
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}
