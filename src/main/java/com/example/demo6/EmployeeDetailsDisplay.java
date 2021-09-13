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

public class EmployeeDetailsDisplay {

    @FXML
    private Button searchamotheremployeebutton;

    @FXML
    private Button updateemployeebutton;

    @FXML
    private Button deleteemployeebutton;

    @FXML
    private TextField employeeinputname;

    @FXML
    private TextField employeeinputaddress;

    @FXML
    private TextField nicdisplay;

    @FXML
    private TextField employeeinputmobile;

    @FXML
    private Button backbutton;

    @FXML
    private Button mainmenu;

    private MongoClient database;

    MongoCollection<Document> employeeCollection;
    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        employeeCollection = database.getCollection("Employee");
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteemployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        String nicdisplayText = nicdisplay.getText();
        Bson filter = eq("NIC No", nicdisplayText);
        DeleteResult result = employeeCollection.deleteOne(filter);
        System.out.println(result);


    }

    @FXML
    void mainmenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void searchanotheremployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void updateemployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        //updateemployeebutton

        // update one document
        String employeeinputnameText = employeeinputname.getText(), employeeinputaddressText = employeeinputaddress.getText(), nicdisplayText = nicdisplay.getText(), employeeinputmobileText = employeeinputmobile.getText();

        System.out.println(employeeinputnameText + employeeinputaddressText + nicdisplayText + employeeinputmobileText);



        Bson filter = eq("NIC No", nicdisplayText);


        Bson updateName = set("Name", employeeinputnameText); // creating an array with a comment.
        Bson updateQuantity = set("Address", employeeinputaddressText); // using addToSet so no effect.
        Bson updatePrise = set("Mobile No", employeeinputmobileText); // using addToSet so no effect.

        List<Bson> updatePredicates = new ArrayList<Bson>();
        updatePredicates.add(updateName);
        updatePredicates.add(updateQuantity);
        updatePredicates.add(updatePrise);


        //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
        FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Document newVersion = employeeCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

        System.out.println("Updating Employee Details");
        System.out.println(newVersion);







    }

}

