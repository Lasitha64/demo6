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
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StockView implements Initializable {

    private String itid;
    private String itname;
    private String itqunt;
    private String itprice;
    private String itdate;
    private String itdes;

    @FXML
    private TextField search_fld;

    @FXML
    private Button buttn_search;


    @FXML
    private TableView<Item> StockItem;

    @FXML
    private TableColumn<Item, String> item_id;

    @FXML
    private TableColumn<Item, String> item_name;

    @FXML
    private TableColumn<Item, String> item_qunt;

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

    MongoCollection<Document> ItemCollection;
    MongoCollection<Document> ItemAddReportCollection;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("item-main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/item-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Update(ActionEvent event) throws IOException {

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
            //insert data to ItemAddReport
            try {
                String idfldText1 = idfld.getText(), namefldText1 = namefld.getText(), quantityfldText1 = quantityfld.getText(), pricefldText1 = pricefld.getText(), datefldText1 = datefld.getValue().toString(), DescipfldText1 = Descipfld.getText();
                insertItemAddReport(ItemAddReportCollection, idfldText1, namefldText1, quantityfldText1, pricefldText1, datefldText1, DescipfldText1);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //calculate the Quantity
            Item item = StockItem.getSelectionModel().getSelectedItem();

            String Qunt =item.getQuantity();

            int Qunty=Integer.parseInt(item.getQuantity().toString());
            int qunt=Integer.parseInt(quantityfld.getText().toString());

            quantityfld.setText(Integer.toString(Qunty+qunt));

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
            Document newVersion = ItemCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            showItem();
            clearText();
            System.out.println("Updating the stock");
            System.out.println(newVersion);
            ab.display("Success","Data Updated Successfully!");
        }
    }

    public void showItem() {

        ObservableList<Item> list = getItemList();

        item_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        item_name.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
        item_qunt.setCellValueFactory(new PropertyValueFactory<Item, String>("Quantity"));

        StockItem.setItems(list);

    }

    private ObservableList<Item> getItemList() {
        ObservableList<Item> attend = FXCollections.observableArrayList();

        MongoCursor<Document> cursor = ItemCollection.find().iterator();
        try {

            for (int i = 0; i < ItemCollection.count(); i++) {


                Document doc = cursor.next();
                itid = doc.getString("Item_ID");
                //  System.out.println(itid);
                itname = doc.getString("Item_Name");
                itqunt = doc.getString("Quantity");

                attend.add(new Item(itid, itname, itqunt, itprice, itdate, itdes));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;
//      call the setTable method
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        ItemCollection = database.getCollection("Item");
        ItemAddReportCollection = database.getCollection("ItemAddReport");

        showItem();
        searchitem();
    }

    @FXML
    void searchitem() {

        ObservableList<Item>  searchlist = getItemList();

        item_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        item_name.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
        item_qunt.setCellValueFactory(new PropertyValueFactory<Item, String>("Quantity"));

        StockItem.setItems(searchlist);

        FilteredList<Item> filterdata = new FilteredList<>(searchlist, b->true);
        search_fld.textProperty().addListener((observable, oldValue, newValue)->{
            filterdata.setPredicate(item -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(item.getId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if(item.getName().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }else if(item.getQuantity().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                    return false;
                }
            });
        });
        SortedList<Item> sortedData = new SortedList<>(filterdata);
        sortedData.comparatorProperty().bind(StockItem.comparatorProperty());
        StockItem.setItems(sortedData);

    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        Item item = StockItem.getSelectionModel().getSelectedItem();
        idfld.setText(item.getId());
        namefld.setText(item.getName());
    }

    private void clearText(){
        idfld.clear();
        namefld.clear();
        quantityfld.clear();
        pricefld.clear();
        datefld.getEditor().clear();
        Descipfld.clear();
    }

    private void insertItemAddReport(MongoCollection<Document> itemaddreportCollection, String idfldText1, String namefldText1, String quantityfldText1, String pricefldText1, String datefldText1, String DescipfldText1) {

        Document item = new Document("_id", new ObjectId())
                .append("Item_ID", idfldText1)
                .append("Item_Name", namefldText1)
                .append("Quantity", quantityfldText1)
                .append("Price", pricefldText1)
                .append("Date", datefldText1)
                .append("Description", DescipfldText1);
        itemaddreportCollection.insertOne(item);
        System.out.println("Connection S3");
    }

}

