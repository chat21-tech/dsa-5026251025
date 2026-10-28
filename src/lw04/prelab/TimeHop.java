package lw04.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TimeHop {
    public static void insertionSortDesc(int[] A, int n, int pass) {
        for (int i = 1; i <= pass; i++) {
            int key = A[i];
            int j = i - 1;
 
            while (j >= 0 && A[j] < key) {
                A[j + 1] = A[j];
                j--;
            }
 
            A[j + 1] = key;
        }
    }
 
    public static int linearSearch(int[] A, int n, int target) {
        for (int i = 0; i < n; i++) {
            if (A[i] == target) {
                return i;
            }
        }
        return -1;
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner (new File("src/lw04/prelab/branches.txt"));
 
        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }
 
        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();
 
        insertionSortDesc(branches, n, 3);
 
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(branches[i]);
        }
        System.out.println();
 
        for (int i = 0; i < m; i++) {
            int idx = linearSearch(branches, n, targets[i]);
            if (idx != -1) {
                System.out.println(targets[i] + ": FOUND at index " + idx);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }
}
