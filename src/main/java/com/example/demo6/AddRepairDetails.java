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
import java.util.Date;

public class AddRepairDetails {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField vid;

    @FXML
    private TextField des;

    @FXML
    private TextField p;

    @FXML
    private DatePicker dt;

    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> vRepairCollection;

    @FXML

    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        vRepairCollection = database.getCollection("vRepair");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("RepairDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void ad(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("RepairDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        try {


            String vidText = vid.getText(), desText = des.getText(), pText = p.getText(),dtText=dt.getValue().toString();

            insertvRepair(vRepairCollection, vidText, desText, pText, dtText);
            System.out.println("Connection S2");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connection a1");


    }

    private void insertvRepair(MongoCollection<Document> vRepairCollection, String vidText, String desText, String pText, String dtText) {

        Document vehicle = new Document("_id", new ObjectId())
                .append("Vehicle_id", vidText)
                .append("Description", desText)
                .append("Date", dtText)
                .append("Price", pText);

        vRepairCollection.insertOne(vehicle);
        System.out.println("Connection S3");
    }





}





