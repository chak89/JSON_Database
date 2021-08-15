package server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class ResponseJsonObject {
    private String response;
    private String reason;
    private JsonElement value;

    public ResponseJsonObject(String response, String reason, JsonElement value) {
        this.response = response;
        this.reason = reason;
        this.value = value;
    }

    public String getSerializeJSON() {
        return new Gson().toJson(this);
    }
}
