package com.example.demo6;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;

public class LubricantMain implements Initializable {

    private String lubid;
    private String lubname;
    private String lubqunt;
    private String lubprice;
    private String lubdate;
    private String lubdes;
    MongoClient database;
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
    private TableColumn<Lubricant, String> item_qunt;

    @FXML
    private Button buttn_remove;

    @FXML
    private Button buttn_dlt;

    @FXML
    private Button buttn_add;

    @FXML
    private Button buutn_back;

    @FXML
    private TextField itemId;

    @FXML
    private TextField itemName;

    @FXML
    private TextField itemQuanty;

    public ObservableList<Lubricant> list;
    public ObservableList<Lubricant> searchlist;
    private AlertBox ab;

    @FXML
    void Add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stock-lubricant.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-lubricant.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
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
        Lubricant selectedfordelete = StockLubricant.getSelectionModel().getSelectedItem();
        if (selectedfordelete == null){
            ab.display("No item selected", "Please select the item you want to delete.");
        }
        else {
            String idtxt = itemId.getText();
            System.out.println(idtxt);
            Bson filter = eq("Item_ID", idtxt);
            DeleteResult result = LubricantCollection.deleteOne(filter);
            showLubricant();
            System.out.println(result);
            ab.display("OK", "Delete Successful");
        }
    }

    @FXML
    void Remove(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lubricant-remove.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/lubricant-remove.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
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
        item_qunt.setCellValueFactory(new PropertyValueFactory<Lubricant, String>("Quantity"));

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

    @FXML
    void searchLubricant() {

        ObservableList<Lubricant>  searchlist = getLubricantList();

        item_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        item_name.setCellValueFactory(new PropertyValueFactory<Lubricant, String>("Name"));
        item_qunt.setCellValueFactory(new PropertyValueFactory<Lubricant, String>("Quantity"));

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
        itemId.setText(lubricant.getId());
        itemName.setText(lubricant.getName());
        itemQuanty.setText(lubricant.getQuantity());
    }


}
