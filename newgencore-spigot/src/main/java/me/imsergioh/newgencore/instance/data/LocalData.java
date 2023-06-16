package me.imsergioh.newgencore.instance.data;

import org.bson.Document;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalData extends Document {

    public LocalData(Document document) {
        super(document);
    }

    public LocalData(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            append(key, jsonObject.get(key));
        }
    }

    public LocalData(File jsonFile) {
        this(readFileToJSONObject(jsonFile));
    }

    private static JSONObject readFileToJSONObject(File jsonFile) {
        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(jsonFile.getAbsolutePath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new JSONObject(jsonContent);
    }

}
