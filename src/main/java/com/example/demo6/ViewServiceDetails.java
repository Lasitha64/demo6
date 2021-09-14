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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ViewServiceDetails {

    private AlertBox ab;

    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> cid;

    @FXML
    private TableColumn<?, ?> cname;

    @FXML
    private TableColumn<?, ?> cquan;

    @FXML
    private TableColumn<?, ?> cprice;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private TextField vid;

    @FXML
    private TextField des;

    @FXML
    private DatePicker dt;

    @FXML
    private TextField p;
    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> vServiceCollection;

    @FXML

    public void initialize() {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        vServiceCollection = database.getCollection("vService");
    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ServiceDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void Delete(ActionEvent event) {

        String vidText = vid.getText();
        Bson filter = eq("Vehicle_id",vidText);
        DeleteResult result = vServiceCollection.deleteOne(filter);
        System.out.println(result);

        ab.display("Done"," Data Deleted Successfully");

    }

    @FXML
    void Update(ActionEvent event) {

        if (vid.getText().isEmpty() || des.getText().isEmpty() || p.getText().isEmpty() || dt.getValue().toString().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");

        } else if (!p.getText().matches("[0-9]+")) {
            ab.display("Error", "Price needs to be a number");
        } else {

            // update one document
            String vidText = vid.getText(), desText = des.getText(), pText = p.getText(), dtText = dt.getValue().toString();


            System.out.println(vidText + desText + pText + dtText);

            Bson filter = eq("Vehicle_id", vidText);


            Bson updatedes = set("Description", desText); // creating an array with a comment.
            Bson updatep = set("Price", pText); // using addToSet so no effect.
            Bson updatedate = set("Date", dtText); // using addToSet so no effect.

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updatedes);
            updatePredicates.add(updatep);
            updatePredicates.add(updatedate);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = vServiceCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the Vehicle repair Details");
            System.out.println(newVersion);

            ab.display("Done"," Data Updated Successfully");


        }
    }

}

