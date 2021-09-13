//page g

package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
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

    @FXML
    private Button btn_back;

    @FXML
    private Button addbtn;



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

    //back to a
    public void Back(ActionEvent actionEvent) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneratorMainPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/main.css");
        scene.getStylesheets().add("stylesheet/login.css");
        scene.getStylesheets().add("stylesheet/GeneratorA.css");
        scene.getStylesheets().add("stylesheet/GeneratorB.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
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
