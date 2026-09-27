package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransactionProcessor {
    public static void main(String[] args) {
 
        LinkedList<String[]> transactions = new LinkedList<>();
 
        try {
            File file = new File("transactions.txt");
            if (!file.exists()) {
                file = new File("src/lw02/prelab/transactions.txt");
            }
            Scanner scanner = new Scanner(file);
 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                transactions.add(parts);
            }
 
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt not found.");
            return;
        }
 
        LinkedList<String[]> customers = new LinkedList<>();
 
        for (String[] transaction : transactions) {
            String name = transaction[0];
            boolean exists = false;
 
            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    exists = true;
                    break;
                }
            }
 
            if (!exists) {
                customers.add(new String[] { name, "0" });
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<>();
        transactionQueue.addAll(transactions);
 
        Stack<String[]> failedTransactions = new Stack<>();
 
        String[] transaction;
        while ((transaction = transactionQueue.poll()) != null) {
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);
 
            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }
 
            if (customer == null) {
                continue; 
            }
 
            int balance = Integer.parseInt(customer[1]);
 
            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failedTransactions.push(transaction);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }
 
        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }
 
        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}
