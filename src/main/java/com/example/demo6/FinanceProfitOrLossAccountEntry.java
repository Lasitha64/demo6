//page e

package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FinanceProfitOrLossAccountEntry {

    @FXML
    private Label Cost_of_sales;

    @FXML
    private TextField ID;

    @FXML
    private DatePicker POLdate;

    @FXML
    private TextField Sales;

    @FXML
    private TextField Credit;

    @FXML
    private TextField other_income;

    @FXML
    private TextField other_expenses;

    @FXML
    private Button btn_back;

    @FXML
    private Button Next;

    @FXML
    private Button Update_or_delete_bn;


    //************************************************************************************************************
    //DB connection
    MongoCollection<Document> POLCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        POLCollection = database.getCollection("Finance Profit or loss account");
    }


    MongoDatabase database;

   //*************************************************************************************************************


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
    void move_to_h(ActionEvent event) {
//        Next.getScene().getWindow().hide();
//        Stage signup = new Stage();
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountEntryCnfermation.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
//        signup.setScene(scene);
//        signup.show();
//        signup.setResizable(false);


        //entering the values
        try {
            //Get the values from the UI
            //Enter the id
            String POL_ID = ID.getText(),
                   PLdateText = POLdate.getValue().toString();
            double SalesText = Double.parseDouble(Sales.getText());
            double Cost_of_salesText = Double.parseDouble(Credit.getText());
            double other_incomeText = Double.parseDouble(other_income.getText());
            double other_expensesText = Double.parseDouble(other_expenses.getText());

            double GrossProfit = SalesText - Cost_of_salesText;
            double NetProfit = GrossProfit + other_incomeText - other_expensesText;

            insertPOLStock(POLCollection, POL_ID, PLdateText, SalesText, Cost_of_salesText, other_incomeText, other_expensesText, GrossProfit, NetProfit);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPOLStock(MongoCollection<Document> POLCollection, String POL_ID, String PLdateText, double SalesText, double Cost_of_salesText, double other_incomeText, double other_expensesText, double GrossProfit, double NetProfit) {
        Document generator = new Document("_id", new ObjectId()).append("entry ID", POL_ID)
                .append("Date", PLdateText)
                .append("Sales", SalesText)
                .append("Cost_of_sales", Cost_of_salesText)
                .append("other_income", other_incomeText)
                .append("other_expenses", other_expensesText)
                .append("GrossProfit", GrossProfit)
                .append("NetProfit", NetProfit);
        POLCollection.insertOne(generator);
        System.out.println("Connection S3");

    }

    @FXML
    void move_to_s(ActionEvent event) {
        Update_or_delete_bn.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountKey.fxml"));
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

