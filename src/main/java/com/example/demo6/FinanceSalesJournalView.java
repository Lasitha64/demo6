//page p

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

public class FinanceSalesJournalView implements Initializable {

    private String sJ_ID, sJDate, sJDis, sJinvoice_no, sJcustomer, sJTotalValue, sJLedgerPage;

    @FXML
    private TableView<FinanceSalesJournalMain> SJ;

    @FXML
    private TableColumn<FinanceSalesJournalMain, String> SJID;

    @FXML
    private TableColumn<FinanceSalesJournalMain, String> Date;

    @FXML
    private TableColumn<FinanceSalesJournalMain, String> InvoiceNo;

    @FXML
    private TableColumn<FinanceSalesJournalMain, String> Customer;

    @FXML
    private TableColumn<FinanceSalesJournalMain, String> TotalValue;

    @FXML
    private TableColumn<FinanceSalesJournalMain, String> LedgerPage;

    @FXML
    private Button back;

    @FXML
    private Button btn_back1;

    public ObservableList<FinanceGeneralJournalMain> list;
    public ObservableList<FinanceGeneralJournalMain> searchlist;

    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> SJCollection;

//    @FXML
//    public void initialize(){
//        //initialize database connection
//        Database databaseController = new Database();
//        MongoDatabase database = databaseController.connectToDB("HerathCMD");
//
//        // get collection
//        SJCollection = database.getCollection("Finance Sales Journal");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSJ();
    }

    private void showSJ() {
        ObservableList<FinanceSalesJournalMain> list = getSalesJournal();

        SJID.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSJ_ID()));
        Date.setCellValueFactory(new PropertyValueFactory<FinanceSalesJournalMain, String>("SJDate"));
        InvoiceNo.setCellValueFactory(new PropertyValueFactory<FinanceSalesJournalMain, String>("SJlp"));
        Customer.setCellValueFactory(new PropertyValueFactory<FinanceSalesJournalMain, String>("SJdis"));
        TotalValue.setCellValueFactory(new PropertyValueFactory<FinanceSalesJournalMain, String>("SJcustomer"));
        LedgerPage.setCellValueFactory(new PropertyValueFactory<FinanceSalesJournalMain, String>("SJtotal_value"));

        SJ.setItems(list);
    }

    private ObservableList<FinanceSalesJournalMain> getSalesJournal() {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        SJCollection = database.getCollection("Finance Sales Journal");

        ObservableList<FinanceSalesJournalMain> attend = FXCollections.observableArrayList();

        MongoCursor<Document> cursor = SJCollection.find().iterator();
        try {

            for (int i = 0; i < SJCollection.count(); i++) {


                Document doc = cursor.next();
                sJ_ID = doc.getString("SalesJournal_ID");
                sJDate = doc.getString("SJDate");

                sJDis = doc.getString("Dis");
                sJinvoice_no = doc.getString("invoice_no");
                sJcustomer = doc.getString("customer");

                sJTotalValue = doc.getDouble("total_value").toString();
                sJLedgerPage = doc.getDouble("ledger_page").toString();

                attend.add(new FinanceSalesJournalMain(sJ_ID, sJDate, sJDis, sJinvoice_no, sJcustomer, sJTotalValue, sJLedgerPage));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;

    }
}
