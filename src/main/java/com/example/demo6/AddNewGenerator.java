//page g

package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class AddNewGenerator {

    @FXML
    public TextField gid;

    @FXML
    private TextField gbrand;

    @FXML
    public TextField gmanyear;

    @FXML
    public TextField gcountry;

    @FXML
    public TextField gwarranty;


    MongoCollection<Document> generatorsCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        generatorsCollection = database.getCollection("Generators");
    }


    MongoDatabase database;
    public void Back(ActionEvent actionEvent) {
    }

    public void AddNewGenerator(ActionEvent actionEvent) {
              try {
                  //Get the values from the UI
                  //Enter the id
                  String gidText = gid.getText() , gbrandText = gbrand.getText() ,
                          gmanyearText = gmanyear.getText() ,
                          gcountryText = gcountry.getText() ,
                          gwarrantyText = gwarranty.getText();;
                  insertGenerators(generatorsCollection, gidText, gbrandText, gmanyearText, gcountryText, gwarrantyText);

                  //Enter the brand
//                  String gbrandText = gbrand.getText();
//                  insertGenerators(generatorsCollection, gbrandText);
//
//                  //Enter the manyear
//                  String gmanyearText = gmanyear.getText();
//                  insertGenerators(generatorsCollection, gmanyearText);
//
//                  //Enter the country
//                  String gcountryText = gcountry.getText();
//                  insertGenerators(generatorsCollection, gcountryText);
//
//                  //Enter the warranty
//                  String gwarrantyText = gwarranty.getText();
//                  insertGenerators(generatorsCollection, gwarrantyText);

            } catch (Exception e) {
                  e.printStackTrace();
              }

    }

    public void insertGenerators(MongoCollection<Document> generatorsCollection, String gidText, String gbrandText, String gmanyearText, String gcountryText, String gwarrantyText) {
        Document generator = new Document("_id", new ObjectId()).append("generator_id", gidText)
                .append("brand", gbrandText)
                .append("manyear", gmanyearText)
                .append("country", gcountryText)
                .append("warranty", gwarrantyText);
        generatorsCollection.insertOne(generator);
        System.out.println("Connection S3");
    }

}
