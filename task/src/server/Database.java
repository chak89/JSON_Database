package server;

import java.util.Arrays;


public class Database {
    String[] storage;

    public Database() {
        this.storage  = new String[100];
        Arrays.fill(storage,"");
    }

    public String[] getStorage() {
        return storage;
    }

    public void setStorage(String[] storage) {
        this.storage = storage;
    }

    public void setCell(int index, String data) {
        if (!isIndexInRange(index)) {return;}

        storage[index - 1] = data;
        System.out.println("OK");
    }


    public void getCell(int index){

        if (!isIndexInRange(index)) {return;}

        if (storage[index - 1].isEmpty()) {
            System.out.println("ERROR");
            return;
        }
        System.out.println(storage[index - 1]);
    }

    public void delCell(int index) {
        if (!isIndexInRange(index)) {return;}

        if (!storage[index - 1].isEmpty()) {
            storage[index - 1] = "";
        }
        System.out.println("OK");
    }

    public boolean isIndexInRange(int index) {

        if (index < 1 || index > 100) {
            System.out.println("ERROR");
            return false;
        }
        return true;
    }
}
