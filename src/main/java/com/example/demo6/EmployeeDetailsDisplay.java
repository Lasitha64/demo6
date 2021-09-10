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

public class EmployeeDetailsDisplay {

    @FXML
    private Button searchamotheremployeebutton;

    @FXML
    private Button updateemployeebutton;

    @FXML
    private Button deleteemployeebutton;

    @FXML
    private TextField namedisplay;

    @FXML
    private TextField usernamedisplay;

    @FXML
    private TextField emaildisplay;

    @FXML
    private TextField nicdisplay;

    @FXML
    private Button backbutton;

    @FXML
    private Button mainmenu;

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteemployee(ActionEvent event) {

    }

    @FXML
    void mainmenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void searchanotheremployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void updateemployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeUpdate.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeUpdate.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

}

