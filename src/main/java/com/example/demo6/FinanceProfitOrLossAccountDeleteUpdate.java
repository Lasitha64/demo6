//page t

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class FinanceProfitOrLossAccountDeleteUpdate {

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
    void Updatedb(ActionEvent event) {
        // update one document
        String POL_ID = ID.getText();
        double SalesText = Double.parseDouble(Sales.getText());
        double Cost_of_salesText = Double.parseDouble(Credit.getText());
        double other_incomeText = Double.parseDouble(other_income.getText());
        double other_expensesText = Double.parseDouble(other_expenses.getText());

        double GrossProfit = SalesText - Cost_of_salesText;
        double NetProfit = GrossProfit + other_incomeText - other_expensesText;

        System.out.println(POL_ID + SalesText + Cost_of_salesText + other_incomeText + other_expensesText + GrossProfit + NetProfit);

        Bson filter = eq("entry ID", POL_ID);


        Bson updateSales = set("Sales", SalesText); // creating an array with a comment.
        Bson updateCost_of_sales = set("Cost_of_sales", Cost_of_salesText); // using addToSet so no effect.
        Bson updateother_income = set("other_income", other_incomeText); // using addToSet so no effect.
        Bson updateother_expenses = set("other_expenses", other_expensesText); // using addToSet so no effect.

        Bson updateGrossProfit = set("GrossProfit", GrossProfit); // using addToSet so no effect.
        Bson updateNetProfit = set("NetProfit", NetProfit); // using addToSet so no effect.

        List<Bson> updatePredicates = new ArrayList<Bson>();
        updatePredicates.add(updateSales);
        updatePredicates.add(updateCost_of_sales);
        updatePredicates.add(updateother_income);
        updatePredicates.add(updateother_expenses);
        updatePredicates.add(updateGrossProfit);
        updatePredicates.add(updateNetProfit);


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
    void deletedb(ActionEvent event) {
        String POL_ID = ID.getText();
        Bson filter = eq("entry ID", POL_ID);
        DeleteResult result = POLCollection.deleteOne(filter);
        System.out.println(result);

    }



    @FXML
    void move_to_h(ActionEvent event) {
        Next.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountEntryCnfermation.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);




        //entering the values
        try {
            //Get the values from the UI
            //Enter the id
            String POL_ID = ID.getText();
            double SalesText = Double.parseDouble(Sales.getText());
            double Cost_of_salesText = Double.parseDouble(Credit.getText());
            double other_incomeText = Double.parseDouble(other_income.getText());
            double other_expensesText = Double.parseDouble(other_expenses.getText());

            double GrossProfit = SalesText - Cost_of_salesText;
            double NetProfit = GrossProfit + other_incomeText - other_expensesText;

            insertPOLStock(POLCollection, POL_ID, SalesText, Cost_of_salesText, other_incomeText, other_expensesText, GrossProfit, NetProfit);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPOLStock(MongoCollection<Document> POLCollection, String POL_ID, double SalesText, double Cost_of_salesText, double other_incomeText, double other_expensesText, double GrossProfit, double NetProfit) {
        Document generator = new Document("_id", new ObjectId()).append("entry ID", POL_ID)
                .append("Sales", SalesText)
                .append("Cost_of_sales", Cost_of_salesText)
                .append("other_income", other_incomeText)
                .append("other_expenses", other_expensesText)
                .append("GrossProfit", GrossProfit)
                .append("NetProfit", NetProfit);
        POLCollection.insertOne(generator);
        System.out.println("Connection S3");


    }

}
