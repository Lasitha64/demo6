package com.example.demo6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//mongo db conection
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("stylesheet/login.css").toExternalForm());
        stage.setTitle("Herath Metal Crusher");
        stage.setScene(scene);
        stage.show();
    }


    //mongo db conection from the Database class singleton method
    public static void main(String[] args) {
//        try (MongoClient mongoClient = Database.connect()) {
//           /* List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
//            databases.forEach(db -> System.out.println(db.toJson()));*/
//        }

        launch();
    }
}