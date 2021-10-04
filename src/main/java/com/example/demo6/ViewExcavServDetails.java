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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class ViewExcavServDetails implements Initializable {

    private Stage stage;
    private Scene scene;
    private AlertBox ab;
    private String excavid;
    private String excavname;
    private String excavpri;
    private String excavdate;

    MongoCollection<Document> excavatorServiceCollection;
    @FXML
    private TableView<ExcavatorService> excavService;

    @FXML
    private TableColumn<ExcavatorService, String> exid;

    @FXML
    private TableColumn<ExcavatorService, String> exdes;

    @FXML
    private TableColumn<ExcavatorService, String> exdate;

    @FXML
    private TableColumn<ExcavatorService, String> exprice;
    @FXML
    private TextField eid;

    @FXML
    private TextField edescription;

    @FXML
    private TextField eprice;

    @FXML
    private DatePicker edate;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> ExcavatorServiceCollection;

    @FXML


    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        ExcavatorServiceCollection = database.getCollection("ExcavatorService");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Excav-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Update(ActionEvent event) {
        if (eid.getText().isEmpty() || edescription.getText().isEmpty() || edate.getValue().toString().isEmpty() || eprice.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if(!eprice.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Price needs to be a double (ex: 200.90)");
        } else {
            // update one document
            String eidText = eid.getText(), edescriptionText = edescription.getText(), edateText = edate.getValue().toString(), epriceText = eprice.getText();


            System.out.println(eidText + edescriptionText + edateText + epriceText);

            Bson filter = eq("ExcavatorID", eidText);


            Bson updateDescription = set("Description", edescriptionText); // creating an array with a comment.
            Bson updateDate = set("Date", edateText); // using addToSet so no effect.
            Bson updatePrice = set("Price", epriceText); // using addToSet so no effect.


            List<Bson> updatePredicates = new ArrayList<>();
            updatePredicates.add(updateDescription);
            updatePredicates.add(updateDate);
            updatePredicates.add(updatePrice);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = ExcavatorServiceCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating Excavator Service Details");
            System.out.println(newVersion);
            ab.display("Data Update ","Data Update Successfull");
        }
    }


    @FXML
    void Delete(ActionEvent event) {
        String eidText = eid.getText();
        Bson filter = eq("ExcavatorID", eidText);
        DeleteResult result = ExcavatorServiceCollection.deleteOne(filter);
        System.out.println(result);
        ab.display("Data Delete ","Data Delete Successfull");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCrusher();
    }
    public void showCrusher() {

        ObservableList<ExcavatorService> list = getCrusherList();

        exid.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        exdes.setCellValueFactory(new PropertyValueFactory<ExcavatorService, String>("Name"));
        exprice.setCellValueFactory(new PropertyValueFactory<ExcavatorService, String>("Price"));
        exdate.setCellValueFactory(new PropertyValueFactory<ExcavatorService, String>("Date"));


        excavService.setItems(list);

    }

    private ObservableList<ExcavatorService> getCrusherList() {
        ObservableList<ExcavatorService> attend = FXCollections.observableArrayList();

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        excavatorServiceCollection = database.getCollection("ExcavatorService");
        MongoCursor<Document> cursor = excavatorServiceCollection.find().iterator();
        try {

            for (int i = 0; i < excavatorServiceCollection.count(); i++) {


                Document doc = cursor.next();
                excavid = doc.getString("ExcavatorID");
                //  System.out.println(cruid);
                excavname = doc.getString("Description");
                excavpri = doc.getString("Price");
                excavdate = doc.getString("Date");

                attend.add(new ExcavatorService(excavid, excavname, excavpri, excavdate));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return attend;
    }
    @FXML
    void handleMouseAction(MouseEvent event) {
        ExcavatorService vehicle = excavService.getSelectionModel().getSelectedItem();
        eid.setText(vehicle.getId());
        edescription.setText(vehicle.getName());
        edate.setValue(LocalDate.parse(vehicle.getDate().toString()));
        eprice.setText(vehicle.getPrice());

    }
}
