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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ViewExcavDetails {

    private Stage stage;
    private Scene scene;
    @FXML
    private TextField eid;

    @FXML
    private TextField ebrand;

    @FXML
    private TextField ereg;

    @FXML
    private TextField econ;

    @FXML
    private TextField esite;


    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

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

        Parent root = FXMLLoader.load(getClass().getResource("Excav-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();}
    @FXML
    void Update(ActionEvent event){
        // update one document
        String eidText = eid.getText(), ebrandText = ebrand.getText(), esiteText = esite.getText(), econText = econ.getText(), eregText = ereg.getText();


        System.out.println(eidText + ebrandText + esiteText + econText + eregText);

        Bson filter = eq("ExcavatorID", eidText);


        Bson updateBrand = set("Brand", ebrandText); // creating an array with a comment.
        Bson updateReg = set("Registration No", eregText); // using addToSet so no effect.
        Bson updateCon = set("Condition", econText); // using addToSet so no effect.
        Bson updateSite = set("Site", esiteText); // using addToSet so no effect.




        List<Bson> updatePredicates = new ArrayList<Bson>();
        updatePredicates.add(updateBrand);
        updatePredicates.add(updateReg);
        updatePredicates.add(updateCon);
        updatePredicates.add(updateSite);


        //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
        FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Document newVersion = ExcavatorCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

        System.out.println("Updating the Excavator Details");
        System.out.println(newVersion);

    }
    @FXML
    void Delete(ActionEvent event){
        String eidText = eid.getText();
        Bson filter = eq("ExcavatorID", eidText);
        DeleteResult result = ExcavatorCollection.deleteOne(filter);
        System.out.println(result);
    }



    }
