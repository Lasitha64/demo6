package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

    //start of attributes in crusher-view fxml
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_gen;

    @FXML
    private Button btn_sea;
    //end of attributes in crusher-view xml

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
    private TextField tx_date;

    @FXML
    private Button btn_add_parts;
    //end of attributes in crusher-parts-add xml


    // start attributes use in multiple views
    @FXML
    private Button btn_back;
    // end attributes use in multiple views

    //start -> functions use in crusher-parts fxml
    @FXML
    void AddParts(ActionEvent event) {

    }
    //end -> functions use in crusher-parts fxml



    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    //start -> functions use in crusher-view fxml
    @FXML
    void Add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("crusher-parts-add.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/crusher-add.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Generate(ActionEvent event) {

    }

    @FXML
    void Search(ActionEvent event) {

    }
    //end -> functions use in crusher-view fxml

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
    void otheraction(ActionEvent event) {

    }

    @FXML
    void salesaction(ActionEvent event) {

    }

    @FXML
    void vechicleaction(ActionEvent event) {

    }
    //end -> functions use in main-view fxml
}
