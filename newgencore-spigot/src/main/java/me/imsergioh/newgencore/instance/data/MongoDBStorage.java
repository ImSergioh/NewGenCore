package me.imsergioh.newgencore.instance.data;

import com.mongodb.MongoClient;
import me.imsergioh.newgencore.NewGenCore;
import org.bson.Document;

public class MongoDBStorage implements DataStorage {

    private final String database;
    private final String collection;
    private final Document findQuery;

    private Document document;

    public MongoDBStorage(String database, String collection, Document findQuery) {
        this.database = database;
        this.collection = collection;
        this.findQuery = findQuery;
    }

    @Override
    public void save(LocalData data) {
        delete();
        NewGenCore.getMongoDBConnection().getMongoClient()
                .getDatabase(database)
                .getCollection(collection)
                .insertOne(data);
    }

    @Override
    public LocalData load() {
        Document document = NewGenCore.getMongoDBConnection().getMongoClient()
                .getDatabase(database)
                .getCollection(collection)
                .find(findQuery).first();
        if (document == null) document = findQuery;
        return (LocalData) document;
    }

    @Override
    public void delete() {
        NewGenCore.getMongoDBConnection().getMongoClient()
                .getDatabase(database)
                .getCollection(collection)
                .deleteOne(findQuery);
    }
}
