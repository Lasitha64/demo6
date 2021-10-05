package com.example.demo6;

import com.example.demo6.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.bson.Document;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Charts implements Initializable {

    MongoCollection<Document> crusherCollection;

    @FXML
    private BarChart<String, Number> crusherChart;

    @FXML
    private CategoryAxis cruQuanY;

    @FXML
    private NumberAxis cruNamX;

    private String name;
    private String quantity;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_generateReports;

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("crusher-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/crusher.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        crusherCollection = database.getCollection("Metal Crusher");

        MongoCursor<Document> cursor = crusherCollection.find().iterator();

        XYChart.Series set1 = new XYChart.Series<>();

        for (int i = 0; i < crusherCollection.count() ; i++) {


            Document doc = cursor.next();
            name = doc.getString("Name");
            quantity = doc.getString("Quantity");



            int quan = Integer.parseInt(quantity);



            set1.getData().add(new XYChart.Data(name,quan));


            }

        crusherChart.getData().addAll(set1);


        }

    @FXML
    public void generateReports(ActionEvent event) throws IOException {
        File myFile = new File("C:\\Users\\MSI\\IdeaProjects\\demo6\\src\\main\\resources\\com\\example\\demo6\\Jasper\\Invoice_1.jasper");
            Desktop.getDesktop().open(myFile);
    }
}

