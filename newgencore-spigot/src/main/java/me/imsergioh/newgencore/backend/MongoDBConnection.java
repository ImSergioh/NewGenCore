package me.imsergioh.newgencore.backend;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;

public class MongoDBConnection {

    @Getter
    private final MongoClient mongoClient;

    public MongoDBConnection(String uriString) {
        ConnectionString connectionString = new ConnectionString(uriString);
        mongoClient = MongoClients.create(connectionString);
    }

}
