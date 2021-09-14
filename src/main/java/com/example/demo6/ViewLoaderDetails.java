package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class ViewLoaderDetails {

    private Stage stage;
    private Scene scene;
    private alertbox ab;

    MongoCollection<Document> LoaderCollection;

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> L_id;

    @FXML
    private TableColumn<?, ?> L_brand;

    @FXML
    private TableColumn<?, ?> L_regno;

    @FXML
    private TableColumn<?, ?> L_price;

    @FXML
    private TableColumn<?, ?> L_site;

    @FXML
    private TextField Lid;

    @FXML
    private TextField Lbrand;

    @FXML
    private TextField Lregno;

    @FXML
    private TextField Lcondition;

    @FXML
    private TextField Lsite;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        LoaderCollection = database.getCollection("Loader");
    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Loader-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();}
    @FXML
    void Update(ActionEvent event){

        if(Lid.getText().isEmpty() || Lbrand.getText().isEmpty() || Lregno.getText().isEmpty() || Lcondition.getText().isEmpty() || Lsite
                .getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!Lregno.getText().matches("[0-9]+")){
            ab.display("Error","RegNo needs to be a number");
        }
        else {

            String Stock_ID = Lid.getText(),
                    BrandText = Lbrand.getText(),
                    RegText = Lregno.getText(),
                    CondiText = Lcondition.getText(),
                    SiteText = Lsite.getText();

            //   System.out.println(Stock_ID + NameText + QuantityText + PriseText);

            Bson filter = eq("LoaderID", Stock_ID);


            Bson updateName = set("Brand", BrandText); // creating an array with a comment.
            Bson updateQuantity = set("WorkingSite", RegText); // using addToSet so no effect.
            Bson updatePrise = set("Condition", CondiText);
            Bson updateSite = set("Site", SiteText);// using addToSet so no effect.

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateName);
            updatePredicates.add(updateQuantity);
            updatePredicates.add(updatePrise);
            updatePredicates.add(updateSite);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = LoaderCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the generator maintenence stock");
            System.out.println(newVersion);

        }
    }
    @FXML
    void Delete(ActionEvent event){

        String Stock_ID = L_id.getText();
        Bson filter = eq("LoaderID", Stock_ID);
        DeleteResult result = LoaderCollection.deleteOne(filter);
        System.out.println(result);

    }

    }
