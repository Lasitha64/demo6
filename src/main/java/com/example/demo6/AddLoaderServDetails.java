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

public class AddLoaderServDetails {
    private Stage stage;
    private Scene scene;
    private alertbox ab;

    @FXML
    private TextField Lid;

    @FXML
    private TextField Ldescription;

    @FXML
    private TextField Lprice;

    @FXML
    private DatePicker Ldate;

    @FXML
    private Button add;

    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> LoaderServiceDetailsCollection;

    @FXML


    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LoaderServiceDetailsCollection = database.getCollection("LoaderServiceDetails");
    }


    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Loader-service.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void addD(ActionEvent event) {

        if (Lid.getText().isEmpty() || Ldescription.getText().isEmpty() || Lprice.getText().isEmpty() || Ldate.getValue().toString().isEmpty()){
            ab.display("Error", " Input Fields can't be empty");
        } else if (!Lprice.getText().matches("[0-9]+")) {
            ab.display("Error", "Price needs to be a number");
        } else {

            try {


                String LidText = Lid.getText(), LdescriptionText = Ldescription.getText(), epriceText = Lprice.getText(), LdateText = Ldate.getValue().toString();
                insertLoaderServiceDetails(LoaderServiceDetailsCollection, LidText, LdescriptionText, LdateText, epriceText);
                ab.display("Service Entry", "Service Data Entry Successfull");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void insertLoaderServiceDetails(MongoCollection<Document> loaderServiceDetailsCollection, String LidText, String LdescriptionText, String LdateText, String LpriceText) {

        Document loaderServiceDetails = new Document("_id", new ObjectId())
                .append("LoaderID", LidText)
                .append("Description", LdescriptionText)
                .append("Date", LdateText)
                .append("Price", LpriceText);


        loaderServiceDetailsCollection.insertOne(loaderServiceDetails);
        System.out.println("Connection S3");
    }
}

