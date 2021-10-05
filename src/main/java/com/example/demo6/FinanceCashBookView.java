//page r

package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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

public class FinanceCashBookView implements Initializable {

    private String cbID;

    private String cbDrDate;
    private String cbDrDis;
    private String cbDrLeFo;
    private String cbDrVal;

    private String cbCrDate;
    private String cbCrDis;
    private String cbCrLeFo;
    private String cbCrVal;



    @FXML
    private Button btn_back1;

    @FXML
    private TableView<FinanceCashbookMain> CashBook;

    @FXML
    private TableColumn<FinanceCashbookMain, String> CashBookID;

    @FXML
    private TableColumn<FinanceCashbookMain, String> DateCr;

    @FXML
    private TableColumn<FinanceCashbookMain, String> DiscriptionCr;

    @FXML
    private TableColumn<FinanceCashbookMain, String> LFcr;

    @FXML
    private TableColumn<FinanceCashbookMain, String> ValueCr;

    @FXML
    private TableColumn<FinanceCashbookMain, String> DateDr;

    @FXML
    private TableColumn<FinanceCashbookMain, String> DiscriptionDr;

    @FXML
    private TableColumn<FinanceCashbookMain, String> LFdr;

    @FXML
    private TableColumn<FinanceCashbookMain, String> ValueDr;

    public ObservableList<FinanceCashbookMain> list;
    public ObservableList<FinanceCashbookMain> searchlist;

    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> CBCollection;


//    @FXML
//    public void initialize(){
//        //initialize database connection
//        Database databaseController = new Database();
//        MongoDatabase database = databaseController.connectToDB("HerathCMD");
//
//        // get collection
//        CBCollection = database.getCollection("Finance Cash Book");
//    }
//
//
//    MongoDatabase database;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCB();
        // searchpart();
    }

    private void showCB() {
        ObservableList<FinanceCashbookMain>  list = getCashBook();

        CashBookID.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCID()));
        DateCr.setCellValueFactory(new PropertyValueFactory<FinanceCashbookMain, String>("CDateCr"));
        DiscriptionCr.setCellValueFactory(new PropertyValueFactory<FinanceCashbookMain, String>("CDiscriptionCr"));
        LFcr.setCellValueFactory(new PropertyValueFactory<FinanceCashbookMain, String>("CLFcr"));
        ValueCr.setCellValueFactory(new PropertyValueFactory<FinanceCashbookMain, String>("CValueCr"));
        DateDr.setCellValueFactory(new PropertyValueFactory<FinanceCashbookMain, String>("CDateDr"));
        DiscriptionDr.setCellValueFactory(new PropertyValueFactory<FinanceCashbookMain, String>("CDiscriptionDr"));
        LFdr.setCellValueFactory(new PropertyValueFactory<FinanceCashbookMain, String>("CLFdr"));
        ValueDr.setCellValueFactory(new PropertyValueFactory<FinanceCashbookMain, String>("CValueDr"));


        CashBook.setItems(list);
    }

    private ObservableList<FinanceCashbookMain> getCashBook() {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        CBCollection = database.getCollection("Finance Cash Book");

        ObservableList<FinanceCashbookMain> attend = FXCollections.observableArrayList();

        MongoCursor<Document> cursor = CBCollection.find().iterator();
        try {

            for (int i = 0; i < CBCollection.count(); i++) {


                Document doc = cursor.next();
                cbID = doc.getString("CB ID");

                cbDrDate = doc.getString("Dr CBDate");
                cbDrDis = doc.getString("Dr CBdiscription");
                cbDrLeFo = doc.getString("Dr Ledger_forlio");
                cbDrVal = doc.getDouble("Dr value").toString();

                cbCrDate = doc.getString("Cr CBDate");
                cbCrDis = doc.getString("Cr CBdiscription");
                cbCrLeFo = doc.getString("Cr Ledger_forlio");
                cbCrVal = doc.getDouble("Cr value").toString();

                attend.add(new FinanceCashbookMain(cbID, cbDrDate, cbDrDis, cbDrLeFo, cbDrVal, cbCrDate, cbCrDis, cbCrLeFo, cbCrVal));

            }
              list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;
//      call the setTable method
    }


}
