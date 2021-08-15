package client;

import com.beust.jcommander.Parameter;

public class ArgsCmd {
    @Parameter(
            names = "-in",
            description = "argument followed by the file name was provided, you should read a request from that file. The file will be stored in /client/data."
    )
    private String filename;

    @Parameter(
            names = "-t",
            description = "Type requested"
    )
    private String type;

    @Parameter(
            names = "-k",
            description = "The key"
    )
    private String key;

    @Parameter(
            names = "-v",
            description = "Value to save in the database"
    )
    private String stringValue;

    public String getFilename() {
        if (filename == null) {
            return null;
        }
        return filename;
    }

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
