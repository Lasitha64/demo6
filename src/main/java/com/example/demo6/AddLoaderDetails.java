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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

public class AddLoaderDetails {
    private Stage stage;
    private Scene scene;
    private alertbox ab;
    @FXML
    private TextField loaderid;

    @FXML
    private TextField brand;

    @FXML
    private TextField workingsite;

    @FXML
    private Button addbutton;

    @FXML
    private TextField condition;

    @FXML
    private TextField regno;

    @FXML
    private Button btn_back;
    private MongoClient database;

    MongoCollection<Document> LoaderCollection;
    @FXML

    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LoaderCollection = database.getCollection("Loader");
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoaderDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void adddetails(ActionEvent event) {


        if(loaderid.getText().isEmpty() || brand.getText().isEmpty() || workingsite.getText().isEmpty() || condition.getText().isEmpty() || regno.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!regno.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","RegNo must be a Number");
        }
        else {


        }
        try {


            String loaderidText = loaderid.getText(), brandText = brand.getText(), workingsiteText = workingsite.getText(), conditionText = condition.getText(), regnoText = regno.getText();
            insertLoader(LoaderCollection, loaderidText, brandText, workingsiteText, conditionText, regnoText);
            ab.display("Data Entry"," Data Entry Sucessfull");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void insertLoader(MongoCollection<Document> LoaderCollection, String loaderidText, String brandText, String workingsiteText, String conditionText, String regnoText) {

        Document loader = new Document("_id", new ObjectId())
                .append("LoaderID", loaderidText)
                .append("Brand", brandText)
                .append("WorkingSite", workingsiteText)
                .append("Condition", conditionText)
                .append("Register No", regnoText);
        LoaderCollection.insertOne(loader);
        System.out.println("Connection S3");
    }



}
