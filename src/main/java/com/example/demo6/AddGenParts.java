//page f

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddGenParts {

    @FXML
    private TextField GPStock_ID;

    @FXML
    private TextField G_Stock_ID;

    @FXML
    private DatePicker Date_of_purchase;

    @FXML
    private TextField GSQuantity;

    @FXML
    private TextField UnitPrice;

    @FXML
    private Button Back;

    @FXML
    private Button Add;

    @FXML
    void AddToDB(ActionEvent event) {

    }

    @FXML
    void back_to_e(ActionEvent event) {
        Back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneratorParts.fxml"));
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
