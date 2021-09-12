//page d

package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

}

