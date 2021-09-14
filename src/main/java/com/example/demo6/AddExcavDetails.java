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

public class AddExcavDetails {
    private Stage stage;
    private Scene scene;
    private AlertBox ab;

    @FXML
    private Button btn_back;
    @FXML
    private TextField eid;

    @FXML
    private TextField ebrand;

    @FXML
    private TextField esite;

    @FXML
    private Button addDetails;

    @FXML
    private TextField econ;

    @FXML
    private TextField ereg;

    private MongoClient database;

    MongoCollection<Document> ExcavatorCollection;



    @FXML


    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        ExcavatorCollection = database.getCollection("Excavator");
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ExcavDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void add(ActionEvent event) {
        if(eid.getText().isEmpty() || ebrand.getText().isEmpty() || esite.getText().isEmpty() || econ.getText().isEmpty() || ereg.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!ereg.getText().matches("[0-9]+")){
            ab.display("Error","Registration No. needs to be a number");
        }

        else {


            try {


                String eidText = eid.getText(), ebrandText = ebrand.getText(), esiteText = esite.getText(), econText = econ.getText(), eregText = ereg.getText();
                insertExcavator(ExcavatorCollection, eidText, ebrandText, esiteText, econText, eregText);
                ab.display("Data Entry ","Data Entry Successfull");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void insertExcavator(MongoCollection<Document> excavatorCollection, String eidText, String ebrandText, String esiteText, String econText, String eregText) {

        Document excavator = new Document("_id", new ObjectId())
                .append("ExcavatorID", eidText)
                .append("Brand", ebrandText)
                .append("Site", esiteText)
                .append("Condition", econText)
                .append("Registration No", eregText);

        excavatorCollection.insertOne(excavator);
        System.out.println("Connection S3");

    }


}
