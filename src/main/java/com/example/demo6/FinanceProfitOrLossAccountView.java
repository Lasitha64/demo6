//page i

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

public class FinanceProfitOrLossAccountView implements Initializable  {

    private String plID, plDate, plSales, plCOS, plOI, plOX, plGross, plNet;


    @FXML
    private TableView<FinanceProfitOrLossAccountMain> POLacc;

    @FXML
    private TableColumn<FinanceProfitOrLossAccountMain, String> POLid;

    @FXML
    private TableColumn<FinanceProfitOrLossAccountMain, String> Date;

    @FXML
    private TableColumn<FinanceProfitOrLossAccountMain, String> Sales;

    @FXML
    private TableColumn<FinanceProfitOrLossAccountMain, String> COS;

    @FXML
    private TableColumn<FinanceProfitOrLossAccountMain, String> OI;

    @FXML
    private TableColumn<FinanceProfitOrLossAccountMain, String> OX;

    @FXML
    private TableColumn<FinanceProfitOrLossAccountMain, String> GrossProfit;

    @FXML
    private TableColumn<FinanceProfitOrLossAccountMain, String> NetProfit;

    @FXML
    private Button btn_back;

    @FXML
    private Button graphs;

    public ObservableList<FinanceProfitOrLossAccountMain> list;
    public ObservableList<FinanceProfitOrLossAccountMain> searchlist;

//************************************************************************************************************
    //DB connection
    MongoCollection<Document> POLCollection;
//
//    @FXML
//    public void initialize(){
//        //initialize database connection
//        Database databaseController = new Database();
//        MongoDatabase database = databaseController.connectToDB("HerathCMD");
//
//        // get collection
//        POLCollection = database.getCollection("Finance Profit or loss account");
//    }
//
//
//    MongoDatabase database;

//*************************************************************************************************************

    @FXML
    void back_to_a(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
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
    void move_to_j(ActionEvent event) {
        graphs.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceProfitGraphs.fxml"));
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
        showPOLacc();
    }

    private void showPOLacc() {
        ObservableList<FinanceProfitOrLossAccountMain> list = getProfitOrLossAccount();

        POLid.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPLid()));
        Date.setCellValueFactory(new PropertyValueFactory<FinanceProfitOrLossAccountMain, String>("PLdate"));
        Sales.setCellValueFactory(new PropertyValueFactory<FinanceProfitOrLossAccountMain, String>("PLsales"));
        COS.setCellValueFactory(new PropertyValueFactory<FinanceProfitOrLossAccountMain, String>("PLcos"));
        OI.setCellValueFactory(new PropertyValueFactory<FinanceProfitOrLossAccountMain, String>("PLoi"));
        OX.setCellValueFactory(new PropertyValueFactory<FinanceProfitOrLossAccountMain, String>("PLox"));
        GrossProfit.setCellValueFactory(new PropertyValueFactory<FinanceProfitOrLossAccountMain, String>("PLgross"));
        NetProfit.setCellValueFactory(new PropertyValueFactory<FinanceProfitOrLossAccountMain, String>("PLnet"));


        POLacc.setItems(list);
    }

    private ObservableList<FinanceProfitOrLossAccountMain> getProfitOrLossAccount() {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        POLCollection = database.getCollection("Finance Profit or loss account");

        ObservableList<FinanceProfitOrLossAccountMain> attend = FXCollections.observableArrayList();

        MongoCursor<Document> cursor = POLCollection.find().iterator();
        try {

            for (int i = 0; i < POLCollection.count(); i++) {


                Document doc = cursor.next();
                plID = doc.getString("entry ID");

                plDate = doc.getString("Date");
                plSales = doc.getDouble("Sales").toString();
                plCOS = doc.getDouble("Cost_of_sales").toString();
                plOI = doc.getDouble("other_income").toString();
                plOX = doc.getDouble("other_expenses").toString();
                plGross = doc.getDouble("GrossProfit").toString();
                plNet = doc.getDouble("NetProfit").toString();

                attend.add(new FinanceProfitOrLossAccountMain(plID, plDate, plSales, plCOS, plOI, plOX, plGross, plNet));

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

