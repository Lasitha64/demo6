package com.example.demo6;



import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class ViewHammerDetails implements Initializable {

    private Stage stage;
    private Scene scene;
    private AlertBox ab;

    private String haid;
    private String haname;
    private String haquan;
    private String hapri;
    private String hadate;






    @FXML
    private TableView<Hammer> CrusherParts;

    @FXML
    private TableColumn<Hammer, String> chid;

    @FXML
    private TableColumn<Hammer, String> csname;

    @FXML
    private TableColumn<Hammer, String> cquan;

    @FXML
    private TableColumn<Hammer, String> cprice;

    @FXML
    private TableColumn<Hammer, String> cdate;

    @FXML
    private TextField hid;

    @FXML
    private TextField hs;

    @FXML
    private TextField h_quan;

    @FXML
    private TextField h_price;

    @FXML
    private TextField h_date1;



    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;


    MongoCollection<Document> HammerCollection;
    private String DateText;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        HammerCollection = database.getCollection("Hammer");
    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("hammer-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();}
    @FXML
    void Update(ActionEvent event) {

        if (hid.getText().isEmpty() || hs.getText().isEmpty() || h_quan.getText().isEmpty() || h_price.getText().isEmpty() || h_date1.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!h_quan.getText().matches("[0-9]+")) {
            ab.display("Error", "Quantity needs to be a number");
        } else if (!h_price.getText().matches("[0-9]+(\\.){0,1}[0-9]*")) {
            ab.display("Error", "Price needs to be a double (ex: 200.90)");
        } else {


            // update one document
            String Stock_ID = hid.getText(),
                    NameText = hs.getText(),
                    QuantityText = h_quan.getText(),
                    PriseText = h_price.getText();
            DateText = h_date1.getText();

            System.out.println(Stock_ID + NameText + QuantityText + PriseText);

            Bson filter = eq("Hammer id", Stock_ID);


            Bson updateId = set("Hammer id", NameText); // creating an array with a comment.
            Bson updateName = set("spare part name", QuantityText); // using addToSet so no effect.
            Bson updateQuantity = set("quantity", PriseText); // using addToSet so no effect.
            Bson updatePrice = set("price", PriseText);
            Bson updatedate = set("quantity", PriseText);

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateId);
            updatePredicates.add(updateName);
            updatePredicates.add(updateQuantity);
            updatePredicates.add(updatePrice);
            updatePredicates.add(updatedate);



            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = HammerCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            ab.display("Error", "Data entry successful");

            System.out.println("Updating the hammer maintenence stock");
            System.out.println(newVersion);

        }
    }

    @FXML
    void Delete(ActionEvent event){
        String Stock_ID = hid.getText();
        Bson filter = eq("Hammer id", Stock_ID);
        DeleteResult result = HammerCollection.deleteOne(filter);
        ab.display("Error", "Data deleted successful");
        System.out.println(result);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showHammer();
    }

    private void showHammer() {
        ObservableList<Hammer> list = getHammerList();

        chid.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getID()));
        csname.setCellValueFactory(new PropertyValueFactory<Hammer, String>("Name"));
        cquan.setCellValueFactory(new PropertyValueFactory<Hammer, String>("Quantity"));
        cprice.setCellValueFactory(new PropertyValueFactory<Hammer, String>("Price"));
        cdate.setCellValueFactory(new PropertyValueFactory<Hammer, String>("Date"));

        CrusherParts.setItems(list);


    }

    private ObservableList<Hammer> getHammerList() {

        ObservableList<Hammer> attend = FXCollections.observableArrayList();
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        HammerCollection = database.getCollection("Hammer");
        MongoCursor<Document> cursor = HammerCollection.find().iterator();
        try {

            for (int i = 0; i < HammerCollection.count(); i++) {


                Document doc = cursor.next();
                haid = doc.getString("Hammer id");
                haname = doc.getString(" spare part name");
                haquan = doc.getString("quantity");
                hapri = doc.getString("price");
                hadate = doc.getString("Date");
                attend.add(new Hammer(haid, haname, haquan, hapri, hadate));
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





