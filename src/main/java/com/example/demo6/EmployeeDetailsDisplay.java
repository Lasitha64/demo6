package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDetailsDisplay implements Initializable {
    private AlertBox ab;
    private String eName;
    private String eAddress;
    private String eNIC;
    private String eMobile;

    @FXML
    private Button searchamotheremployeebutton;

    @FXML
    private Button updateemployeebutton;

    @FXML
    private Button deleteemployeebutton;

    @FXML
    private TextField employeeinputname;

    @FXML
    private TextField employeeinputaddress;

    @FXML
    private TextField nicdisplay;

    @FXML
    private TextField employeeinputmobile;

    @FXML
    private Button backbutton;

    @FXML
    private Button mainmenu;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> employeeName;

    @FXML
    private TableColumn<Employee, String> employeeAddress;

    @FXML
    private TableColumn<Employee, String> employeeNIC;

    @FXML
    private TableColumn<Employee, String> employeeMobile;

    @FXML
    private TextField searchField;

    private MongoClient database;

    MongoCollection<Document> employeeCollection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmployee();
        searchEmployee();

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteemployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeDetailsDisplay.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        String nicdisplayText = nicdisplay.getText();
        Bson filter = eq("NIC No", nicdisplayText);
        DeleteResult result = employeeCollection.deleteOne(filter);
        System.out.println(result);
        ab.display("OK", "Employee Deleted Successfully");


    }

    @FXML
    void mainmenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void searchanotheremployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void updateemployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employeeDetailsDisplay.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet/employeeSearch.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        if (employeeinputname.getText().isEmpty() || employeeinputaddress.getText().isEmpty() || nicdisplay.getText().isEmpty() || employeeinputmobile.getText().isEmpty()) {
            ab.display("Error", " Input Fields can't be empty");
        } else if (!employeeinputmobile.getText().matches("[0-9]+")) {
            ab.display("Error", "Mobile No needs to be a number");
        } else {

            //updateemployeebutton

            // update one document
            String employeeinputnameText = employeeinputname.getText(), employeeinputaddressText = employeeinputaddress.getText(), nicdisplayText = nicdisplay.getText(), employeeinputmobileText = employeeinputmobile.getText();

            System.out.println(employeeinputnameText + employeeinputaddressText + nicdisplayText + employeeinputmobileText);


            Bson filter = eq("NIC No", nicdisplayText);


            Bson updateName = set("Name", employeeinputnameText); // creating an array with a comment.
            Bson updateAddress = set("Address", employeeinputaddressText); // using addToSet so no effect.
            Bson updateMobile = set("Mobile No", employeeinputmobileText); // using addToSet so no effect.

            List<Bson> updatePredicates = new ArrayList<Bson>();
            updatePredicates.add(updateName);
            updatePredicates.add(updateAddress);
            updatePredicates.add(updateMobile);


            //Bson updateOperation = set("Name", NameText);
        /*.append("Name", NameText)
                .append("Quantity", QuantityText)
                .append("Prise", PriseText);*/
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = employeeCollection.findOneAndUpdate(filter, Updates.combine(updatePredicates));

            System.out.println("Updating Employee Details");
            System.out.println(newVersion);
            ab.display("OK", "Employee Updated Successfully");


        }
    }

    @FXML
    void searchEmployee() {
        ObservableList<Employee> searchlist = getCrusherList();


        employeeNIC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployeeNIC()));
        employeeAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeAddress"));
        employeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
        employeeMobile.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeMobile"));
        employeeTable.setItems(searchlist);

        FilteredList<Employee> filterdata = new FilteredList<>(searchlist, b->true);
        searchField.textProperty().addListener((observable, oldValue, newValue)->{
            filterdata.setPredicate(employee -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(employee.getEmployeeNIC().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else{
                    return false;
                }
            });
        });
        SortedList<Employee> sortedData = new SortedList<>(filterdata);
        sortedData.comparatorProperty().bind(employeeTable.comparatorProperty());
        employeeTable.setItems(sortedData);
    }





    private void showEmployee() {
        ObservableList<Employee> list = getCrusherList();
        employeeNIC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployeeNIC()));
        employeeAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeAddress"));
        employeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
        employeeMobile.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeMobile"));
        employeeTable.setItems(list);
    }

    private ObservableList<Employee> getCrusherList() {
        ObservableList<Employee> attend = FXCollections.observableArrayList();
        //initialize database connection
        Database databaseController = new Database();
        MongoDatabase database = databaseController.connectToDB("HerathCMD");
        // get collection
        employeeCollection = database.getCollection("Employee");
        MongoCursor<Document> cursor = employeeCollection.find().iterator();
        try {
            for (int i = 0; i < employeeCollection.count(); i++) {

                Document doc = cursor.next();
                eName = doc.getString("Name");
                //  System.out.println(eName);
                eAddress = doc.getString("Address");
                eNIC = doc.getString("NIC No");
                eMobile = doc.getString("Mobile No");
                attend.add(new Employee(eName, eAddress, eNIC, eMobile));
            }
            //  list = FXCollections.observableArrayList(attend);
        } finally {
//          close the connection
            cursor.close();
        }
        return  attend;
//      call the setTable method
    }
}

