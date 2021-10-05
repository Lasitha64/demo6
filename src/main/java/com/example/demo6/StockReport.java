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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockReport implements Initializable {

    private String itid;
    private String itname;
    private String itqunt;
    private String itprice;
    private String itdate;
    private String itdes;
    MongoClient database;
    MongoCollection<Document> ItemAddReportCollection;

    @FXML
    private DatePicker datefld;

    @FXML
    private TableView<Item> StockItem;

    @FXML
    private TableColumn<Item, String> item_id;

    @FXML
    private TableColumn<Item, String> item_name;

    @FXML
    private TableColumn<Item, String> item_qunt;

    @FXML
    private TableColumn<Item, String> Item_price;

    @FXML
    private TableColumn<Item, String> item_date;


    @FXML
    private TableColumn<Item, String> item_desc;

    @FXML
    private Button buttn_back;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stcock-mngt.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-mngt.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        ItemAddReportCollection = database.getCollection("ItemAddReport");

        showItem();
    }

    public void showItem() {

        ObservableList<Item> list = getItemList();

        item_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        item_name.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
        item_qunt.setCellValueFactory(new PropertyValueFactory<Item, String>("Quantity"));
        Item_price.setCellValueFactory(new PropertyValueFactory<Item, String>("Price"));
        item_date.setCellValueFactory(new PropertyValueFactory<Item, String>("Date"));
        item_desc.setCellValueFactory(new PropertyValueFactory<Item, String>("Description"));

        StockItem.setItems(list);

    }

    private ObservableList<Item> getItemList() {
        ObservableList<Item> attend = FXCollections.observableArrayList();
        MongoCursor<Document> cursor = ItemAddReportCollection.find().iterator();
        try {

            for (int i = 0; i < ItemAddReportCollection.count(); i++) {


                Document doc = cursor.next();
                itid = doc.getString("Item_ID");
                //  System.out.println(itid);
                itname = doc.getString("Item_Name");
                itqunt = doc.getString("Quantity");
                itprice = doc.getString("Price");
                itdate = doc.getString("Date");
                itdes = doc.getString("Description");

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
}
