//page q

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

public class FinancePurchaseJournalView implements Initializable {

    private String pJ_ID, pJDate, pJDis, pJinvoice_no, pJSupplier, pJTotalValue, pJLedgerPage;

    @FXML
    private TableView<FinancePurchaseJournalMain> PJ;

    @FXML
    private TableColumn<FinancePurchaseJournalMain, String> PJID;

    @FXML
    private TableColumn<FinancePurchaseJournalMain, String> Date;

    @FXML
    private TableColumn<FinancePurchaseJournalMain, String> InvoiceNo;

    @FXML
    private TableColumn<FinancePurchaseJournalMain, String> Supplieer;

    @FXML
    private TableColumn<FinancePurchaseJournalMain, String> TotalValue;

    @FXML
    private TableColumn<FinancePurchaseJournalMain, String> LedgerPage;

    @FXML
    private Button back;

    @FXML
    private Button home;

    public ObservableList<FinanceGeneralJournalMain> list;
    public ObservableList<FinanceGeneralJournalMain> searchlist;


    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> PJCollection;

//    @FXML
//    public void initialize(){
//        //initialize database connection
//        Database databaseController = new Database();
//        MongoDatabase database = databaseController.connectToDB("HerathCMD");
//
//        // get collection
//        PJCollection = database.getCollection("Finance Purchase Journal");
//    }
//
//
//    MongoDatabase database;

    //************************************************************************************************************



    @FXML
    void back_to_a(ActionEvent event) {
        home.getScene().getWindow().hide();
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
        showPJ();
    }

    private void showPJ() {
        ObservableList<FinancePurchaseJournalMain> list = getPurJournal();

        PJID.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPJ_ID()));
        Date.setCellValueFactory(new PropertyValueFactory<FinancePurchaseJournalMain, String>("PJDate"));
        InvoiceNo.setCellValueFactory(new PropertyValueFactory<FinancePurchaseJournalMain, String>("PJlp"));
        Supplieer.setCellValueFactory(new PropertyValueFactory<FinancePurchaseJournalMain, String>("PJdis"));
        TotalValue.setCellValueFactory(new PropertyValueFactory<FinancePurchaseJournalMain, String>("PJsupplier"));
        LedgerPage.setCellValueFactory(new PropertyValueFactory<FinancePurchaseJournalMain, String>("PJtotal_value"));

        PJ.setItems(list);
    }

    private ObservableList<FinancePurchaseJournalMain> getPurJournal() {

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        PJCollection = database.getCollection("Finance Purchase Journal");

        ObservableList<FinancePurchaseJournalMain> attend = FXCollections.observableArrayList();

        MongoCursor<Document> cursor = PJCollection.find().iterator();
        try {

            for (int i = 0; i < PJCollection.count(); i++) {


                Document doc = cursor.next();
                pJ_ID = doc.getString("PurchaseJournal_ID");
                pJDate = doc.getString("PJDate");

                pJDis = doc.getString("Dis");
                pJinvoice_no = doc.getString("invoice_no");
                pJSupplier = doc.getString("Supplier");

                pJTotalValue = doc.getDouble("total_value").toString();
                pJLedgerPage = doc.getDouble("ledger_page").toString();

                attend.add(new FinancePurchaseJournalMain(pJ_ID, pJDate, pJDis, pJinvoice_no, pJSupplier, pJTotalValue, pJLedgerPage));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;
//      call the setTable method
    }
}
