package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {

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

    @FXML
    void adminaction(ActionEvent event) throws IOException {
//        btn_cr.getScene().getWindow().hide();
//        Stage signup = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("stylesheet/main.css");
//        signup.setScene(scene);
//        signup.show();
//        signup.setResizable(false);

    }

    @FXML
    void crusheraction(ActionEvent event) {

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
    void vechicleaction(ActionEvent event) {

    }

    public void genaratoraction(ActionEvent actionEvent) {
        btn_cr.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneratorMainPage.fxml"));
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
}
