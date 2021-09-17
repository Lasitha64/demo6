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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class AdminUpdateDelete {
    private AlertBox ab;

    @FXML
    private Button updatebutton;

    @FXML
    private Button deletebutton;

    @FXML
    private TextField inputname;

    @FXML
    private TextField inputusername;

    @FXML
    private TextField inputemail;

    @FXML
    private TextField inputnic;

    @FXML
    private TextField inputmobile;

    @FXML
    private TextField inputpassword;

    @FXML
    private Button backbutton;

    @FXML
    private Button mainmenu;

    private MongoClient database;

    MongoCollection<Document> adminCollection;
    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        adminCollection = database.getCollection("Admin");
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminLogin.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteaccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminLogin.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        String inputnicText = inputnic.getText();
        Bson filter = eq("NIC No", inputnicText);
        DeleteResult result = adminCollection.deleteOne(filter);
        System.out.println(result);
        ab.display("OK", "Admin Deleted Successfully");

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
    void updateaccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminLogin.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        if (inputname.getText().isEmpty() || inputusername.getText().isEmpty() || inputemail.getText().isEmpty() || inputnic.getText().isEmpty() || inputmobile.getText().isEmpty() || inputpassword.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!inputmobile.getText().matches("[0-9]+")) {
            ab.display("Error", "Mobile No needs to be a number");
        } else {


            //updateemployeebutton

            // update one document
            String inputnameText = inputname.getText(), inputusernameText = inputusername.getText(), inputemailText = inputemail.getText(), inputnicText = inputnic.getText(), inputmobileText = inputmobile.getText(), inputpasswordText = inputpassword.getText();

            System.out.println(inputnameText + inputusernameText + inputemailText + inputnicText + inputmobileText + inputpasswordText);


            Bson filter = eq("NIC No", inputnicText);


            Bson updateName = set("Name", inputnameText);
            Bson updateUserName = set("Username", inputusernameText);// creating an array with a comment.
            Bson updateEmail = set("Email", inputemailText);
            Bson updateMobile = set("Mobile No", inputmobileText); // using addToSet so no effect.
            Bson updatePassword = set("Password", inputpasswordText);// using addToSet so no effect.

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateName);
            updatePredicates.add(updateUserName);
            updatePredicates.add(updateEmail);
            updatePredicates.add(updateMobile);
            updatePredicates.add(updatePassword);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = adminCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating Admin Details");
            System.out.println(newVersion);
            ab.display("OK", "Admin Updated Successfully");


        }
    }

    }



