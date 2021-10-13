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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

public class AddHammerServDetails {
    private Stage stage;
    private Scene scene;
    private AlertBox ab;


    @FXML
    private TextField Hid;

    @FXML
    private TextField Hdes;

    @FXML
    private TextField Hprice;

    @FXML
    private TextField Hdate;

    @FXML
    private Button hammerS;


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
        HammerCollection = database.getCollection("Hammer Service Details");
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


        if(Hid.getText().isEmpty() || Hdes.getText().isEmpty() || Hdate.getText().isEmpty() || Hprice.getText().isEmpty() ){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!Hdate.getText().matches("[0-9]+")){
            ab.display("Error","Date needs to be a number");
        }
        else if(!Hprice.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Price needs to be a double (ex: 200.90)");
        }
        else {

            try {


                String  HidText =  Hid.getText(), HdesText = Hdes.getText(), HdateText = Hdate.getText(), HpriceText = Hprice.getText();
                insertHammer(HammerCollection, HidText, HdesText, HdateText, HpriceText);
                ab.display("Error"," Data entry sucessful ");


            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    }

    private void insertHammer(MongoCollection<Document> hammerCollection, String HidText, String HdesText, String HdateText, String HpriceText){

        Document hammer = new Document("_id", new ObjectId())
                .append("Hammer id", HidText)
                .append("spare part name", HdesText)
                .append("quantity", HdateText)
                .append("price", HpriceText);



        hammerCollection.insertOne(hammer);
        System.out.println("Connection S3");
    }



}












