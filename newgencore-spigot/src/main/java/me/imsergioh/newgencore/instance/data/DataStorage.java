package me.imsergioh.newgencore.instance.data;

public interface DataStorage {

    void save(LocalData data, Object queryObject);
    LocalData load(Object queryObject);
    void delete(Object queryObject);

}
