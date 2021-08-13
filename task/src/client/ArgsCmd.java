package client;

import com.beust.jcommander.Parameter;

public class ArgsCmd {
    @Parameter(
            names = "-t",
            description = "Type requested",
            required = true
    )
    private String type;

    @Parameter(
            names = "-k",
            description = "The key",
            required = false
    )
    private String key;


    @Parameter(
            names = "-v",
            description = "Value to save in the database"
    )
    private String stringValue;

    public String getType() {
        return type;
    }

    public String getKey() {
        if (key == null) {
            return null;
        }
        return key.toString();
    }

    public String getStringValue() {
        if (stringValue == null) {
            return null;
        }
        return stringValue;
    }

    public void printAll() {
        System.out.println("-t " + this.getType());
        System.out.println("-i " + this.getKey());
        System.out.println("-m " + this.getStringValue());
    }
}
