package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.bson.Document;
import javafx.scene.control.cell.TextFieldTableCell;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.example.demo6.Database;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class CrusherPartUpdate implements Initializable{

    private Stage stage;
    private Scene scene;
    private int pos;
    private String cruid;
    private String cruname;
    private String cruquan;
    private String crupri;
    private String crudate;
    MongoClient database;
    MongoCollection<Document> crusherCollection;
    MongoCursor<Document> cursor = crusherCollection.find().iterator();


    @FXML
    private TableView<Crusher> CrusherParts;

    @FXML
    private TableColumn<Crusher, String> cid;

    @FXML
    private TableColumn<Crusher, String> cname;

    @FXML
    private TableColumn<Crusher, Integer> cquan;

    @FXML
    private TableColumn<Crusher, Double> cprice;

    @FXML
    private TableColumn<Crusher, String> cdate;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_quan;

    @FXML
    private TextField tf_price;

    @FXML
    private TextField tf_date1;

    @FXML
    private TextField tf_date;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    private AlertBox ab;

    public ObservableList<Crusher> list;



    public List attend = new ArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //    showCrusher();
      //  CrusherParts.setEditable(true);
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        crusherCollection = database.getCollection("Metal Crusher");

    }



//    public Connection getConnection(){
//        Connection conn;
//        try{
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crusherdb","root","test");
//            return conn;
//
//        }catch(Exception ex){
//            System.out.println("Error :"+ex.getMessage());
//            return null;
//        }
//    }
//
//    private void executeQuery(String query){
//        Connection conn = getConnection();
//        Statement st;
//        try{
//            st = conn.createStatement();
//            st.executeUpdate(query);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//
//
//    }
//
//    public ObservableList<Crusher> getCrusherList(){
//        ObservableList<Crusher> bookList = FXCollections.observableArrayList();
//        Connection conn = getConnection();
//        String query = "select * from parts";
//        Statement st;
//        ResultSet rs;
//
//        try{
//            st = conn.createStatement();
//            rs = st.executeQuery(query);
//            Crusher cru;
//            while(rs.next()){
//                cru = new Crusher(rs.getString("ID"),rs.getString("Name"),rs.getInt("Quantity"),rs.getDouble("Price"),rs.getString("Date"));
//                bookList.add(cru);
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return bookList;
//    }
//
//    public void showCrusher(){
//        ObservableList<Crusher>  list = getCrusherList();
//
//        cid.setCellValueFactory(new PropertyValueFactory<Crusher, String>("id"));
//        cname.setCellValueFactory(new PropertyValueFactory<Crusher, String>("name"));
//        cquan.setCellValueFactory(new PropertyValueFactory<Crusher, Integer>("quantity"));
//        cprice.setCellValueFactory(new PropertyValueFactory<Crusher, Double>("price"));
//        cdate.setCellValueFactory(new PropertyValueFactory<Crusher, String>("date"));
//
//        CrusherParts.setItems(list);
//
//    }

    @FXML
    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("crusher-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/crusher.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Delete(ActionEvent event) {
        String Stock_ID = tf_id.getText();
        Bson filter = eq("Stock_ID", Stock_ID);
        DeleteResult result = crusherCollection.deleteOne(filter);
        System.out.println(result);
//        String query = "DELETE FROM parts WHERE id ='" + tf_id.getText() + "'";
//        executeQuery(query);
//        showCrusher();
    }

    @FXML
    void Update(ActionEvent event) {
        if(tf_id.getText().isEmpty() || tf_name.getText().isEmpty() || tf_quan.getText().isEmpty() || tf_price.getText().isEmpty() || tf_date1.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!tf_quan.getText().matches("[0-9]+")){
            ab.display("Error","Quantity needs to be a number");
        }
        else if(!tf_price.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Price needs to be a double (ex: 200.90)");
        }
        else{
//            String query = "UPDATE parts SET Name = '"+tf_name.getText()+"' ,Quantity = "+tf_quan.getText()+",Price= "+tf_price.getText()+" ,Date= '"+tf_date1.getText()+"' WHERE ID ='" + tf_id.getText() + "'";
//            executeQuery(query);
//            showCrusher();
            String Stock_ID = tf_id.getText(),
                    NameText = tf_name.getText() ,
                    QuantityText = tf_quan.getText() ,
                    PriceText = tf_price.getText(),
                    DateText = tf_date.getText();

            System.out.println(Stock_ID + NameText + QuantityText + PriceText + DateText);

            Bson filter = eq("Stock_ID", Stock_ID);

            Bson updateName = set("Name", NameText);
            Bson updateQuan = set("Quantity", QuantityText);
            Bson updatePrice = set("price", PriceText);
            Bson updateDate = set("Date", DateText);

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateName);
            updatePredicates.add(updateQuan);
            updatePredicates.add(updatePrice);
            updatePredicates.add(updateDate);

            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = crusherCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating the generator maintenence stock");
            System.out.println(newVersion);
        }

    }
}
