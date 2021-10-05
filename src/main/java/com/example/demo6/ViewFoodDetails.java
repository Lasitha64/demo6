package com.example.demo6;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class
ViewFoodDetails {

    private Stage stage;
    private Scene scene;
    private AlertBox ab;
    private String ttbillNo;
    private String ttDescription;
    private String ttAmount;
    private String ttDate;

    public ObservableList<Crusher> list;
    MongoCollection<Document> LoaderCollection;

    @FXML
    private TableView<Food> CrusherParts;

    @FXML
    private TableColumn<Food, String> tbillNo;

    @FXML
    private TableColumn<Food, String> tdescription;

    @FXML
    private TableColumn<Food, String> tamount;

    @FXML
    private TableColumn<Food, String> tdate;

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
        showCrusher();

    }

    private void showCrusher() {
        ObservableList<Food> list = getCrusherList();

        tbillNo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillNo()));
        tdescription.setCellValueFactory(new PropertyValueFactory<Food, String>("Description"));
        tamount.setCellValueFactory(new PropertyValueFactory<Food, String>("Amount"));
        tdate.setCellValueFactory(new PropertyValueFactory<Food, String>("Date"));


        CrusherParts.setItems(list);
    }

    private ObservableList<Food> getCrusherList() {
        ObservableList<Food> attend = FXCollections.observableArrayList();

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        LoaderCollection = database.getCollection("UtilitiesFood");
        MongoCursor<Document> cursor = LoaderCollection.find().iterator();
        try {

            for (int i = 0; i < LoaderCollection.count(); i++) {


                Document doc = cursor.next();
                ttbillNo = doc.getString("BillNo");
                //  System.out.println(cruid);
                ttDescription = doc.getString("Description");
                ttAmount = doc.getString("Amount");
                ttDate = doc.getString("Date");


                attend.add(new Food(ttbillNo, ttDescription, ttAmount, ttDate));

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
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Utility-main.fxml"));
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
        else if(!amount.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Amount needs to be a number");
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

            ab.display("Error","Data Updated");



        }
        showCrusher();
    }
    @FXML
    void Delete(ActionEvent event){

        String Stock_ID = billNo.getText();
        Bson filter = eq("BillNo", Stock_ID);
        DeleteResult result = LoaderCollection.deleteOne(filter);
        ab.display("Error","Data Deleted");
        showCrusher();

    }

    }
