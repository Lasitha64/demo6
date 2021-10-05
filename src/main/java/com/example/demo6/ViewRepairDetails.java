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

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewRepairDetails implements Initializable {

    private String rpid;
    private String rpds;
    private String rpda;
    private String rpp;


    private AlertBox ab;


    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<repairR> repairR;

    @FXML
    private TableColumn<repairR, String> rid;

    @FXML
    private TableColumn<repairR, String> rd;

    @FXML
    private TableColumn<repairR, String> rda;

    @FXML
    private TableColumn<repairR, String> rp;


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

    MongoCollection<Document> vRepairCollection;

    @FXML

    public void initialize() {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        vRepairCollection = database.getCollection("vRepair");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("repairDetails.fxml"));
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
        DeleteResult result = vRepairCollection.deleteOne(filter);
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
            Document newVersion = vRepairCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the Vehicle repair Details");
            System.out.println(newVersion);

            ab.display("Done", " Data Updated Successfully");

        }

    }
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            showRepair();
        }

        public void showRepair() {

            ObservableList<repairR> list = getRepairList();

            rid.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
            rd.setCellValueFactory(new PropertyValueFactory<repairR, String>("Description"));
            rda.setCellValueFactory(new PropertyValueFactory<repairR, String>("Date"));
            rp.setCellValueFactory(new PropertyValueFactory<repairR, String>("Price"));

            repairR.setItems(list);

        }

        private ObservableList<repairR> getRepairList() {
            ObservableList<repairR> attend = FXCollections.observableArrayList();

            //initialize database connection
            Database databaseController = new Database();
            MongoDatabase database = databaseController.connectToDB("HerathCMD");
            // get collection
            vRepairCollection = database.getCollection("vRepair");
            MongoCursor<Document> cursor = vRepairCollection.find().iterator();
            try {

                for (int i = 0; i < vRepairCollection.count(); i++) {


                    Document doc = cursor.next();

                    //  System.out.println(cruid);

                    rpid = doc.getString("Vehicle_id");
                    rpds = doc.getString("Description");
                    rpda= doc.getString("Date");
                    rpp= doc.getString("Price");

                    attend.add(new repairR(rpid, rpds, rpda, rpp));

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
            repairR vehicle = repairR.getSelectionModel().getSelectedItem();

            vid.setText(vehicle.getId());
            des.setText(vehicle.getDescription());
            dt.setValue(LocalDate.parse(vehicle.getDate().toString()));
            p.setText(vehicle.getPrice());

        }

    }
