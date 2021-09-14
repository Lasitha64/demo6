package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class LoaderMain {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button btn_add1;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_gen;

    @FXML
    private Button btn_sea;

    @FXML
    private Button btn_back;

    @FXML
    void Back(ActionEvent event) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
            stage.setScene(scene);
            stage.show();



    }

    @FXML
    void e(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Loaderexpenses.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void ed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoaderDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void rd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Loader-Repair.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void sd(ActionEvent event) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("Loader-service.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

    }

}


