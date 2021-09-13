//page d

/*
@author..Lasitha
@edited..Kisaja
 */

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneMaintenanceItems {

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> cid;

    @FXML
    private TableColumn<?, ?> cname;

    @FXML
    private TableColumn<?, ?> cquan;

    @FXML
    private TableColumn<?, ?> cprice;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_quan;

    @FXML
    private TextField tf_price;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    @FXML
    void Back_to_c(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GeneMaintainance.fxml"));
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
    void Delete(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

}


