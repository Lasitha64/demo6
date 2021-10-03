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


public class ViewLoaderDetails {

    private Stage stage;
    private Scene scene;
    private AlertBox ab;

    MongoCollection<Document> LoaderCollection;

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> tbillNo;

    @FXML
    private TableColumn<?, ?> tdescription;

    @FXML
    private TableColumn<?, ?> tamount;

    @FXML
    private TableColumn<?, ?> tdate;

    @FXML
    private TextField billNo;

    @FXML
    private TextField description;

    @FXML
    private TextField amount;

    @FXML
    private DatePicker date;

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
        LoaderCollection = database.getCollection("Loader");
    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Loader-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();}
    @FXML
    void Update(ActionEvent event){

        if(billNo.getText().isEmpty() || description.getText().isEmpty() || amount.getText().isEmpty() || date.getValue().toString().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!billNo.getText().matches("[0-9]+")){
            ab.display("Error","RegNo needs to be a number");
        }
        else {

            String Stock_ID = billNo.getText(),
                    BrandText = description.getText(),
                    RegText = amount.getText(),
                    CondiText = date.getValue().toString();


            //   System.out.println(Stock_ID + NameText + QuantityText + PriseText);

            Bson filter = eq("BillNo", Stock_ID);


            Bson updateName = set("Description", BrandText); // creating an array with a comment.
            Bson updateQuantity = set("Amount", RegText); // using addToSet so no effect.
            Bson updatePrise = set("Date", CondiText);


            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateName);
            updatePredicates.add(updateQuantity);
            updatePredicates.add(updatePrise);



            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = LoaderCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the generator maintenence stock");
            System.out.println(newVersion);

        }
    }
    @FXML
    void Delete(ActionEvent event){

        String Stock_ID = billNo.getText();
        Bson filter = eq("BillNo", Stock_ID);
        DeleteResult result = LoaderCollection.deleteOne(filter);
        System.out.println(result);

    }

    }
