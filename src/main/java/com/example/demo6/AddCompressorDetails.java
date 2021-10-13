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

public class AddCompressorDetails {
    private Stage stage;
    private Scene scene;
    private AlertBox ab;



    @FXML
    private TextField com;

    @FXML
    private TextField comsp;

    @FXML
    private Button addbutton;

    @FXML
    private TextField compri;

    @FXML
    private TextField comqun;

    @FXML
    private TextField comdate;

    @FXML
    private Button btn_back;


    private MongoClient database;

    MongoCollection<Document> CompressorCollection;


    @FXML


    public void initialize(){
        //initialize database connection
        com.example.demo6.Database databaseController = new com.example.demo6.Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        CompressorCollection = database.getCollection("Compressor");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("compressorDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void adddetails(ActionEvent event) {

        if(com.getText().isEmpty() || comsp.getText().isEmpty() || compri.getText().isEmpty() || comqun.getText().isEmpty() || comdate.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!comqun.getText().matches("[0-9]+")){
            ab.display("Error","Quantity needs to be a number");
        }
        else if(!compri.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Price needs to be a double (ex: 200.90)");
        }
        else {

            try {


                String  comText =  com.getText(), comspText = comsp.getText(), compriText = compri.getText(), comqunText = comqun.getText(), comdateText = comdate.getText();
                insertCompressor(CompressorCollection,comText , comspText, compriText, comqunText, comdateText);
                ab.display("Error"," Data entry sucessful ");


            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    }



    private void insertCompressor(MongoCollection<Document> CompressorCollection, String comText, String comspText, String compriText, String comqunText, String comdateText){

        Document hammer = new Document("_id", new ObjectId())
                .append("Compressor id", comText)
                .append("spare part name", comspText)
                .append("quantity", compriText)
                .append("price", comqunText)
                .append("Date", comdateText);


        CompressorCollection.insertOne(hammer);
        System.out.println("Connection S3");
    }



}
