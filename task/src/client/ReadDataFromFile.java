package client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadDataFromFile {
    File file;
    private String data;

    public ReadDataFromFile(String fileName) {
        //String path = "JSON Database/task/src/client/data/";
        String path = System.getProperty("user.dir") + "/src/client/data/";
        String pathToFile = path + fileName;
        file = new File(pathToFile).getAbsoluteFile();
    }

    public void readDataToString() {
        try {
            this.data = Files.readString(Path.of(file.getAbsolutePath()));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getData() {
        return this.data;
    }
}
