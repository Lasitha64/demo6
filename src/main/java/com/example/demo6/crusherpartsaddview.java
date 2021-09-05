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

public class crusherpartsaddview {

    private Stage stage;
    private Scene scene;

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

    //start -> functions use in crusher-parts fxml
    @FXML
    void AddParts(ActionEvent event) {


    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
    //end -> functions use in crusher-parts fxml
}
