package server;

import com.google.gson.Gson;

import java.io.Serializable;

public class JsonObject {
    private String type;
    private String key;
    private String value;
    private transient String json_string;
    private transient Gson gson;

    public JsonObject() {
        this(null);
    }

    public JsonObject(String json_string) {
        this.json_string = json_string;
        this.gson = new Gson();
    }

    public JsonObject(String type, String key, String value) {
        this();
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public JsonObject getDeserialzeJSON() {
        return this.gson.fromJson(this.json_string, JsonObject.class);
    }

    public String getSerializeJSON() {
        return this.gson.toJson(this);
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
