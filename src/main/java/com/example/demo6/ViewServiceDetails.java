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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ViewServiceDetails implements Initializable {

    private String spid;
    private String spds;
    private String spda;
    private String spp;

    private AlertBox ab;

    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<serviceS> serviceS;

    @FXML
    private TableColumn<serviceS, String> sid;

    @FXML
    private TableColumn<serviceS, String> sd;

    @FXML
    private TableColumn<serviceS, String> sda;

    @FXML
    private TableColumn<serviceS, String> sp;


    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private TextField vid;

    @FXML
    private TextField des;

    @FXML
    private DatePicker dt;

    @FXML
    private TextField p;
    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> vServiceCollection;

    @FXML

    public void initialize() {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        vServiceCollection = database.getCollection("vService");
    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ServiceDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void Delete(ActionEvent event) {

        String vidText = vid.getText();
        Bson filter = eq("Vehicle_id",vidText);
        DeleteResult result = vServiceCollection.deleteOne(filter);
        System.out.println(result);

        ab.display("Done"," Data Deleted Successfully");

    }

    @FXML
    void Update(ActionEvent event) {

        if (vid.getText().isEmpty() || des.getText().isEmpty() || p.getText().isEmpty() || dt.getValue().toString().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");

        } else if (!p.getText().matches("[0-9]+")) {
            ab.display("Error", "Price needs to be a number");
        } else {

            // update one document
            String vidText = vid.getText(), desText = des.getText(), pText = p.getText(), dtText = dt.getValue().toString();


            System.out.println(vidText + desText + pText + dtText);

            Bson filter = eq("Vehicle_id", vidText);


            Bson updatedes = set("Description", desText); // creating an array with a comment.
            Bson updatep = set("Price", pText); // using addToSet so no effect.
            Bson updatedate = set("Date", dtText); // using addToSet so no effect.

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updatedes);
            updatePredicates.add(updatep);
            updatePredicates.add(updatedate);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = vServiceCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the Vehicle repair Details");
            System.out.println(newVersion);

            ab.display("Done"," Data Updated Successfully");


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showService();
    }

    public void showService() {

        ObservableList<serviceS> list = getServiceList();

        sid.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        sd.setCellValueFactory(new PropertyValueFactory<serviceS, String>("Description"));
        sda.setCellValueFactory(new PropertyValueFactory<serviceS, String>("Date"));
        sp.setCellValueFactory(new PropertyValueFactory<serviceS, String>("Price"));

        serviceS.setItems(list);

    }

    private ObservableList<serviceS> getServiceList() {
        ObservableList<serviceS> attend = FXCollections.observableArrayList();

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        vServiceCollection = database.getCollection("vService");
        MongoCursor<Document> cursor = vServiceCollection.find().iterator();
        try {

            for (int i = 0; i < vServiceCollection.count(); i++) {


                Document doc = cursor.next();

                //  System.out.println(cruid);

                spid = doc.getString("Vehicle_id");
                spds = doc.getString("Description");
                spda= doc.getString("Date");
                spp= doc.getString("Price");

                attend.add(new serviceS(spid, spds, spda, spp));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return attend;
//      call the setTable method
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        serviceS vehicle = serviceS.getSelectionModel().getSelectedItem();

        vid.setText(vehicle.getId());
        des.setText(vehicle.getDescription());
        dt.setValue(LocalDate.parse(vehicle.getDate().toString()));
        p.setText(vehicle.getPrice());

    }

}


