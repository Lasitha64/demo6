package com.example.demo6;

import com.mongodb.client.MongoCollection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.bson.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainView {

    public Label lblTime;
    public Label lblDate;
    private Stage stage;
    private Scene scene;


    //start of attributes in main-view fxml



    @FXML
    private Button btn_ve;

    @FXML
    private Button btn_cr;

    @FXML
    private Button btn_hm;

    @FXML
    private Button btn_ot;

    @FXML
    private Button btn_lo;

    @FXML
    private Button btn_ex;

    @FXML
    private Button btn_sl;

    @FXML
    private Button btn_ad;



    //end of attributes in main-view xml


    //start -> functions use in main-view fxml

    public void initialize(){
        lblDate.setText(new SimpleDateFormat("EEEE, MMMM dd yyyy").format(new Date()));
        Timeline time=new Timeline(new KeyFrame(Duration.ZERO, e -> {
                LocalTime currentTime=LocalTime.now();
                lblTime.setText(currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));//ISO_LOCAL_TIME.substring(0,8)
            }),
            new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    @FXML
    void admin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/adminDetails.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void crusheraction(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("crusher-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/crusher.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void excavatoraction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("viewVmain.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void loaderaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Loader-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }



    @FXML
    void otheraction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stcock-mngt.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-mngt.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void genaratoraction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GeneratorMainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("stylesheet/login.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("stylesheet/GeneratorA.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("stylesheet/GeneratorB.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void employeeaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeDetails.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void vechicleaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("vehicle.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Vehicle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void hammeraction(ActionEvent event) {

    }
    //end -> functions use in main-view fxml
}
