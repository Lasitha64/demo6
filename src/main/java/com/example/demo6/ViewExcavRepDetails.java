package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
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
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class ViewExcavRepDetails {

    private Stage stage;
    private Scene scene;
    private AlertBox ab;

    @FXML
    private TextField eid;

    @FXML
    private TextField edescription;

    @FXML
    private TextField eprice;

    @FXML
    private DatePicker edate;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;
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

        Parent root = FXMLLoader.load(getClass().getResource("Excav-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Update(ActionEvent event) {
        if (eid.getText().isEmpty() || edescription.getText().isEmpty() || edate.getValue().toString().isEmpty() || eprice.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!eprice.getText().matches("[0-9]+(\\.){0,1}[0-9]*")) {
            ab.display("Error", "Price needs to be a double (ex: 200.90)");
        } else {
            // update one document
            String eidText = eid.getText(), edescriptionText = edescription.getText(), epriceText = eprice.getText(), edateText = edate.getValue().toString();


            System.out.println(eidText + edescriptionText + epriceText + edateText);

            Bson filter = eq("ExcavatorID", eidText);


            Bson updateDescription = set("Description", edescriptionText); // creating an array with a comment.
            Bson updateDate = set("Date", edateText); // using addToSet so no effect.
            Bson updatePrice = set("Price", epriceText); // using addToSet so no effect.


            List<Bson> updatePredicates = new ArrayList<>();
            updatePredicates.add(updateDescription);
            updatePredicates.add(updateDate);
            updatePredicates.add(updatePrice);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = ExcavatorRepairCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating Excavator Repair Details");
            System.out.println(newVersion);
            ab.display("Data Update ","Data Update Successfull");
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        String eidText = eid.getText();
        Bson filter = eq("ExcavatorID", eidText);
        DeleteResult result = ExcavatorRepairCollection.deleteOne(filter);
        System.out.println(result);
        ab.display("Data Delete ","Data Delete Successfull");
    }
    
}
