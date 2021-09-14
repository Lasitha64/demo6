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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class StockLubricant {

    @FXML
    private TextField search_fld;

    @FXML
    private Button buttn_search;

    @FXML
    private TableColumn<?, ?> item_id;

    @FXML
    private TableColumn<?, ?> item_name;

    @FXML
    private TableColumn<?, ?> Item_qunt;

    @FXML
    private TextField idfld;

    @FXML
    private TextField namefld;

    @FXML
    private TextField quantityfld;

    @FXML
    private TextField pricefld;

    @FXML
    private DatePicker datefld;

    @FXML
    private TextArea Descipfld;

    @FXML
    private Button buttn_add;

    @FXML
    private Button buttn_dlt;

    @FXML
    private Button buutn_back;

    private AlertBox ab;
    private MongoClient database;

    MongoCollection<Document> LubricantCollection;

    @FXML

    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LubricantCollection = database.getCollection("Lubricant");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stcock-mngt.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-mngt.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Delete(ActionEvent event) {
        String idfldText = idfld.getText();
        Bson filter = eq("Item_ID", idfldText);
        DeleteResult result = LubricantCollection.deleteOne(filter);
        System.out.println(result);
        ab.display("Success","Data Deleted Successfully!");
    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

        if(idfld.getText().isEmpty() || namefld.getText().isEmpty() || quantityfld.getText().isEmpty() || pricefld.getText().isEmpty() || datefld.getValue().toString().isEmpty() || Descipfld.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!quantityfld.getText().matches("[0-9]+")){
            ab.display("Error","Quantity needs to be a number");
        }
        else if(!pricefld.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Price needs to be a double (ex: 1000.90)");
        }
        else {
            // update one document
            String idfldText = idfld.getText(), namefldText = namefld.getText(), quantityfldText = quantityfld.getText(), pricefldText = pricefld.getText(), datefldText = datefld.getValue().toString(), DescipfldText = Descipfld.getText();

            System.out.println(idfldText + namefldText + quantityfldText + pricefldText + datefldText + DescipfldText);

            Bson filter = eq("Item_ID", idfldText);


            Bson updateName = set("Item_Name", namefldText); // creating an array with a comment.
            Bson updateQuantity = set("Quantity", quantityfldText); // using addToSet so no effect.
            Bson updatePrice = set("Price", pricefldText); // using addToSet so no effect.
            Bson updateDate = set("Date", datefldText);
            Bson updateDescription = set("Description", DescipfldText);

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateName);
            updatePredicates.add(updateQuantity);
            updatePredicates.add(updatePrice);
            updatePredicates.add(updateDate);
            updatePredicates.add(updateDescription);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = LubricantCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the stock");
            System.out.println(newVersion);
            ab.display("Success","Data Updated Successfully!");
        }
    }

}
