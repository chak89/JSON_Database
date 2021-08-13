package client;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class ArgsCmd {
    @Parameter(
            names = "-t",
            description = "Type requested",
            required = true
    )
    private String type;

    @Parameter(
            names = "-i",
            description = "Index of the cell",
            required = false
    )
    private Integer cellInx;


    @Parameter(
            names = "-m",
            description = "Value to save in the database"
    )
    private String stringValue;

    public String getType() {
        return type;
    }

    public String getCellInx() {
        if (cellInx == null) {
            return "";
        }

        return cellInx.toString();
    }

    public String getStringValue() {
        if (stringValue == null) {
            return "";
        }
        return stringValue;
    }

    public void printAll() {
        System.out.println("-t " + this.getType());
        System.out.println("-i " + this.getCellInx());
        System.out.println("-m " + this.getStringValue());
    }
}
