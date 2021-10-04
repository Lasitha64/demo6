package com.example.demo6;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class crusherview  {

    private MainView main;
    private Stage stage;
    private Scene scene;
    private HashMap<String , Object> map;
    Database con;

    //start of attributes in crusher-view fxml
    @FXML
    private AnchorPane crusherviewfrag;

    @FXML
    private Button btnvechicle;

    @FXML
    private Button btncrusher;

    @FXML
    private Button btnloader;

    @FXML
    private Button btnexcvator;

    @FXML
    private Button btnother;

    @FXML
    private Button btngen;

    @FXML
    private Button btnemp;

    @FXML
    private Button btnadmin;

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
    void Generate(ActionEvent event)  {
        try {
//            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\MSI\\IdeaProjects\\demo6\\src\\main\\resources\\com\\example\\demo6\\Jasper\\Invoice_1.jrxml");
//            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
//            JasperPrint jprint = JasperFillManager.fillReport(jreport,map, (JRDataSource) con);
//            JRViewer viewer = new JRViewer(jprint);
            File myFile = new File("C:\\Users\\MSI\\IdeaProjects\\demo6\\src\\main\\resources\\com\\example\\demo6\\Jasper\\Invoice_1.jasper");
            Desktop.getDesktop().open(myFile);
        } catch (  IOException ex) {
            // no application registered for PDFs
        }

      //  Report.createReport( con,map);

    }

    @FXML
    void Search(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("crusher-part-update.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/update.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void adminaction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("adminDetails.fxml"));
        crusherviewfrag.getChildren().setAll(anchorPane);
        anchorPane.getStylesheets().add(getClass().getResource("stylesheet/adminDetails.css").toExternalForm());

    }



    @FXML
    void empaction(ActionEvent event) {

    }

    @FXML
    void excavatoraction(ActionEvent event) {

    }

    @FXML
    void genaction(ActionEvent event) {

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


    //end -> functions use in crusher-view fxml

}
