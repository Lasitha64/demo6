package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddNewStock implements Initializable{

    @FXML
    private ChoiceBox <String> choiceBox;

    @FXML
    private TextField idfld;

    @FXML
    private TextField namefld;

    @FXML
    private TextField quantityfld;

    @FXML
    private TextField pricefld;

    @FXML
    private DatePicker datefld;

    @FXML
    private TextArea Descipfld;

    @FXML
    private Button buttn_back;

    @FXML
    private Button buttn_add;

    private AlertBox ab;
    private MongoClient database;

    MongoCollection<Document> LubricantCollection;
    MongoCollection<Document> ItemCollection;
    MongoCollection<Document> LubricantAddReportCollection;
    MongoCollection<Document> ItemAddReportCollection;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LubricantCollection = database.getCollection("Lubricant");
        ItemCollection = database.getCollection("Item");
        LubricantAddReportCollection = database.getCollection("LubricantAddReport");
        ItemAddReportCollection = database.getCollection("ItemAddReport");

        choiceBox.getItems().add("LUBRICANTS");
        choiceBox.getItems().add("OTHER ITEMS");
    }

    @FXML
    void Add(ActionEvent event) throws IOException {

        if (choiceBox.getValue() == null){
            ab.display("Error","Select the type of stock");
        }
        else if(idfld.getText().isEmpty() || namefld.getText().isEmpty() || quantityfld.getText().isEmpty() || pricefld.getText().isEmpty() || datefld.getValue().toString().isEmpty() || Descipfld.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!quantityfld.getText().matches("[0-9]+")){
            ab.display("Error","Quantity needs to be a number");
        }
        else if(!pricefld.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Price needs to be a double (ex: 1000.90)");
        }
        else {
            try {

                    String choicetxt = choiceBox.getValue(), idfldText = idfld.getText(), namefldText = namefld.getText(), quantityfldText = quantityfld.getText(), pricefldText = pricefld.getText(), datefldText = datefld.getValue().toString(), DescipfldText = Descipfld.getText();
                    if (Objects.equals(choiceBox.getValue(), "LUBRICANTS")) {
                        insertLubricant(LubricantCollection,choicetxt, idfldText, namefldText, quantityfldText, pricefldText, datefldText, DescipfldText);
                        insertLubricantAddReport(LubricantAddReportCollection,choicetxt, idfldText, namefldText, quantityfldText, pricefldText, datefldText, DescipfldText);
                    }


                    String choicetxt1 = choiceBox.getValue(), idfldText1 = idfld.getText(), namefldText1 = namefld.getText(), quantityfldText1 = quantityfld.getText(), pricefldText1 = pricefld.getText(), datefldText1 = datefld.getValue().toString(), DescipfldText1 = Descipfld.getText();
                    if (Objects.equals(choiceBox.getValue(), "OTHER ITEMS")){
                        insertItem(ItemCollection, choicetxt1, idfldText1, namefldText1, quantityfldText1, pricefldText1, datefldText1, DescipfldText1);
                        insertItemAddReport(ItemAddReportCollection,choicetxt1, idfldText1, namefldText1, quantityfldText1, pricefldText1, datefldText1, DescipfldText1);
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void insertLubricant(MongoCollection<Document> lubricantCollection, String choicetxt, String idfldText, String namefldText, String quantityfldText, String pricefldText, String datefldText, String DescipfldText) {

        Document item = new Document("_id", new ObjectId())
                .append("Item_ID", idfldText)
                .append("Item_Name", namefldText)
                .append("Quantity", quantityfldText)
                .append("Price", pricefldText)
                .append("Date", datefldText)
                .append("Description", DescipfldText);
        lubricantCollection.insertOne(item);
        System.out.println("Connection S3");
        ab.display("Success","Data Inserted Successfully!");
    }

    private void insertItem(MongoCollection<Document> itemCollection, String choicetxt1, String idfldText1, String namefldText1, String quantityfldText1, String pricefldText1, String datefldText1, String DescipfldText1) {

        Document item = new Document("_id", new ObjectId())
                .append("Item_ID", idfldText1)
                .append("Item_Name", namefldText1)
                .append("Quantity", quantityfldText1)
                .append("Price", pricefldText1)
                .append("Date", datefldText1)
                .append("Description", DescipfldText1);
        itemCollection.insertOne(item);
        System.out.println("Connection S3");
        ab.display("Success","Data Inserted Successfully!");
    }

    private void insertLubricantAddReport(MongoCollection<Document> lubricantaddreportCollection, String choicetxt, String idfldText, String namefldText, String quantityfldText, String pricefldText, String datefldText, String DescipfldText) {

        Document item = new Document("_id", new ObjectId())
                .append("Item_ID", idfldText)
                .append("Item_Name", namefldText)
                .append("Quantity", quantityfldText)
                .append("Price", pricefldText)
                .append("Date", datefldText)
                .append("Description", DescipfldText);
        lubricantaddreportCollection.insertOne(item);
        System.out.println("Connection S3");
    }

    private void insertItemAddReport(MongoCollection<Document> itemaddreportCollection, String choicetxt1, String idfldText1, String namefldText1, String quantityfldText1, String pricefldText1, String datefldText1, String DescipfldText1) {

        Document item = new Document("_id", new ObjectId())
                .append("Item_ID", idfldText1)
                .append("Item_Name", namefldText1)
                .append("Quantity", quantityfldText1)
                .append("Price", pricefldText1)
                .append("Date", datefldText1)
                .append("Description", DescipfldText1);
        itemaddreportCollection.insertOne(item);
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

