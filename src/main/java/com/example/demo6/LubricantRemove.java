package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class LubricantRemove implements Initializable {

    private String lubid;
    private String lubname;
    private String lubqunt;
    private String lubprice;
    private String lubdate;
    private String lubdes;
    private AlertBox ab;
    private MongoClient database;
    MongoCollection<Document> LubricantCollection;
    MongoCollection<Document> LubricantRemoveReportCollection;

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
    private DatePicker datefld;

    @FXML
    private TextArea Descipfld;

    @FXML
    private Button buttn_remove;

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
    void Remove(ActionEvent event) throws IOException {

        if(idfld.getText().isEmpty() || namefld.getText().isEmpty() || quantityfld.getText().isEmpty() || datefld.getValue().toString().isEmpty() || Descipfld.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!quantityfld.getText().matches("[0-9]+")){
            ab.display("Error","Quantity needs to be a number");
        }
        else {
            //insert data to LubricantRemoveReport
            try {
                String idfldText1 = idfld.getText(), namefldText1 = namefld.getText(), quantityfldText1 = quantityfld.getText(), datefldText1 = datefld.getValue().toString(), DescipfldText1 = Descipfld.getText();
                insertLubricantRemoveReport(LubricantRemoveReportCollection, idfldText1, namefldText1, quantityfldText1, datefldText1, DescipfldText1);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //calculate the quantity
            Lubricant lubricant = StockLubricant.getSelectionModel().getSelectedItem();

            String Qunt =lubricant.getQuantity();

            int Qunty=Integer.parseInt(lubricant.getQuantity().toString());
            int qunt=Integer.parseInt(quantityfld.getText().toString());

            quantityfld.setText(Integer.toString(Qunty-qunt));

            // update one document
            String idfldText = idfld.getText(), namefldText = namefld.getText(), quantityfldText = quantityfld.getText(), datefldText = datefld.getValue().toString(), DescipfldText = Descipfld.getText();

            System.out.println(idfldText + namefldText + quantityfldText + datefldText + DescipfldText);

            Bson filter = eq("Item_ID", idfldText);


            Bson updateName = set("Item_Name", namefldText); // creating an array with a comment.
            Bson updateQuantity = set("Quantity", quantityfldText); // using addToSet so no effect.
            Bson updateDate = set("Date", datefldText);
            Bson updateDescription = set("Description", DescipfldText);

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateName);
            updatePredicates.add(updateQuantity);
            updatePredicates.add(updateDate);
            updatePredicates.add(updateDescription);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = LubricantCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            showLubricant();
            clearText();
            System.out.println("Updating the stock");
            System.out.println(newVersion);
            ab.display("Success","Data Updated Successfully!");
        }
    }


    @FXML
    void handleMouseAction(MouseEvent event) {
        Lubricant lubricant = StockLubricant.getSelectionModel().getSelectedItem();
        idfld.setText(lubricant.getId());
        namefld.setText(lubricant.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LubricantCollection = database.getCollection("Lubricant");
        LubricantRemoveReportCollection = database.getCollection("LubricantRemoveReport");

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

    private void clearText(){
        idfld.clear();
        namefld.clear();
        quantityfld.clear();
        datefld.getEditor().clear();
        Descipfld.clear();
    }

    private void insertLubricantRemoveReport(MongoCollection<Document> lubricantremovereportCollection, String idfldText1, String namefldText1, String quantityfldText1, String datefldText1, String DescipfldText1) {

        Document item = new Document("_id", new ObjectId())
                .append("Item_ID", idfldText1)
                .append("Item_Name", namefldText1)
                .append("Quantity", quantityfldText1)
                .append("Date", datefldText1)
                .append("Description", DescipfldText1);
        lubricantremovereportCollection.insertOne(item);
        System.out.println("Connection S3");
    }

}
