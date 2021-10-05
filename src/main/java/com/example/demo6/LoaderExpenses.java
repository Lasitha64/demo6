package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class LoaderExpenses {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<?> CrusherParts;

    @FXML
    private TableColumn<?, ?> cid;

    @FXML
    private TableColumn<?, ?> cname;

    @FXML
    private TableColumn<?, ?> cquan;

    @FXML
    private Button btn_add1;

    @FXML
    private Button btn_add11;

    @FXML
    private Button btn_add111;

    @FXML
    private Button btn_back;

    @FXML
    void Add(ActionEvent event) {
        File myFile = new File("C:\\Users\\MSI\\IdeaProjects\\demo6\\src\\main\\resources\\com\\example\\demo6\\Jasper\\Invoice_1.jasper");
        Desktop.getDesktop().open(myFile);
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Utility-main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Excav-main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}