package server;

import com.google.gson.*;
import com.google.gson.JsonObject;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Database {
    private JsonObject jsonObjectDatabase;
    private ResponseJsonObject responseJsonObject;
    private final String databaseFilename;
    private final Lock readLock;
    private final Lock writeLock;

    public Database() {
        this.databaseFilename = "db.json";
        this.jsonObjectDatabase = new JsonObject();

        ReadWriteLock lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();
    }

    public ResponseJsonObject getJsonObjectResponse() {
        return responseJsonObject;
    }

    public void setCell(JsonElement key, JsonElement value) {
        writeLock.lock();

        try {
            fetchFromDatabase();

            if (key.isJsonPrimitive()) {
                this.jsonObjectDatabase.add(key.getAsString(), value);
            } else if (key.isJsonArray()) {
                JsonArray keys = key.getAsJsonArray();
                JsonElement tempDbs = this.jsonObjectDatabase;

                //Remove keys array by 1. So that the loop stop on the second last index.
                String addKey = keys.remove(keys.size() - 1).getAsString();

                for (JsonElement elemKey : keys) {
                    try {
                        if (!tempDbs.getAsJsonObject().has(elemKey.getAsString())) {
                            tempDbs.getAsJsonObject().add(elemKey.getAsString(), new JsonObject());
                        }
                        tempDbs = tempDbs.getAsJsonObject().get(elemKey.getAsString());


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                tempDbs.getAsJsonObject().add(addKey, value);
            }
            this.responseJsonObject = new ResponseJsonObject("OK", null, null);
            saveToDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public void getCell(JsonElement key) {
        readLock.lock();

        try {
            fetchFromDatabase();

            //Check if the key is of primitive type and that the key exist in our database
            if (key.isJsonPrimitive()) {
                if (this.jsonObjectDatabase.has(key.getAsString())) {
                    this.responseJsonObject = new ResponseJsonObject("OK", null, this.jsonObjectDatabase.get(key.getAsString()));
                } else {
                    this.responseJsonObject = new ResponseJsonObject("ERROR", "No such key", null);
                }
            } else if (key.isJsonArray()) {
                JsonElement elem = fetchElement(key.getAsJsonArray());

                if (elem == null) {
                    this.responseJsonObject = new ResponseJsonObject("ERROR", "No such key", null);
                } else {
                    this.responseJsonObject = new ResponseJsonObject("OK", null, elem);
                }
            }
        } finally {
            readLock.unlock();
        }
    }


    public void delCell(JsonElement key) {
        writeLock.lock();
        try {
            fetchFromDatabase();

            if (key.isJsonPrimitive()) {
                this.jsonObjectDatabase.remove(key.getAsString());
                this.responseJsonObject = new ResponseJsonObject("OK", null, null);
            } else if (key.isJsonArray()) {
                JsonArray keys = key.getAsJsonArray();
                JsonElement tempDbs = this.jsonObjectDatabase;

                //Remove keys array by 1. So that the loop stop on the second last index.
                String addKey = keys.remove(keys.size() - 1).getAsString();

                for (JsonElement elemKey : keys) {
                    try {
                        if (!tempDbs.getAsJsonObject().has(elemKey.getAsString())) {
                            this.responseJsonObject = new ResponseJsonObject("ERROR", "No such key", null);
                            return;
                        }

                        tempDbs = tempDbs.getAsJsonObject().get(elemKey.getAsString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (!tempDbs.getAsJsonObject().has(addKey)) {
                    this.responseJsonObject = new ResponseJsonObject("ERROR", "No such key", null);
                    return;
                }

                tempDbs.getAsJsonObject().remove(addKey);
            }

            saveToDatabase();
            this.responseJsonObject = new ResponseJsonObject("OK", null, null);
        } finally {
            writeLock.unlock();
        }
    }

    public JsonElement fetchElement(JsonArray keys) {
        JsonElement tempDbs = this.jsonObjectDatabase;

        for (JsonElement key : keys) {
            try {
                if (tempDbs.getAsJsonObject().has(key.getAsString())) {
                    tempDbs = tempDbs.getAsJsonObject().get(key.getAsString());
                } else {
                    tempDbs = null;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tempDbs;
    }

    public void fetchFromDatabase() {
        FileIO fileToRead = new FileIO(this.databaseFilename, null);
        this.jsonObjectDatabase = fileToRead.readJSONFromFile();
    }

    public void saveToDatabase() {
        FileIO fileToWrite = new FileIO(this.databaseFilename, this.jsonObjectDatabase);
        fileToWrite.writeJSONToFile();
    }

    public void exit() {
        this.responseJsonObject = new ResponseJsonObject("OK", null, null);
    }
}
