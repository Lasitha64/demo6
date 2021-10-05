//page h

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
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class FinanceProfitOrLossAccountEntryCnfermation {

    @FXML
    private DatePicker date;

    @FXML
    private Label Sales;

    @FXML
    private Label CostOfSales;

    @FXML
    private Label GrossProfit;

    @FXML
    private Label otherincome;

    @FXML
    private Label otherExpenses;

    @FXML
    private Label NetProfit;

    @FXML
    private Button btn_back;

    @FXML
    private Button Add;

    @FXML
    private Button POLview;


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
    void Add_to_db(ActionEvent event) {

    }

    @FXML
    void back_to_e(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountEntry.fxml"));
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
    void get_date(ActionEvent event) {

    }

    @FXML
    void move_to_i(ActionEvent event) {
        POLview.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountView.fxml"));
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

