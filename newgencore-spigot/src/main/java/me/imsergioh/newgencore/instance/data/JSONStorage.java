package me.imsergioh.newgencore.instance.data;

import me.imsergioh.newgencore.util.ExceptionsUtil;
import org.bson.BsonBinaryWriter;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONStorage implements DataStorage {

    private final File file;

    public JSONStorage(File file) {
        this.file = file;
    }

    @Override
    public void save(LocalData data) {
        BsonDocument bson = BsonDocument.parse(data.toJson());

        BasicOutputBuffer outputBuffer = new BasicOutputBuffer();
        BsonBinaryWriter writer = new BsonBinaryWriter(outputBuffer);
        new BsonDocumentCodec().encode(writer, bson, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        byte[] byteArr = outputBuffer.toByteArray();
        writeToFile(byteArr);
    }

    @Override
    public LocalData load() {
        String fileContent = null;
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
        return (LocalData) Document.parse(fileContent);
    }

    @Override
    public void delete() {
        file.delete();
    }

    private void writeToFile(byte[] byteArray) {
        try (FileOutputStream fos = new FileOutputStream(file.getAbsolutePath())) {
            fos.write(byteArray);
        } catch (IOException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }
}
