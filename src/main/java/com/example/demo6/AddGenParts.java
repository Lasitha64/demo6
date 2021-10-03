//page f
/*
@author..kisaja
 */

package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;

public class AddGenParts {

    @FXML
    private TextField GPStock_ID;

    @FXML
    private TextField G_Stock_ID;

    @FXML
    private DatePicker Date_of_purchase;

    @FXML
    private TextField GSQuantity;

    @FXML
    private TextField UnitPrice;

    @FXML
    private Button Back;

    @FXML
    private Button Add;

    MongoCollection<Document> generatorsCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        generatorsCollection = database.getCollection("GeneratorMainStock");
    }

    @FXML
    void AddToDB(ActionEvent event) {
        try {
            //Get the values from the UI
            //Enter the id
            String GPStock_IDText = GPStock_ID.getText();
            String G_Stock_IDText = G_Stock_ID.getText();
            LocalDate Date_of_purchaseText = Date_of_purchase.getValue();
            String GSQuantityText = GSQuantity.getText();
            String UnitPriceText = UnitPrice.getText();
            insertGeneratorMaintenance(generatorsCollection, GPStock_IDText, G_Stock_IDText, Date_of_purchaseText, GSQuantityText, UnitPriceText);

            System.out.println(Date_of_purchaseText);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void insertGeneratorMaintenance(MongoCollection<Document> generatorsCollection, String GPStock_IDText, String G_Stock_IDText, LocalDate Date_of_purchaseText, String GSQuantityText, String UnitPriceText) {
        Document generator = new Document("_id", new ObjectId()).append("GPStock ID", GPStock_IDText)
                .append("G_Stock_IDText", G_Stock_IDText)
                .append("Date_of_purchaseText", Date_of_purchaseText)
                .append("Type", GSQuantityText)
                .append("Type", UnitPriceText);
        generatorsCollection.insertOne(generator);
        System.out.println("Connection S3");
    }

    @FXML
    void back_to_e(ActionEvent event) {
        Back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneratorParts.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Gene.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}
