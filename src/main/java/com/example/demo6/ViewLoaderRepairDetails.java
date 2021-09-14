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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class ViewLoaderRepairDetails {

    private Stage stage;
    private Scene scene;

    MongoCollection<Document> LoaderCollection;

    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> L_id;

    @FXML
    private TableColumn<?, ?> L_description;

    @FXML
    private TableColumn<?, ?> L_date;

    @FXML
    private TableColumn<?, ?> L_price;

    @FXML
    private TextField Lid;

    @FXML
    private TextField Ldescription;

    @FXML
    private DatePicker Ldate;

    @FXML
    private TextField Lprice;


    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LoaderCollection = database.getCollection("LoaderRepairDetails");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Loader-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Update(ActionEvent event) {

        String Stock_ID = Lid.getText(),
                RegText = Ldescription.getText() ,
                CondiText = Ldate.getValue().toString(),
                SiteText = Lprice.getText();

        //   System.out.println(Stock_ID + NameText + QuantityText + PriseText);

        Bson filter = eq("LoaderID", Stock_ID);


        Bson updateQuantity = set("Description", RegText); // using addToSet so no effect.
        Bson updatePrise = set("Date", CondiText);
        Bson updateSite = set("Price",SiteText);// using addToSet so no effect.

        List<Bson> updatePredicates = new ArrayList<Bson>();
        updatePredicates.add(updateQuantity);
        updatePredicates.add(updatePrise);
        updatePredicates.add(updateSite);


        //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
        FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Document newVersion = LoaderCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

        System.out.println("Updating the generator maintenence stock");
        System.out.println(newVersion);


    }

    @FXML
    void Delete(ActionEvent event) {

        String Stock_ID = Lid.getText();
        Bson filter = eq("LoaderID", Stock_ID);
        DeleteResult result = LoaderCollection.deleteOne(filter);
        System.out.println(result);
    }
}
