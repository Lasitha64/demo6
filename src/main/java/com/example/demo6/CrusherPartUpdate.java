package com.example.demo6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CrusherPartUpdate implements Initializable{

    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<Crusher> CrusherParts;

    @FXML
    private TableColumn<Crusher, String> cid;

    @FXML
    private TableColumn<Crusher, String> cname;

    @FXML
    private TableColumn<Crusher, Integer> cquan;

    @FXML
    private TableColumn<Crusher, Double> cprice;

    @FXML
    private TableColumn<Crusher, String> cdate;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_quan;

    @FXML
    private TextField tf_price;

    @FXML
    private TextField tf_date1;

    @FXML
    private DatePicker tf_date;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_back;

    private AlertBox ab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCrusher();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crusherdb","root","test");
            return conn;

        }catch(Exception ex){
            System.out.println("Error :"+ex.getMessage());
            return null;
        }
    }

    private void executeQuery(String query){
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }


    }

    public ObservableList<Crusher> getCrusherList(){
        ObservableList<Crusher> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from parts";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Crusher cru;
            while(rs.next()){
                cru = new Crusher(rs.getString("ID"),rs.getString("Name"),rs.getInt("Quantity"),rs.getDouble("Price"),rs.getString("Date"));
                bookList.add(cru);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }

    public void showCrusher(){
        ObservableList<Crusher>  list = getCrusherList();

        cid.setCellValueFactory(new PropertyValueFactory<Crusher, String>("id"));
        cname.setCellValueFactory(new PropertyValueFactory<Crusher, String>("name"));
        cquan.setCellValueFactory(new PropertyValueFactory<Crusher, Integer>("quantity"));
        cprice.setCellValueFactory(new PropertyValueFactory<Crusher, Double>("price"));
        cdate.setCellValueFactory(new PropertyValueFactory<Crusher, String>("date"));

        CrusherParts.setItems(list);

    }

    @FXML
    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("crusher-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/crusher.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Delete(ActionEvent event) {
        String query = "DELETE FROM parts WHERE id ='" + tf_id.getText() + "'";
        executeQuery(query);
        showCrusher();
    }

    @FXML
    void Update(ActionEvent event) {
        if(tf_id.getText().isEmpty() || tf_name.getText().isEmpty() || tf_quan.getText().isEmpty() || tf_price.getText().isEmpty() || tf_date1.getText().isEmpty()){
            ab.display("Error"," Input Fields can't be empty");
        }
        else if(!tf_quan.getText().matches("[0-9]+")){
            ab.display("Error","Quantity needs to be a number");
        }
        else if(!tf_price.getText().matches("[0-9]+(\\.){0,1}[0-9]*")){
            ab.display("Error","Price needs to be a double (ex: 200.90)");
        }
        else{
            String query = "UPDATE parts SET Name = '"+tf_name.getText()+"' ,Quantity = "+tf_quan.getText()+",Price= "+tf_price.getText()+" ,Date= '"+tf_date1.getText()+"' WHERE ID ='" + tf_id.getText() + "'";
            executeQuery(query);
            showCrusher();
        }

    }
}
