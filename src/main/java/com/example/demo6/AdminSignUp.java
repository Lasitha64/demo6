package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
import org.bson.types.ObjectId;

import java.io.IOException;

public class AdminSignUp {
    private AlertBox ab;

    @FXML
    private TextField inputname;

    @FXML
    private Button createaccountbutton;

    @FXML
    private Button backbutton;

    @FXML
    private Button mainmenu;

    @FXML
    private TextField inputusename;

    @FXML
    private TextField inputemail;

    @FXML
    private TextField inputnic;

    @FXML
    private TextField inputmobile;

    @FXML
    private TextField inputpassword;

    private MongoClient database;

    MongoCollection<Document> AdminCollection;

    @FXML

    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        AdminCollection = database.getCollection("Admin");
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void createaccount(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        if(inputname.getText().isEmpty() || inputusename.getText().isEmpty() || inputemail.getText().isEmpty() || inputnic.getText().isEmpty() || inputmobile.getText().isEmpty() || inputpassword.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!inputmobile.getText().matches("[0-9]+")){
            ab.display("Error","Quantity needs to be a number");
        }

        else {

            try {


                String inputnameText = inputname.getText(), inputusenameText = inputusename.getText(), inputemailText = inputemail.getText(), inputnicText = inputnic.getText(), inputmobileText = inputmobile.getText(), inputpasswordText = inputpassword.getText();
                insertAdmin(AdminCollection, inputnameText, inputusenameText, inputemailText, inputnicText, inputmobileText, inputpasswordText);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void insertAdmin(MongoCollection<Document> adminCollection, String inputnameText, String inputusenameText, String inputemailText, String inputnicText, String inputmobileText, String inputpasswordText) {

        Document admin = new Document("_id", new ObjectId())
                .append("Name", inputnameText)
                .append("Username", inputusenameText)
                .append("Email", inputemailText)
                .append("NIC No", inputnicText)
                .append("Mobile No", inputmobileText)
                .append("Password", inputpasswordText);
        adminCollection.insertOne(admin);
        System.out.println("Connection S3");
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

}
