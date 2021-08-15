package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileIO {
    File file;
    private final Map<String, String> jsonObject;

    public FileIO(String fileName, Map<String, String> jsonObject) {
        //System.getProperty("user.dir") + "/src/server/data/db.json"
        String path = System.getProperty("user.dir") + "/src/server/data/";
        //String path = "JSON Database/task/src/server/data/";
        String pathToFile = path + fileName;
        this.file = new File(pathToFile).getAbsoluteFile();
        this.jsonObject = jsonObject;
    }

    public void writeJSONToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = new FileWriter(file.getAbsolutePath());
            gson.toJson(this.jsonObject, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> readJSONFromFile() {

        // create Gson instance
        Gson gson = new Gson();

        // create a reader
        try (Reader reader = Files.newBufferedReader(Path.of(file.getAbsolutePath()))) {

            // convert JSON file to map
            return gson.fromJson(reader, HashMap.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}