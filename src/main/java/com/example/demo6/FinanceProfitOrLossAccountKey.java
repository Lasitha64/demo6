//page s

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceProfitOrLossAccountKey {

    private AlertBox ab;

    @FXML
    private Button btn_back;

    @FXML
    private Button enter;

    @FXML
    private TextField Manager_key;

    @FXML
    void enter(ActionEvent event) {
        double Manager_keyText = Double.parseDouble(Manager_key.getText());

        if (Manager_keyText == 123.456){

            enter.getScene().getWindow().hide();
            Stage signup = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FinanceProfitOrLossAccountDeleteUpdate.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
            signup.setScene(scene);
            signup.show();
            signup.setResizable(false);
        }

        else {
            ab.display("OK", "Wrong pin");

            System.out.println("Wrong pin");

            btn_back.getScene().getWindow().hide();
            Stage signup = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FinanceMain.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
            signup.setScene(scene);
            signup.show();
            signup.setResizable(false);
        }

    }

    @FXML
    void move_to_a(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FinanceMain.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/Finance.css").toExternalForm());
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

}
