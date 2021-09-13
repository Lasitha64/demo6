package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class StockReport {

    @FXML
    private DatePicker datefld;

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> item_id;

    @FXML
    private TableColumn<?, ?> item_name;

    @FXML
    private TableColumn<?, ?> item_qunt;

    @FXML
    private TableColumn<?, ?> Item_price;

    @FXML
    private Button btn_daily;

    @FXML
    private Button btn_month;

    @FXML
    private Button buttn_back;

    @FXML
    void Add(ActionEvent event) {

    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stcock-mngt.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/stock-mngt.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Generate(ActionEvent event) {

    }

}
