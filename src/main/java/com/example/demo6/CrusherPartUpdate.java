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

public class CrusherPartUpdate implements Initializable {

    private Stage stage;
    private Scene scene;
    private int pos;
    private String cruid;
    private String cruname;
    private String cruquan;
    private String crupri;
    private String crudate;
    MongoClient database;
    MongoCollection<Document> crusherCollection;


    @FXML
    private TableView<Crusher> CrusherParts;

    @FXML
    private TableColumn<Crusher, String> cid;

    @FXML
    private TableColumn<Crusher, String> cname;

    @FXML
    private TableColumn<Crusher, String> cquan;

    @FXML
    private TableColumn<Crusher, String> cprice;

    @FXML
    private TableColumn<Crusher, String> cdate;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_quan;

    @FXML
    private TextField tf_price;

    @FXML
    private TextField tf_date1;


    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    private AlertBox ab;

    public ObservableList<Crusher> list;

   // public List attend = new ArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        showCrusher();

    }


    public void showCrusher() {

        ObservableList<Crusher>  list = getCrusherList();

        cid.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        cname.setCellValueFactory(new PropertyValueFactory<Crusher, String>("Name"));
        cquan.setCellValueFactory(new PropertyValueFactory<Crusher, String>("Quantity"));
        cprice.setCellValueFactory(new PropertyValueFactory<Crusher, String>("Price"));
        cdate.setCellValueFactory(new PropertyValueFactory<Crusher, String>("Date"));

        CrusherParts.setItems(list);

    }

    private ObservableList<Crusher> getCrusherList() {
        ObservableList<Crusher> attend = FXCollections.observableArrayList();

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        crusherCollection = database.getCollection("Metal Crusher");
        MongoCursor<Document> cursor = crusherCollection.find().iterator();
        try {

            for (int i = 0; i < crusherCollection.count(); i++) {


                Document doc = cursor.next();
                cruid = doc.getString("ID");
              //  System.out.println(cruid);
                cruname = doc.getString("Name");
                cruquan = doc.getString("Quantity");
                crupri = doc.getString("Price");
                crudate = doc.getString("Date");

                attend.add(new Crusher(cruid, cruname, cruquan, crupri, crudate));

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
    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("crusher-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/crusher.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Delete(ActionEvent event) {
        String Stock_ID = tf_id.getText();
        Bson filter = eq("ID", Stock_ID);
        DeleteResult result = crusherCollection.deleteOne(filter);
        showCrusher();
        System.out.println(result);
        ab.display("OK", "Delete Successful");


    }
    @FXML
    void handleMouseAction(MouseEvent event) {
        Crusher crusher = CrusherParts.getSelectionModel().getSelectedItem();
        tf_id.setText(crusher.getId());
        tf_name.setText(crusher.getName());
        tf_quan.setText(crusher.getQuantity());
        tf_price.setText(crusher.getPrice());
        tf_date1.setText(crusher.getDate());

    }
    @FXML
    void Update(ActionEvent event) {
        if (tf_id.getText().isEmpty() || tf_name.getText().isEmpty() || tf_quan.getText().isEmpty() || tf_price.getText().isEmpty() || tf_date1.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!tf_quan.getText().matches("[0-9]+")) {
            ab.display("Error", "Quantity needs to be a number");
        } else if (!tf_price.getText().matches("[0-9]+(\\.){0,1}[0-9]*")) {
            ab.display("Error", "Price needs to be a double (ex: 200.90)");
        } else {

            String Stock_ID = tf_id.getText(),
                    NameText = tf_name.getText(),
                    QuantityText = tf_quan.getText(),
                    PriceText = tf_price.getText(),
                    DateText = tf_date1.getText();

            System.out.println(Stock_ID + NameText + QuantityText + PriceText + DateText);

            Bson filter = eq("ID", Stock_ID);

            Bson updateName = set("Name", NameText);
            Bson updateQuan = set("Quantity", QuantityText);
            Bson updatePrice = set("Price", PriceText);
            Bson updateDate = set("Date", DateText);

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateName);
            updatePredicates.add(updateQuan);
            updatePredicates.add(updatePrice);
            updatePredicates.add(updateDate);

            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = crusherCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            showCrusher();
            System.out.println("Updating the generator maintenence stock");
            System.out.println(newVersion);
            ab.display("OK", "Update Successful");


        }

    }
}
