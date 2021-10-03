//page c

package com.example.demo6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.time.LocalDate;

public class GeneMaintainance {

    @FXML
    private TextField GeneratorID;

    @FXML
    private TextField MaintenanceNO;

    @FXML
    private DatePicker MaintenanceDate;

    @FXML
    private RadioButton servise;


    @FXML
    private ToggleGroup Mtype;

    @FXML
    private RadioButton repair;

    @FXML
    private Button btn_back;

    @FXML
    private Button Next;

    @FXML
    private Button Veiw_maintenance_details;

//    //radio
//    private final ToggleGroup toggles = new ToggleGroup();
//
//    public void start(Stage stage) {
//
//        VBox root = new VBox(5);
//        root.setPadding(new Insets(20));
//
//        root.getChildren().add(createToggle("Option 1"));
//        root.getChildren().add(createToggle("Option 2"));
//
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private ButtonBase createToggle(String name) {
//        RadioButton toggle = new RadioButton(name);
//        toggle.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
//            if (toggle.isSelected()) {
//                toggles.selectToggle(null);
//                e.consume();
//            }
//        });
//        toggle.setToggleGroup(toggles);
//        return toggle;
//    }

    //database
    MongoCollection<Document> generatorsCollection;

    @FXML
    public void initialize(){
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");

        // get collection
        generatorsCollection = database.getCollection("GeneMaintenance");
    }


    MongoDatabase database;
    

    @FXML
    void GviewMainteD(ActionEvent event) {
        Veiw_maintenance_details.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneViewMaintenenceDetails.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Gene.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void back_to_b(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ViewGenerator.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/main.css");
        scene.getStylesheets().add("stylesheet/login.css");
        scene.getStylesheets().add("stylesheet/GeneratorA.css");
        scene.getStylesheets().add("stylesheet/GeneratorB.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    void move_to_d(ActionEvent event) {
        try {
            //Get the values from the UI
            //Enter the id
            String GeneratorIDIDText = GeneratorID.getText();
            String MaintenanceNOText = MaintenanceNO.getText();
            LocalDate MaintenanceDateText = MaintenanceDate.getValue();
            //Callback<DatePicker, DateCell> MaintenanceDateText = MaintenanceDate.getDayCellFactory();
            String serviseText = servise.getText();
            String repairText = repair.getText();
            insertGeneratorMaintenance(generatorsCollection, GeneratorIDIDText, MaintenanceNOText, MaintenanceDateText, serviseText, repairText);

            System.out.println(MaintenanceDateText);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        //Direct to next page
        Next.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneMaintenanceItems.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet/main.css");
        scene.getStylesheets().add("stylesheet/login.css");
        scene.getStylesheets().add("stylesheet/GeneratorA.css");
        scene.getStylesheets().add("stylesheet/GeneratorB.css");
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }
    public void insertGeneratorMaintenance(MongoCollection<Document> generatorsCollection, String GeneratorIDIDText, String MaintenanceNOText, java.time.LocalDate MaintenanceDateText, String serviseText, String PriseText) {
        Document generator = new Document("_id", new ObjectId()).append("Generator ID", GeneratorIDIDText)
                .append("MaintenanceNO", MaintenanceNOText)
                .append("MaintenanceDateText", MaintenanceDateText)
                .append("Type", serviseText)
                .append("Type", PriseText);
        generatorsCollection.insertOne(generator);
        System.out.println("Connection S3");
    }

}

