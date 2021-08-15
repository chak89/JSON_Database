package server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Database {
    Map<String, String> jsonStorage;
    JsonObjectResponse jsonObjectResponse;
    private String databaseFilename;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Map<String, String> getJsonStorage() {
        return jsonStorage;
    }

    public Database() {
        this.databaseFilename = "db.json";
        this.jsonStorage = new HashMap<>();
    }

    public JsonObjectResponse getJsonObjectResponse() {
        return jsonObjectResponse;
    }

    public void setCell(String key, String value) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();

        try {
            fetchFromDatabase();
            this.jsonStorage.put(key, value);
            saveToDatabase();
            this.jsonObjectResponse = new JsonObjectResponse("OK", null, null);
        } finally {
            writeLock.unlock();
        }

    }


    public void getCell(String key) {

        Lock readLock = lock.readLock();
        readLock.lock();

        try {
            fetchFromDatabase();
            if (this.jsonStorage.containsKey(key)) {
                this.jsonObjectResponse = new JsonObjectResponse("OK", null, this.jsonStorage.get(key));
            } else {
                this.jsonObjectResponse = new JsonObjectResponse("ERROR", "No such key", null);
            }
        } finally {
            readLock.unlock();
        }


    }

    public void delCell(String key) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();

        try {
            fetchFromDatabase();
            if (this.jsonStorage.containsKey(key)) {
                this.jsonStorage.remove(key);
                saveToDatabase();
                this.jsonObjectResponse = new JsonObjectResponse("OK", null, null);
            } else {
                this.jsonObjectResponse = new JsonObjectResponse("ERROR", "No such key", null);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public void exit(){
        this.jsonObjectResponse = new JsonObjectResponse("OK", null, null);
    }

    public void fetchFromDatabase() {
        FileIO fileToWrite = new FileIO(this.databaseFilename,null);
        this.jsonStorage = fileToWrite.readJSONFromFile();
    }

    public void saveToDatabase() {
        FileIO fileToRead = new FileIO(this.databaseFilename,this.jsonStorage);
        fileToRead.writeJSONToFile();
    }
}
