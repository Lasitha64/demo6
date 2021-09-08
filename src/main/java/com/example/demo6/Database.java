package com.example.demo6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Database {
    public static MongoClient connect(){
        String connectionString = "mongodb+srv://DBkisaja:MC123456@cluster0.xi6ys.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
           return mongoClient;
        }
    }

}
