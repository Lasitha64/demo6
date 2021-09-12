package com.example.demo6;

import com.mongodb.client.*;
import com.mongodb.client.model.InsertManyOptions;

import java.util.LinkedList;
import java.util.List;


public class Database {

    MongoClient mongoClient;

    public Database()
    {
        this.mongoClient = MongoClients.create("mongodb+srv://developer:Developer123@cluster0.ro9s7.mongodb.net/test");
    }

    public MongoDatabase connectToDB(String dbName)
    {
        return mongoClient.getDatabase(dbName);
    }

    public List<String> listAllDB()
    {
        List<String> ret = new LinkedList<>();
        MongoIterable<String> x = mongoClient.listDatabaseNames();
        for(String t : x)
        {
            ret.add(t.toString());
        }
        return ret;
    }
}
