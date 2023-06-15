package me.imsergioh.newgencore.instance.data;

import com.mongodb.MongoClient;
import me.imsergioh.newgencore.NewGenCore;
import org.bson.Document;

import javax.print.Doc;

public class MongoDBStorage implements DataStorage {

    private final String database;
    private final String collection;

    public MongoDBStorage(String database, String collection) {
        this.database = database;
        this.collection = collection;
    }

    @Override
    public void save(LocalData data, Object queryObject) {
        delete(queryObject);
        NewGenCore.getMongoDBConnection().getMongoClient()
                .getDatabase(database)
                .getCollection(collection)
                .insertOne(data);
    }

    @Override
    public LocalData load(Object queryObject) {
        Document findQuery = (Document) queryObject;
        Document document = NewGenCore.getMongoDBConnection().getMongoClient()
                .getDatabase(database)
                .getCollection(collection)
                .find(findQuery).first();
        if (document == null) document = findQuery;
        return (LocalData) document;
    }

    @Override
    public void delete(Object queryObject) {
        Document findQuery = (Document) queryObject;
        NewGenCore.getMongoDBConnection().getMongoClient()
                .getDatabase(database)
                .getCollection(collection)
                .deleteOne(findQuery);
    }
}
