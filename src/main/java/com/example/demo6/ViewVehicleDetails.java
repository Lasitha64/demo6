package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewVehicleDetails {

    private AlertBox ab;

    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> cid;

    @FXML
    private TableColumn<?, ?> cname;

    @FXML
    private TableColumn<?, ?> cquan;

    @FXML
    private TableColumn<?, ?> cprice;

    @FXML
    private TableColumn<?, ?> cdate;

    @FXML
    private TextField vt;

    @FXML
    private TextField c;

    @FXML
    private TextField st;

    @FXML
    private TextField rpn;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    private MongoClient database;

    MongoCollection<Document> VehicleCollection;

    @FXML

    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        VehicleCollection = database.getCollection("Vehicle");
    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("vehicleDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Delete(ActionEvent event) {

        String rpnText = rpn.getText();
        Bson filter = eq("Reg_No", rpnText);
        DeleteResult result = VehicleCollection.deleteOne(filter);
        System.out.println(result);

        ab.display("Done"," Data Deleted Successfully");

    }

    @FXML
    void Update(ActionEvent event) {

        if (vt.getText().isEmpty() || st.getText().isEmpty() || c.getText().isEmpty() || rpn.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else {

            // update one document
            String vtText = vt.getText(), stText = st.getText(), cText = c.getText(), rpnText = rpn.getText();

            System.out.println(vtText + stText + cText + rpnText);

            Bson filter = eq("Reg_No", rpnText);


            Bson updateVtype = set("Vehicle_Type", vtText); // creating an array with a comment.
            Bson updateCondition = set("Condition", cText); // using addToSet so no effect.
            Bson updateSite = set("Site", stText); // using addToSet so no effect.

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateVtype);
            updatePredicates.add(updateCondition);
            updatePredicates.add(updateSite);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = VehicleCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the Vehicle Details");
            System.out.println(newVersion);

            ab.display("Done"," Data Updated Successfully");


        }
    }

<<<<<<< HEAD:src/main/java/com/example/demo6/ViewVehicleDetails.java
=======
    @FXML
    void Delete(ActionEvent event) {
        String eidText = eid.getText();
        Bson filter = eq("ExcavatorID", eidText);
        DeleteResult result = ExcavatorRepairCollection.deleteOne(filter);
        System.out.println(result);
        ab.display("Data Delete ","Data Delete Successfull");
    }
<<<<<<< HEAD:src/main/java/com/example/demo6/ViewVehicleDetails.java
    
>>>>>>> Nimith:src/main/java/com/example/demo6/ViewExcavRepDetails.java
=======
>>>>>>> Nimith:src/main/java/com/example/demo6/ViewExcavRepDetails.java
}

