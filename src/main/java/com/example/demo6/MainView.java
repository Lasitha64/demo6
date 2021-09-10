package com.example.demo6;

import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainView {

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
    @FXML
    void adminaction(ActionEvent event) throws IOException {


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
    void excavatoraction(ActionEvent event) {

    }

    @FXML
    void hammeraction(ActionEvent event) {

    }

    @FXML
    void loaderaction(ActionEvent event) {

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
    void salesaction(ActionEvent event) {

    }

    @FXML
    void vechicleaction(ActionEvent event) {

    }
    //end -> functions use in main-view fxml
}
