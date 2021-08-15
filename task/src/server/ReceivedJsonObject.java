package server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

public class ReceivedJsonObject {
    private String type;
    private JsonElement key;
    private JsonElement value;
    private transient String json_string;

    public ReceivedJsonObject() {
        this(null);
    }

    public ReceivedJsonObject(String json_string) {
        this.json_string = json_string;
    }

    public ReceivedJsonObject(String type, JsonElement key, JsonElement value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    //Deserialize from JSON to this class instance
    public ReceivedJsonObject getDeserializeJSON() {
        Gson gson = new Gson();
        try {
            return gson.fromJson(this.json_string, ReceivedJsonObject.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Serialize this instance to JSON;
    public String getSerializeJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "ReceivedJsonObject{" +
                "type='" + type + '\'' +
                ", key=" + key +
                ", value=" + value +
                '}';
    }
}
