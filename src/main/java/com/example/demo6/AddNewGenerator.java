//page g
/*
@author..kisaja
 */

package com.example.demo6;

import com.mongodb.client.MongoClient;
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
import static java.util.Arrays.asList;

public class AddNewGenerator {

    private AlertBox ab;

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

    @FXML
    private Button deletebn;

    @FXML
    private Button updatebn;



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

        if(gid.getText().isEmpty() || gbrand.getText().isEmpty() || gmanyear.getText().isEmpty() || gcountry.getText().isEmpty() || gwarranty.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!gmanyear.getText().matches("[0-9]+")){
            ab.display("Error","Generator manufacture year needs to be a number");
        }
        else {


            try {
                //Get the values from the UI
                //Enter the id
                String gidText = gid.getText(), gbrandText = gbrand.getText(),
                        gmanyearText = gmanyear.getText(),
                        gcountryText = gcountry.getText(),
                        gwarrantyText = gwarranty.getText();
                ;
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

                ab.display("Success","Success Data entry");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void deleteGenerator(ActionEvent event) {
        String gidText = gid.getText();
        Bson filter = eq("generator_id", gidText);
        DeleteResult result = generatorsCollection.deleteOne(filter);
        System.out.println(result);

        ab.display("Deleted","Success Data Deleted");

    }

    @FXML
    void updategen(ActionEvent event) {

        if(gid.getText().isEmpty()){
            ab.display("Error"," gid can't be empty");
        }
        else if(!gmanyear.getText().matches("[0-9]+")){
            ab.display("Error","Generator manufacture year needs to be a number");
        }
        else {

            // update one document
            String gidText = gid.getText(),
                    gbrandText = gbrand.getText(),
                    gmanyearText = gmanyear.getText(),
                    gcountryText = gcountry.getText(),
                    gwarrantyText = gcountry.getText();

            System.out.println(gid + gbrandText + gmanyearText + gcountryText + gwarrantyText);

            Bson filter = eq("generator_id", gidText);


            Bson updatebrand = set("brand", gbrandText); // creating an array with a comment.
            Bson updatemanyear = set("manyear", gmanyearText); // using addToSet so no effect.
            Bson updatecountry = set("country", gcountryText);
            Bson updatewarranty = set("warranty", gwarrantyText);// using addToSet so no effect.

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updatebrand);
            updatePredicates.add(updatemanyear);
            updatePredicates.add(updatecountry);
            updatePredicates.add(updatewarranty);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = generatorsCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the generator maintenence stock");
            System.out.println(newVersion);

            ab.display("Success","Success Data update");
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
