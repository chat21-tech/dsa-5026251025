package lw04.unguided;

import java.util.Scanner;

public class sortBot {
    static void bubbleSort(int[] a, int startPass, int endPass) {
        int n = a.length;
        for (int i = startPass; i < endPass && i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    static int binarySearch(int[] a, int search, int[] steps) {
        int low = 0;
        int high = a.length - 1;
        steps[0] = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps[0]++;

            if (a[mid] == search) {
                return mid;
            } else if (a[mid] < search) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(sortBot.class.getResourceAsStream("parcels.txt"));

        int N = sc.nextInt();
        int[] parcels = new int[N];
        for (int i = 0; i < N; i++) {
            parcels[i] = sc.nextInt();
        }

        int M = sc.nextInt();
        int[] targets = new int[M];
        for (int i = 0; i < M; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        bubbleSort(parcels, 0, 2);

        int[] afterTwo = new int[N];
        for (int i = 0; i < N; i++) {
            afterTwo[i] = parcels[i];
        }

        bubbleSort(parcels, 2, N - 1);

        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < N; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(afterTwo[i]);
        }
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        for (int i = 0; i < N; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(parcels[i]);
        }
        System.out.println();

        System.out.println("==== Search Results ====");
        int[] steps = new int[1];
        for (int i = 0; i < M; i++) {
            int search = targets[i];
            int index = binarySearch(parcels, search, steps);

            if (index == -1) {
                System.out.println(search + ": NOT FOUND (steps: " + steps[0] + ")");
                continue;
            }

            System.out.println(search + ": FOUND at index " + index + " (steps: " + steps[0] + ")");
        }
    }
}