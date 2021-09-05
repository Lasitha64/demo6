package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneratorAddView {
    @FXML
    private Button btn_save;

    @FXML
    void generator(ActionEvent event) throws IOException {

        Stage new_generator_form = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("generator_add_view.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("stylesheet/generator_view.css").toExternalForm());
        new_generator_form.setScene(scene);
        new_generator_form.show();
        new_generator_form.setResizable(false);
    }
}
