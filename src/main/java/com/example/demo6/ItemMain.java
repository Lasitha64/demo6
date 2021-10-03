package com.example.demo6;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemMain implements Initializable {

    private String itid;
    private String itname;
    private String itqunt;
    private String itprice;
    private String itdate;
    private String itdes;
    MongoClient database;
    MongoCollection<Document> ItemCollection;

    @FXML
    private TextField search_fld;

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
    public ObservableList<Item> searchlist;

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

    }

    @FXML
    void Update(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stock-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-view.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showItem();
        searchitem();
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

}
