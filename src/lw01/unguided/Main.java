package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("src/lw01/unguided/washes.txt"))) {
            int total = scanner.nextInt();
            WashService[] orders = new WashService[total];
 
            for (int i = 0; i < total; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();
 
                if (type.equals("MOTORCYCLE")) {
                    orders[i] = new MotorcycleWash(id, days, units);
                } else if (type.equals("CAR")) {
                    orders[i] = new CarWash(id, days, units);
                } else {
                    throw new IllegalArgumentException("Unknown vehicle type: " + type);
                }
            }

            for (WashService order : orders) {
                System.out.println(order.summary());
            }
 
        } catch (FileNotFoundException e) {
            System.out.println("Could not find washes.txt: " + e.getMessage());
        }
    }
}
 