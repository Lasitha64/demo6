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

public class AddExcavRepDetails {
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField eid;

    @FXML
    private TextField edescription;

    @FXML
    private TextField eprice;

    @FXML
    private DatePicker edate;

    @FXML
    private Button btn_back;

    @FXML
    private Button addDetails;

    private MongoClient database;

    MongoCollection<Document> ExcavatorRepairCollection;

    @FXML


    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        ExcavatorRepairCollection = database.getCollection("ExcavatorRepair");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Excav-repair.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void add(ActionEvent event) {
        try {


            String eidText = eid.getText(), edescriptionText = edescription.getText(),  epriceText = eprice.getText();
            Date edateDate = (Date)edate.getDayCellFactory();
            insertExcavatorRepair(ExcavatorRepairCollection, eidText, edescriptionText, edateDate, epriceText);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void insertExcavatorRepair(MongoCollection<Document> excavatorRepairCollection, String eidText, String edescriptionText, Date edateText, String epriceText) {

        Document excavatorRepair = new Document("_id", new ObjectId())
                .append("ExcavatorID", eidText)
                .append("Description", edescriptionText)
                .append("Date", edateText)
                .append("Price", epriceText);


        excavatorRepairCollection.insertOne(excavatorRepair);
        System.out.println("Connection S3");
    }
}
