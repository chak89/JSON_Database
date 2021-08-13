package server;

import java.util.HashMap;
import java.util.Map;


public class Database {
    Map<String, String> jsonStorage;
    JsonObjectResponse jsonObjectResponse;

    public Database() {
        this.jsonStorage = new HashMap<>();
    }

    public JsonObjectResponse getJsonObjectResponse() {
        return jsonObjectResponse;
    }

    public void setCell(String key, String value) {
        this.jsonStorage.put(key, value);
        this.jsonObjectResponse = new JsonObjectResponse("OK", null, null);
    }


    public void getCell(String key) {
        if (this.jsonStorage.containsKey(key)) {
            this.jsonObjectResponse = new JsonObjectResponse("OK", null, this.jsonStorage.get(key));
        } else {
            this.jsonObjectResponse = new JsonObjectResponse("ERROR", "No such key", null);
        }
    }

    public void delCell(String key) {
        if (this.jsonStorage.containsKey(key)) {
            this.jsonStorage.remove(key);
            this.jsonObjectResponse = new JsonObjectResponse("OK", null, null);
        } else {
            this.jsonObjectResponse = new JsonObjectResponse("ERROR", "No such key", null);
        }
    }

    public void exit(){
        this.jsonObjectResponse = new JsonObjectResponse("OK", null, null);
    }
}
