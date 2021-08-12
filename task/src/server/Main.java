package server;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        String[] storage = database.getStorage();


        try (Scanner sc = new Scanner(System.in)) {

            while (true) {


                String command = sc.nextLine();
                String[] split = command.split(" ", 3);

                switch (split[0]) {
                    case "get":
                        database.getCell(Integer.parseInt(split[1]));
                        break;
                    case "set":
                        database.setCell(Integer.parseInt(split[1]), split[2]);
                        break;
                    case "delete":
                        database.delCell(Integer.parseInt(split[1]));
                        break;
                    case "exit":
                        return;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
