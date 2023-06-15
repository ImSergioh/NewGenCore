package me.imsergioh.newgencore.instance.data;

public interface DataStorage {

    void save(LocalData data);
    LocalData load();
    void delete();

}
