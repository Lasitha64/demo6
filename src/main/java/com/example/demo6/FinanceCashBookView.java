//page r

package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class FinanceCashBookView {

    @FXML
    private Button btn_back1;

    @FXML
    private TableColumn<?, ?> DateCr;

    @FXML
    private TableColumn<?, ?> DiscriptionCr;

    @FXML
    private TableColumn<?, ?> LFcr;

    @FXML
    private TableColumn<?, ?> ValueCr;

    @FXML
    private TableColumn<?, ?> DateDr;

    @FXML
    private TableColumn<?, ?> DiscriptionDr;

    @FXML
    private TableColumn<?, ?> LFdr;

    @FXML
    private TableColumn<?, ?> ValueDr;



    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> CBCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        CBCollection = database.getCollection("Finance Cash Book");
    }


    MongoDatabase database;

    //************************************************************************************************************

    @FXML
    void back_to_a(ActionEvent event) {

        btn_back1.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceMain.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}
