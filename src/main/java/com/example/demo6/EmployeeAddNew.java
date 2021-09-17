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


public class EmployeeAddNew {
    private AlertBox ab;

    @FXML
    private Button employeeaddnewbutton;

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
    void addnewemployeebutton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        if(employeeinputname.getText().isEmpty() || employeeinputaddress.getText().isEmpty() || nicdisplay.getText().isEmpty() || employeeinputmobile.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!employeeinputmobile.getText().matches("[0-9]+")){
            ab.display("Error","Mobile No needs to be a number");
        }

        else {

            try {


                String employeeinputnameText = employeeinputname.getText(), employeeinputaddressText = employeeinputaddress.getText(), nicdisplayText = nicdisplay.getText(), employeeinputmobileText = employeeinputmobile.getText();
                insertEmployee(employeeCollection, employeeinputnameText, employeeinputaddressText, nicdisplayText, employeeinputmobileText);
                ab.display("OK", "Employee Added Successfully");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

        private void insertEmployee(MongoCollection<Document> employeeCollection, String employeeinputnameText, String employeeinputaddressText, String nicdisplayText, String employeeinputmobileText) {

            Document employee = new Document("_id", new ObjectId())
                    .append("Name", employeeinputnameText)
                    .append("Address", employeeinputaddressText)
                    .append("NIC No", nicdisplayText)
                    .append("Mobile No", employeeinputmobileText);
            employeeCollection.insertOne(employee);
            System.out.println("Connection S3");
        }






    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeDetails.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

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

