//page o

package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinanceGeneralJournalView /*implements Initializable */{

    @FXML
    private TableView<FinanceGeneralJournalView> GJdis;

    @FXML
    private TableColumn<FinanceGeneralJournalView, String> Date;

    @FXML
    private TableColumn<FinanceGeneralJournalView, String> VoucherNo;

    @FXML
    private TableColumn<FinanceGeneralJournalView, String> Description;

    @FXML
    private TableColumn<FinanceGeneralJournalView, String> Dr;

    @FXML
    private TableColumn<FinanceGeneralJournalView, String> Cr;

    @FXML
    private Button back;

    @FXML
    private Button btn_back1;

    public ObservableList<FinanceGeneralJournalView> list;
    public ObservableList<FinanceGeneralJournalView> searchlist;



    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> GJCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        GJCollection = database.getCollection("Finance General Journal");
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

    @FXML
    void back_to_n(ActionEvent event) {
        back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceJournals.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        show_general_journal();
//    }
//
//    private void show_general_journal() {
//        ObservableList<FinanceGeneralJournalView>  list = show_general_journal();
//
//        Date.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
//        Date.setCellValueFactory(new PropertyValueFactory<FinanceGeneralJournalView, String>("Date"));
//        VoucherNo.setCellValueFactory(new PropertyValueFactory<FinanceGeneralJournalView, String>("VoucherNo"));
//        Description.setCellValueFactory(new PropertyValueFactory<FinanceGeneralJournalView, String>("Description"));
//        Dr.setCellValueFactory(new PropertyValueFactory<FinanceGeneralJournalView, String>("Dr"));
//        Cr.setCellValueFactory(new PropertyValueFactory<FinanceGeneralJournalView, String>("Cr"));
//
//        GJdis.setItems(list);
//    }
}

