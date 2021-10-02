package com.example.demo6;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemMain implements Initializable {



    @FXML
    private TextField search_fld;

    @FXML
    private Button buttn_search;

    @FXML
    private TableView<Item> StockItem;

    @FXML
    private TableColumn<Item, String> item_id;

    @FXML
    private TableColumn<Item, String> item_name;

    @FXML
    private TableColumn<Item, String> item_qunt;

    @FXML
    private Button buttn_remove;

    @FXML
    private Button buttn_dlt;

    @FXML
    private Button buttn_add;

    @FXML
    private Button buutn_back;

    public ObservableList<Item> list;

    @FXML
    void Back(ActionEvent event) {

    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showItem();
    }

    public void showItem() {

        ObservableList<Item> list = getCrusherList();

        cid.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        cname.setCellValueFactory(new PropertyValueFactory<Crusher, String>("Name"));
        cquan.setCellValueFactory(new PropertyValueFactory<Crusher, String>("Quantity"));
        cprice.setCellValueFactory(new PropertyValueFactory<Crusher, String>("Price"));
        cdate.setCellValueFactory(new PropertyValueFactory<Crusher, String>("Date"));

        CrusherParts.setItems(list);

    }

}
