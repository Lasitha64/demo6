package com.example.demo6;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class ViewHammerServDetails {

    private Stage stage;
    private Scene scene;
    private AlertBox ab;
    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> c_id;

    @FXML
    private TableColumn<?, ?> c_des;

    @FXML
    private TableColumn<?, ?> c_date;

    @FXML
    private TableColumn<?, ?> c_price;


    @FXML
    private TextField h_id;

    @FXML
    private TextField h_des;

    @FXML
    private TextField h_pri;

    @FXML
    private TextField h_date;


    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;


    MongoCollection<Document> HammerCollection;
    private String DateText;

    @FXML
    public void initialize() {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        HammerCollection = database.getCollection("Hammer Service Details");
    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("hammer-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Update(ActionEvent event) {

        if (h_id.getText().isEmpty() || h_date.getText().isEmpty() || h_date.getText().isEmpty() || h_pri.getText().isEmpty() ) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!h_date.getText().matches("[0-9]+")) {
            ab.display("Error", "Date needs to be a number");
        } else if (!h_pri.getText().matches("[0-9]+(\\.){0,1}[0-9]*")) {
            ab.display("Error", "Price needs to be a double (ex: 200.90)");
        } else {

            // update one document
            String Stock_ID = h_id.getText(),
                    NameText = h_des.getText(),
                    DateText = h_date.getText(),
                    PriseText = h_pri.getText();


            Bson filter = eq("Hammer id", Stock_ID);


            Bson updateId = set("Hammer id", Stock_ID); // creating an array with a comment.
            Bson updateName = set("spare part name", NameText); // using addToSet so no effect.
            Bson updateDate = set("date", DateText); // using addToSet so no effect.
            Bson updatePrice = set("price", PriseText);


            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateId);
            updatePredicates.add(updateName);
            updatePredicates.add(updateDate);
            updatePredicates.add(updatePrice);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = HammerCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            ab.display("Error", "Data entry successful");

            System.out.println("Updating the hammer maintenence stock");
            System.out.println(newVersion);
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        String Stock_ID = h_id.getText();
        Bson filter = eq("Hammer id", Stock_ID);
        DeleteResult result = HammerCollection.deleteOne(filter);

        ab.display("Error", "Data deleted successful");
        System.out.println(result);



    }
}

