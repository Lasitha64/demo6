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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ViewExcavDetails implements Initializable {

    private Stage stage;
    private Scene scene;
    private AlertBox ab;
    private String excavid;
    private String excavbrand;
    private String excavreg;
    private String excavcon;
    private String excavsite;

    MongoCollection<Document> excavatorCollection;

    @FXML
    private TableView<Excavator> ExcavatorDetails;

    @FXML
    private TableColumn<Excavator, String> exid;

    @FXML
    private TableColumn<Excavator, String> exbrand;

    @FXML
    private TableColumn<Excavator, String> exreg;

    @FXML
    private TableColumn<Excavator, String> excon;

    @FXML
    private TableColumn<Excavator, String> exsite;

    @FXML
    private TextField eid;

    @FXML
    private TextField ebrand;

    @FXML
    private TextField ereg;

    @FXML
    private TextField econ;

    @FXML
    private TextField esite;


    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> ExcavatorCollection;



    @FXML


    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        ExcavatorCollection = database.getCollection("Excavator");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Excav-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();}
    @FXML
    void Update(ActionEvent event) {
        if (eid.getText().isEmpty() || ebrand.getText().isEmpty() || econ.getText().isEmpty() || esite.getText().isEmpty() || ereg.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!ereg.getText().matches("[0-9]+")) {
            ab.display("Error", "Quantity needs to be a number");
        } else {
            // update one document
            String eidText = eid.getText(), ebrandText = ebrand.getText(), esiteText = esite.getText(), econText = econ.getText(), eregText = ereg.getText();


            System.out.println(eidText + ebrandText + esiteText + econText + eregText);

            Bson filter = eq("ExcavatorID", eidText);


            Bson updateBrand = set("Brand", ebrandText); // creating an array with a comment.
            Bson updateReg = set("Registration No", eregText); // using addToSet so no effect.
            Bson updateCon = set("Condition", econText); // using addToSet so no effect.
            Bson updateSite = set("Site", esiteText); // using addToSet so no effect.


            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateBrand);
            updatePredicates.add(updateReg);
            updatePredicates.add(updateCon);
            updatePredicates.add(updateSite);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = ExcavatorCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the Excavator Details");
            System.out.println(newVersion);
            ab.display("Data Update ","Data Update Successfull");

        }
    }
    @FXML
    void Delete(ActionEvent event){
        String eidText = eid.getText();
        Bson filter = eq("ExcavatorID", eidText);
        DeleteResult result = ExcavatorCollection.deleteOne(filter);
        System.out.println(result);
        ab.display("Data Delete ","Data Delete Successfull");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCrusher();
    }
    public void showCrusher() {

        ObservableList<Excavator> list = getCrusherList();

        exid.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        exbrand.setCellValueFactory(new PropertyValueFactory<Excavator, String>("Brand"));
        exreg.setCellValueFactory(new PropertyValueFactory<Excavator, String>("Regnumber"));
        excon.setCellValueFactory(new PropertyValueFactory<Excavator, String>("Condition"));
        exsite.setCellValueFactory(new PropertyValueFactory<Excavator, String>("Site"));

        ExcavatorDetails.setItems(list);

    }

    private ObservableList<Excavator> getCrusherList() {
        ObservableList<Excavator> attend = FXCollections.observableArrayList();

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        excavatorCollection = database.getCollection("Excavator");
        MongoCursor<Document> cursor = excavatorCollection.find().iterator();
        try {

            for (int i = 0; i < excavatorCollection.count(); i++) {


                Document doc = cursor.next();
                excavid = doc.getString("ExcavatorID");
                //  System.out.println(cruid);
                excavbrand = doc.getString("Brand");
                excavreg = doc.getString("Registration No");
                excavcon = doc.getString("Condition");
                excavsite = doc.getString("Site");

                attend.add(new Excavator(excavid, excavbrand, excavreg, excavcon , excavsite));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;
    }
    @FXML
    void handleMouseAction(MouseEvent event) {
        Excavator vehicle = ExcavatorDetails.getSelectionModel().getSelectedItem();

        eid.setText(vehicle.getId());
        ebrand.setText(vehicle.getBrand());
        ereg.setText(vehicle.getRegnumber());
        econ.setText(vehicle.getCondition());
        esite.setText(vehicle.getSite());

    }

}
