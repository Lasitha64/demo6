package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class StockLubricant implements Initializable {

    private String lubid;
    private String lubname;
    private String lubqunt;
    private String lubprice;
    private String lubdate;
    private String lubdes;
    private AlertBox ab;
    private MongoClient database;
    MongoCollection<Document> LubricantCollection;

    @FXML
    private TextField search_fld;

    @FXML
    private TableView<Lubricant> StockLubricant;

    @FXML
    private TableColumn<Lubricant, String> item_id;

    @FXML
    private TableColumn<Lubricant, String> item_name;

    @FXML
    private TableColumn<Lubricant, String> Item_qunt;

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
    private Button buutn_back;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lubricant-main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/lubricant-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Add(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LubricantCollection = database.getCollection("Lubricant");

        showLubricant();
        searchLubricant();
    }

    public void showLubricant() {

        ObservableList<Lubricant> list = getLubricantList();

        item_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        item_name.setCellValueFactory(new PropertyValueFactory<Lubricant, String>("Name"));
        Item_qunt.setCellValueFactory(new PropertyValueFactory<Lubricant, String>("Quantity"));

        StockLubricant.setItems(list);

    }

    private ObservableList<Lubricant> getLubricantList() {
        ObservableList<Lubricant> attend = FXCollections.observableArrayList();

        MongoCursor<Document> cursor = LubricantCollection.find().iterator();
        try {

            for (int i = 0; i < LubricantCollection.count(); i++) {


                Document doc = cursor.next();
                lubid = doc.getString("Item_ID");
                //  System.out.println(lubid);
                lubname = doc.getString("Item_Name");
                lubqunt = doc.getString("Quantity");

                attend.add(new Lubricant(lubid, lubname, lubqunt, lubprice, lubdate, lubdes));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;
//      call the setTable method
    }

    void searchLubricant() {

        ObservableList<Lubricant>  searchlist = getLubricantList();

        item_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        item_name.setCellValueFactory(new PropertyValueFactory<Lubricant, String>("Name"));
        Item_qunt.setCellValueFactory(new PropertyValueFactory<Lubricant, String>("Quantity"));

        StockLubricant.setItems(searchlist);

        FilteredList<Lubricant> filterdata = new FilteredList<>(searchlist, b->true);
        search_fld.textProperty().addListener((observable, oldValue, newValue)->{
            filterdata.setPredicate(lubricant -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(lubricant.getId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if(lubricant.getName().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }else if(lubricant.getQuantity().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                    return false;
                }
            });
        });
        SortedList<Lubricant> sortedData = new SortedList<>(filterdata);
        sortedData.comparatorProperty().bind(StockLubricant.comparatorProperty());
        StockLubricant.setItems(sortedData);

    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        Lubricant lubricant = StockLubricant.getSelectionModel().getSelectedItem();
        idfld.setText(lubricant.getId());
        namefld.setText(lubricant.getName());
    }

}
