//page d

/*
@author..Lasitha
@edited..Kisaja
 */
package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class GeneMaintenanceItems {

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_quan;

    @FXML
    private TextField tf_price;

    @FXML
    private Button enter_parts;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_back;

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> cid;

    @FXML
    private TableColumn<?, ?> cname;

    @FXML
    private TableColumn<?, ?> cquan;

    @FXML
    private TableColumn<?, ?> cprice;


    MongoCollection<Document> generatorsCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        generatorsCollection = database.getCollection("GeneratorStock");
    }


    MongoDatabase database;

    @FXML
    void Back_to_c(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneMaintainance.fxml"));
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

    @FXML
    void Delete(ActionEvent event) {
        String Stock_ID = tf_id.getText();
        Bson filter = eq("Stock_ID", Stock_ID);
        DeleteResult result = generatorsCollection.deleteOne(filter);
        System.out.println(result);


    }

    @FXML
    void Update(ActionEvent event) {
        // update one document
        String Stock_ID = tf_id.getText(),
                 NameText = tf_name.getText() ,
                 QuantityText = tf_quan.getText() ,
                 PriseText = tf_price.getText();

        System.out.println(Stock_ID + NameText + QuantityText + PriseText);

        Bson filter = eq("Stock_ID", Stock_ID);


        Bson updateName = set("Name", NameText); // creating an array with a comment.
        Bson updateQuantity = set("Quantity", QuantityText); // using addToSet so no effect.
        Bson updatePrise = set("Prise", PriseText); // using addToSet so no effect.

        List<Bson> updatePredicates = new ArrayList<Bson>();
        updatePredicates.add(updateName);
        updatePredicates.add(updateQuantity);
        updatePredicates.add(updatePrise);


        //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
        FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Document newVersion = generatorsCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

        System.out.println("Updating the generator maintenence stock");
        System.out.println(newVersion);


    }

    @FXML
    void enter_to_the_DB(ActionEvent event) {
        try {
            //Get the values from the UI
            //Enter the id
            String Stock_IDText = tf_id.getText() ,
                    NameText = tf_name.getText() ,
                    QuantityText = tf_quan.getText() ,
                    PriseText = tf_price.getText();
            insertGeneratorStock(generatorsCollection, Stock_IDText, NameText, QuantityText, PriseText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertGeneratorStock(MongoCollection<Document> generatorsCollection, String Stock_IDText, String NameText, String QuantityText, String PriseText) {
        Document generator = new Document("_id", new ObjectId()).append("Stock_ID", Stock_IDText)
                .append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);
        generatorsCollection.insertOne(generator);
        System.out.println("Connection S3");
    }

}
