package com.codenation.java.pdg.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferooz on 13/02/17.
 */
public class FileUtil {
    public static List<File> getAllFilesInDirectory(String directory, String[] extensions) throws IOException {
        File file = new File(directory);
        if (file.exists() && file.isDirectory()) {
            return (List<File>) FileUtils.listFiles(file, extensions, true);
        }
        return new ArrayList<>();
    }

    public static String getFileContents(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return FileUtils.readFileToString(file, Charset.defaultCharset());
        }
        return null;
    }
}
