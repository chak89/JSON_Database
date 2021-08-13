package server;

import com.google.gson.Gson;

public class JsonObjectResponse {
    String response;
    String reason;
    String value;
    private transient Gson gson;

    public JsonObjectResponse(String response, String reason, String value) {
        this.response = response;
        this.reason = reason;
        this.value = value;
        this.gson = new Gson();
    }

    public String getSerializeJSON() {
        return gson.toJson(this);
    }
}
