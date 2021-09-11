package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Database {
    public static MongoDatabase connect(){
        String connectionString = "mongodb+srv://developer:Developer123@cluster0.ro9s7.mongodb.net/test";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("HerathCMD");
            return database;
        }
    }

}
