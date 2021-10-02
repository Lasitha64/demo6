//page p

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

public class FinanceSalesJournalView {

    @FXML
    private TableColumn<?, ?> Date;

    @FXML
    private TableColumn<?, ?> InvoiceNo;

    @FXML
    private TableColumn<?, ?> Customer;

    @FXML
    private TableColumn<?, ?> TotalValue;

    @FXML
    private TableColumn<?, ?> LedgerPage;

    @FXML
    private Button back;

    @FXML
    private Button btn_back1;


    //************************************************************************************************************
    //DB connection

    MongoCollection<Document> SJCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        SJCollection = database.getCollection("Finance Sales Journal");
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

}
