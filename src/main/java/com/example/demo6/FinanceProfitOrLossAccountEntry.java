package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button delete_bn;

    @FXML
    private Button Update_bn;

    MongoCollection<Document> POLCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        POLCollection = database.getCollection("Profit or loss account");
    }


    MongoDatabase database;


    @FXML
    void Updatedb(ActionEvent event) {
        // update one document

        String POL_ID = ID.getText();
        double SalesText = Double.parseDouble(Sales.getText());
        double Cost_of_salesText = Double.parseDouble(Credit.getText());
        double other_incomeText = Double.parseDouble(other_income.getText());
        double other_expensesText = Double.parseDouble(other_expenses.getText());

        System.out.println(POL_ID + SalesText + Cost_of_salesText + other_incomeText + other_expensesText);

        Bson filter = eq("Stock_ID", POL_ID);


        Bson updateSales = set("Sales", SalesText);
        Bson updateCost_of_sales = set("Cost_of_sales", Cost_of_salesText);
        Bson updateother_income = set("other_income", other_incomeText);
        Bson updateother_expenses = set("Prise", other_expensesText);



        List<Bson> updatePredicates = new ArrayList<Bson>();
        updatePredicates.add(updateSales);
        updatePredicates.add(updateCost_of_sales);
        updatePredicates.add(updateother_income);
        updatePredicates.add(updateother_expenses);


        //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/

        FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Document newVersion = POLCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

        System.out.println("Updating the Profit or loss account");
        System.out.println(newVersion);

    }

    @FXML
    void back_to_b(ActionEvent event) {

    }

    @FXML
    void deletedb(ActionEvent event) {
        String IDText = ID.getText();
        Bson filter = eq("ID", IDText);
        DeleteResult result = POLCollection.deleteOne(filter);
        System.out.println(result);

    }

    @FXML
    void move_to_h(ActionEvent event) {
        try {
            //Get the values from the UI
            //Enter the id
            String POL_ID = ID.getText();
            double SalesText = Double.parseDouble(Sales.getText());
            double Cost_of_salesText = Double.parseDouble(Credit.getText());
            double other_incomeText = Double.parseDouble(other_income.getText());
            double other_expensesText = Double.parseDouble(other_expenses.getText());
            insertPOLStock(POLCollection, POL_ID, SalesText, Cost_of_salesText, other_incomeText, other_expensesText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPOLStock(MongoCollection<Document> POLCollection, String POL_ID, double SalesText, double Cost_of_salesText, double other_incomeText, double other_expensesText) {
        Document generator = new Document("_id", new ObjectId()).append("entry ID", POL_ID)
                .append("Sales", SalesText)
                .append("Cost_of_sales", Cost_of_salesText)
                .append("other_income", other_incomeText)
                .append("other_expenses", other_expensesText);
        POLCollection.insertOne(generator);
        System.out.println("Connection S3");

    }

}

