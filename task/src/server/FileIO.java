package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileIO {
    File file;
    private final JsonObject jsonObject;

    public FileIO(String fileName, JsonObject jsonObject) {
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

    public JsonObject readJSONFromFile() {

        // create Gson instance
        Gson gson = new Gson();

        // create a reader
        try (Reader reader = Files.newBufferedReader(Path.of(file.getAbsolutePath()))) {

            // convert JSON file to map
            return gson.fromJson(reader, JsonObject.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}