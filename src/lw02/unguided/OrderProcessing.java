package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class OrderProcessing {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        Scanner sc = new Scanner(OrderProcessing.class.getResourceAsStream("orders.txt"));
        while (sc.hasNext()) {
            String name = sc.next();
            String food = sc.next();
            String drink = sc.next();
            String table = sc.next();
            orders.add(new String[]{name, food, drink, table});
        }
        sc.close();

        Queue<String[]>queue = new LinkedList<>();
        for(String[] o : orders) {
            queue.add(o);
        }
        
        Stack<String[]> failed =new Stack<>();
        int failedCount = 0;

        String[] o;
        while ((o = queue.poll()) != null) {
            String food = o[1];
            String drink = o[2];
            String[] stockFood = null;
            
            if (!food.equals("-")) {
                for (String[] f : foods) {
                    if (f[0].equals(food)) {
                        stockFood = f;
                    }
                }
            }
            String[] stockDrink = null;
            if (!drink.equals("-")) {
                for (String[] d : drinks) {
                    if (d[0].equals(drink)) {
                        stockDrink = d;
                    }
                }
            }

            boolean available = true;
            if (!food.equals("-")) {
                if (stockFood == null || Integer.parseInt(stockFood[1]) <= 0) {
                    available = false;
                }
            }
            if (!drink.equals("-")) {
                if (stockDrink == null || Integer.parseInt(stockDrink[1]) <= 0) {
                    available = false;
                }
            }

            if (available) {
                if (stockFood != null) {
                    stockFood[1] = String.valueOf(Integer.parseInt(stockFood[1]) - 1);
                }
                if (stockDrink != null) {
                    stockDrink[1] = String.valueOf(Integer.parseInt(stockDrink[1]) - 1);
                }
                successOrders.add(o);
            } else {
                failed.push(o);
                failedCount++;
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] s : successOrders) {
            System.out.println(s[0] + " " + s[1] + " " + s[2] + " " + s[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foods) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinks) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println("=== Failed Orders ===");
        for (int i = 0; i < failedCount; i++) {
            String[] f = failed.pop();
            System.out.println(f[0] + " " + f[1] + " " + f[2] + " " + f[3]);
        }
    }
}
