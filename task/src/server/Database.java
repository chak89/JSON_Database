package server;

import java.util.Arrays;


public class Database {
    String[] storage;

    public Database() {
        this.storage  = new String[1000];
        Arrays.fill(storage,"");
    }

    public String[] getStorage() {
        return storage;
    }

    public void setStorage(String[] storage) {
        this.storage = storage;
    }

    public String setCell(int index, String data) {
        if (!isIndexInRange(index)) {
            return "ERROR";
        }

        storage[index - 1] = data;
        return "OK";
    }


    public String getCell(int index){
        if (!isIndexInRange(index)) {
            return "ERROR";
        }

        if (storage[index - 1].isEmpty()) {
            return "ERROR";
        }
        return storage[index - 1];
    }

    public String delCell(int index) {
        if (!isIndexInRange(index)) {
            return "ERROR";
        }

        if (!storage[index - 1].isEmpty()) {
            storage[index - 1] = "";
        }
        return "OK";
    }

    public boolean isIndexInRange(int index) {

        if (index < 1 || index > 100) {
            return false;
        }
        return true;
    }
}
