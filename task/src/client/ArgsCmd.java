package client;

import com.beust.jcommander.Parameter;
import com.google.gson.Gson;

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
    private String value;


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

    public String getValue() {
        if (value == null) {
            return null;
        }
        return value;
    }

    public String convertToJson() {
        if (this.filename != null) {
            ReadDataFromFile file1 = new ReadDataFromFile(getFilename());
            file1.readDataToString();
            return file1.getData();
        }

        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void printAll() {
        System.out.println("-t " + this.getType());
        System.out.println("-i " + this.getKey());
        System.out.println("-m " + this.getValue());
    }
}
