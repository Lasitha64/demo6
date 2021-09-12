package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

public class AddNewLub {

    @FXML
    private TextField namefld;

    @FXML
    private TextField quantityfld;

    @FXML
    private TextField pricefld;

    @FXML
    private TextField datefld;

    @FXML
    private TextArea Descipfld;

    @FXML
    private Button buttn_back;

    @FXML
    private Button buttn_add;

    private MongoClient database;

    MongoCollection<Document> LubricantCollection;

    @FXML

    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LubricantCollection = database.getCollection("Lubricant");
    }

    @FXML
    void Add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lubricant-main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/lubricant-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        try {


            String namefldText = namefld.getText(), quantityfldText = quantityfld.getText(), pricefldText = pricefld.getText(), datefldText = datefld.getText(), DescipfldText = Descipfld.getText();
            insertLubricant(LubricantCollection, namefldText, quantityfldText, pricefldText, datefldText, DescipfldText);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void insertLubricant(MongoCollection<Document> lubricantCollection, String namefldText, String quantityfldText, String pricefldText, String datefldText, String DescipfldText) {

        Document item = new Document("_id", new ObjectId())
                .append("Item_Name", namefldText)
                .append("Quantity", quantityfldText)
                .append("Price", pricefldText)
                .append("Date", datefldText)
                .append("Description", DescipfldText);
        lubricantCollection.insertOne(item);
        System.out.println("Connection S3");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lubricant-main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/lubricant-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}

