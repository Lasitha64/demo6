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

public class AddVehicleDetails {

    

    @FXML
    private TextField vt;

    @FXML
    private TextField st;

    @FXML
    private TextField c;

    @FXML
    private TextField rpn;



    @FXML
    private Button addD;

    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> VehicleCollection;

    @FXML

    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        VehicleCollection = database.getCollection("Vehicle");
    }

    @FXML

    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("VehicleDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();



    }



    @FXML
    void addDt(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("VehicleDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        try {


            String vtText = vt.getText(), stText = st.getText(), cText = c.getText(), rpnText = rpn.getText();
            insertVehicle(VehicleCollection, vtText, stText, cText, rpnText);
            System.out.println("Connection S2");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connection a1");


    }

    private void insertVehicle(MongoCollection<Document> vehicleCollection, String vtText, String stText, String cText, String rpnText) {

        Document vehicle = new Document("_id", new ObjectId())
                .append("Vehicle_Type", vtText)
                .append("Condition", cText)
                .append("Site", stText)
                .append("Reg_No", rpnText);

        vehicleCollection.insertOne(vehicle);
        System.out.println("Connection S3");
    }





}
