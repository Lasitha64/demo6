package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
//        //String connectionString = System.getProperty("mongodb.uri");
//        String connectionString = "mongodb+srv://DBkisaja:MC123456@cluster0.xi6ys.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
//        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
//            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
//            databases.forEach(db -> System.out.println(db.toJson()));
//        }

        launch();
    }
}