package com.example.demo6;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemMain implements Initializable {

    private String itid;
    private String itname;
    private String itqunt;
    private MongoClient database;
    MongoCollection<Document> ItemCollection;

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
    private Button buttn_remove;

    @FXML
    private Button buttn_dlt;

    @FXML
    private Button buttn_add;

    @FXML
    private Button buutn_back;

    public ObservableList<Item> list;

    @FXML
    void Back(ActionEvent event) {

    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showItem();
    }

    public void showItem() {

        ObservableList<Item> list = getItemList();

        item_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        item_name.setCellValueFactory(new PropertyValueFactory<Item, String>("Item_Name"));
        item_qunt.setCellValueFactory(new PropertyValueFactory<Item, String>("Quantity"));

        StockItem.setItems(list);

    }

    private ObservableList<Item> getItemList() {
        ObservableList<Item> attend = FXCollections.observableArrayList();

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        ItemCollection = database.getCollection("Item");
        MongoCursor<Document> cursor = ItemCollection.find().iterator();
        try {

            for (int i = 0; i < ItemCollection.count(); i++) {


                Document doc = cursor.next();
                itid = doc.getString("Item_ID");
                //  System.out.println(cruid);
                itname = doc.getString("Item_Name");
                itqunt = doc.getString("Quantity");

                attend.add(new Item(itid, itname, itqunt));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;
//      call the setTable method


    }

}
