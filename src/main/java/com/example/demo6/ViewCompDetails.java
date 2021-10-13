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

public class ViewCompDetails {


    private Stage stage;
    private Scene scene;
    private AlertBox ab;

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> chid;

    @FXML
    private TableColumn<?, ?> csname;

    @FXML
    private TableColumn<?, ?> cquan;

    @FXML
    private TableColumn<?, ?> cprice;

    @FXML
    private TableColumn<?, ?> cdate;

    @FXML
    private TextField comid;

    @FXML
    private TextField combrand;

    @FXML
    private TextField com_quan;

    @FXML
    private TextField comv_price;

    @FXML
    private TextField comv_date;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;




    MongoCollection<Document> CompressorCollection;
    private String DateText;


    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        CompressorCollection = database.getCollection("Compressor");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("compressorDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Delete(ActionEvent event) {

        String Stock_ID = comid.getText();
        Bson filter = eq("Compressor id", Stock_ID);
        DeleteResult result = CompressorCollection.deleteOne(filter);
        ab.display("Error", "Data deleted successful");
        System.out.println(result);

    }

    @FXML
    void Update(ActionEvent event) {

        if (comid.getText().isEmpty() || combrand.getText().isEmpty() || com_quan.getText().isEmpty() || comv_price.getText().isEmpty() || comv_date.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!com_quan.getText().matches("[0-9]+")) {
            ab.display("Error", "Quantity needs to be a number");
        } else if (!comv_price.getText().matches("[0-9]+(\\.){0,1}[0-9]*")) {
            ab.display("Error", "Price needs to be a double (ex: 200.90)");
        } else {


            // update one document
            String Stock_ID = comid.getText(),
                    NameText = combrand.getText(),
                    QuantityText = com_quan.getText(),
                    PriseText = comv_price.getText();
            DateText = comv_date.getText();

            System.out.println(Stock_ID + NameText + QuantityText + PriseText);

            Bson filter = eq("Compressor id", Stock_ID);


            Bson updateId = set("Compressor id", NameText); // creating an array with a comment.
            Bson updateName = set("spare part name", QuantityText); // using addToSet so no effect.
            Bson updateQuantity = set("quantity", PriseText); // using addToSet so no effect.
            Bson updatePrice = set("price", PriseText);
            Bson updatedate = set("quantity", PriseText);

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateId);
            updatePredicates.add(updateName);
            updatePredicates.add(updateQuantity);
            updatePredicates.add(updatePrice);
            updatePredicates.add(updatedate);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = CompressorCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            ab.display("Error", "Data entry successful");

            System.out.println("Updating the Air Compressor maintenence stock");
            System.out.println(newVersion);

        }

    }
}
