package lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
    Set<String> registered = new HashSet<>();
    Set<String> checkedIn = new HashSet<>();
    List<String> results = new ArrayList<>();
    int rejectedCount = 0;

    try {
        Scanner sc = new Scanner (new File("src/lw03/unguided/registrations.txt"));
        while (sc.hasNextLine()) {
            registered.add(sc.next());
        }
        sc.close();

        Scanner scanner = new Scanner(new File("src/lw03/unguided/checkins.txt"));

        while (scanner.hasNext()) {
            String id = scanner.next();

            if (!registered.contains(id)) {
                results.add(id + ": Rejected (not registered)");
                rejectedCount++;
            } else if (checkedIn.contains(id)) {
                results.add(id + ": Rejected (already checked in)");
                rejectedCount++;
            } else {
                checkedIn.add(id);
                results.add(id + ": Checked in");
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        System.out.println("File tidak ditemukan: " + e.getMessage());
        return;
    }

    System.out.println("=====Check-in Results=====");
    for(int i = 0; i< results.size(); i++) {
        System.out.println(results.get(i));
    }

    System.out.println();
    System.out.println("===== Final Event Summary =====");
    System.out.println("Registered students: " + registered.size());
    System.out.println("Successful check-ins: " + checkedIn.size());
    System.out.println("Absent students: " + (registered.size() - checkedIn.size()));
    System.out.println("Rejected attempts: " + rejectedCount);
    }
}


