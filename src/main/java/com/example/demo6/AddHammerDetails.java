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

public class AddHammerDetails {
    private Stage stage;
    private Scene scene;
    private AlertBox ab;

    @FXML
    private TextField ham;

    @FXML
    private TextField ham2;


    @FXML
    private Button addbutton;

    @FXML
    private TextField hamcon;

    @FXML
    private TextField hamreg;

    @FXML
    private TextField hamdate;



    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> HammerCollection;


    @FXML


    public void initialize(){
        //initialize database connection
        com.example.demo6.Database databaseController = new com.example.demo6.Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        HammerCollection = database.getCollection("Hammer");
    }
    @FXML

    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hammerDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void hamsite(ActionEvent event) {

    }
    @FXML
    void adddetails(ActionEvent event) {

        if(ham.getText().isEmpty() || ham2.getText().isEmpty() || hamreg.getText().isEmpty() || hamcon.getText().isEmpty() || hamdate.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!hamreg.getText().matches("[0-9]+")){
            ab.display("Error","Quantity needs to be a number");
        }
        else if(!hamcon.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Price needs to be a double (ex: 200.90)");
        }
        else {

            try {


                String  hamText =  ham.getText(), ham2Text = ham2.getText(), hamregText = hamreg.getText(), hamconText = hamcon.getText(), hamdateText = hamdate.getText();
                insertHammer(HammerCollection, hamText, ham2Text, hamregText, hamconText, hamdateText);
                ab.display("Error"," Data entry sucessful ");


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    private void insertHammer(MongoCollection<Document> hammerCollection, String hamText, String ham2Text, String hamregText, String hamconText, String hamdateText){

        Document hammer = new Document("_id", new ObjectId())
                .append("Hammer id", hamText)
                .append("spare part name", ham2Text)
                .append("quantity", hamregText)
                .append("price", hamconText)
                .append("Date", hamdateText);


        hammerCollection.insertOne(hammer);
        System.out.println("Connection S3");
    }



}

