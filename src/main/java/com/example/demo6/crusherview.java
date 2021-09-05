package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.example.demo6.MainView;
import java.io.IOException;

public class crusherview {

    private MainView main;
    private Stage stage;
    private Scene scene;

    //start of attributes in crusher-view fxml
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_gen;

    @FXML
    private Button btn_sea;
    //end of attributes in crusher-view xml

    // start attributes use in multiple views
    @FXML
    private Button btn_back;
    // end attributes use in multiple views

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

    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    //end -> functions use in crusher-view fxml

}
