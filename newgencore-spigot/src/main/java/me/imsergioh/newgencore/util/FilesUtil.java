package me.imsergioh.newgencore.util;

import java.io.File;
import java.io.IOException;

public class FilesUtil {

    public static void createParents(File file) {
        createDirectories(file.getParentFile());
    }

    public static void createDirectories(File directoryFile) {
        if (directoryFile.exists()) return;
        directoryFile.mkdirs();
    }

    public static void createFileAndParents(File file) {
        createParents(file);
        if (file.exists()) return;
        try {
            file.createNewFile();
        } catch (IOException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }

}
