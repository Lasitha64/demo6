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
import javafx.scene.control.*;
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


public class ViewMedicineDetails implements Initializable {

    private Stage stage;
    private Scene scene;
    private String cruid;
    private String crudes;
    private String crupri;
    private String crudate;

    MongoCollection<Document> crusherCollection;

    @FXML
    private TableView<Medicine> CrusherParts;

    @FXML
    private TableColumn<Medicine, String> L_id;

    @FXML
    private TableColumn<Medicine, String> L_description;

    @FXML
    private TableColumn<Medicine, String> L_date;

    @FXML
    private TableColumn<Medicine, String> L_price;

    @FXML
    private TextField Lid;

    @FXML
    private TextField Ldescription;

    @FXML
    private DatePicker Ldate;

    @FXML
    private TextField Lprice;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    public ObservableList<Medicine> list;



    private void showCrusher() {
        ObservableList<Medicine> list = getCrusherList();

        L_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillNo()));
        L_description.setCellValueFactory(new PropertyValueFactory<Medicine, String>("description"));
        L_price.setCellValueFactory(new PropertyValueFactory<Medicine, String>("amount"));
        L_date.setCellValueFactory(new PropertyValueFactory<Medicine, String>("date"));

        CrusherParts.setItems(list);
    }

    private ObservableList<Medicine> getCrusherList() {
        ObservableList<Medicine> attend = FXCollections.observableArrayList();

        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        crusherCollection = database.getCollection("UtilitiesMedicine");
        MongoCursor<Document> cursor = crusherCollection.find().iterator();
        try {

            for (int i = 0; i < crusherCollection.count(); i++) {


                Document doc = cursor.next();
                cruid = doc.getString("BillNo");
                //  System.out.println(cruid);
                crudes = doc.getString("Description");
                crupri = doc.getString("Price");
                crudate = doc.getString("Date");

                attend.add(new Medicine(cruid, crudes, crupri, crudate));

            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;
//      call the setTable method
    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Utility-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Update(ActionEvent event) {
        String Stock_ID = Lid.getText(),
                RegText = Ldescription.getText() ,
                SiteText = Lprice.getText(),
                CondiText = Ldate.getValue().toString();


        //   System.out.println(Stock_ID + NameText + QuantityText + PriseText);

        Bson filter = eq("BillNo", Stock_ID);


        Bson updateQuantity = set("Description", RegText); // using addToSet so no effect.
        Bson updateSite = set("Price",SiteText);// using addToSet so no effect.
        Bson updatePrise = set("Date", CondiText);


        List<Bson> updatePredicates = new ArrayList<Bson>();
        updatePredicates.add(updateQuantity);
        updatePredicates.add(updatePrise);
        updatePredicates.add(updateSite);


        //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
        FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Document newVersion = crusherCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

       showCrusher();
    }


    @FXML
    void Delete(ActionEvent event) {

            String Stock_ID = Lid.getText();
            Bson filter = eq("BillNo", Stock_ID);
            DeleteResult result = crusherCollection.deleteOne(filter);

            showCrusher();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCrusher();
    }
}
