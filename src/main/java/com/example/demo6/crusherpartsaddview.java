package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;


public class crusherpartsaddview {

    private Stage stage;
    private Scene scene;
    private AlertBox ab;
    private MongoClient database;
    private String cruid;

    MongoCollection<Document> crusherCollection;

    //start of attributes in crusher-parts-add fxml
    @FXML
    private TextField tx_id;

    @FXML
    private TextField tx_name;

    @FXML
    private TextField tx_quan;

    @FXML
    private TextField tx_pri;

    @FXML
    private DatePicker tx_date;

    @FXML
    private Button btn_add_parts;


    public void initialize() {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        crusherCollection = database.getCollection("Metal Crusher");

    }

    @FXML
    public void AddParts(ActionEvent event) {



        if (tx_id.getText().isEmpty() || tx_name.getText().isEmpty() || tx_quan.getText().isEmpty() || tx_pri.getText().isEmpty() || tx_date.getValue().toString().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!tx_quan.getText().matches("[0-9]+")) {
            ab.display("Error", "Quantity needs to be a number");
        } else if (!tx_pri.getText().matches("[0-9]+(\\.){0,1}[0-9]*")) {
            ab.display("Error", "Price needs to be a double (ex: 200.90)");
        }else if(checkdup()){
            ab.display("Error", "ID already exists");
        }
        else {


            try {


                checkdup();
              //  System.out.println(checkdup());
                String crusherid = tx_id.getText(), crushername = tx_name.getText(), crusherquan = tx_quan.getText(), crusherpri = tx_pri.getText(), crusherdate = tx_date.getValue().toString();
                insertAdmin(crusherCollection, crusherid, crushername, crusherquan, crusherpri, crusherdate);
                ab.display("OK", "Data Insert Successful");


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public boolean checkdup(){


        boolean res = false;
        String tid = tx_id.getText().toString();

        MongoCursor<Document> cursor = crusherCollection.find().iterator();

        for (int i = 0; i < crusherCollection.count() ; i++) {


            Document doc = cursor.next();
            cruid = doc.getString("ID");


            System.out.println(tid);
            System.out.println(cruid);

            if(tid.equals(cruid)){


                res = true;
               return res;
            }


        }


        return res;
    }




    private void insertAdmin(MongoCollection<Document> crusherCollection, String crusherid, String crushername, String crusherquan, String crusherpri, String crusherdate) {


        Document admin = new Document("_id", new ObjectId())
                .append("ID", crusherid)
                .append("Name", crushername)
                .append("Quantity", crusherquan)
                .append("Price", crusherpri)
                .append("Date", crusherdate);
        crusherCollection.insertOne(admin);
        System.out.println("Insert Successful");

    }


    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("crusher-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/crusher.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }


    //end -> functions use in crusher-parts fxml
}
