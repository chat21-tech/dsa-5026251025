package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void problem1() throws FileNotFoundException {
        LinkedList<String> playlist = new LinkedList<>();
        Scanner sc = new Scanner(new File("src/lw03/prelab/playlist.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] kata = line.split(" ");
            String op = kata[0];

            if (op.equals("ADD")) {
                String lagu = line.substring(4);
                playlist.add(lagu);

            } else if (op.equals("INSERT")) {
                int index = Integer.parseInt(kata[1]);
                String lagu = line.substring(7 + kata[1].length() + 1);
                playlist.add(index, lagu);

            } else if (op.equals("REMOVE")) {
                String lagu = line.substring(7);
                playlist.remove(lagu);
            }
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for(int i = 0; i< playlist.size(); i++){
            System.out.println((i+1) + ": " + playlist.get(i));
        }
    }
    
    public static void problem2() throws FileNotFoundException {
        Set<String> peserta = new LinkedHashSet<>();
        Scanner sc = new Scanner(new File("src/lw03/prelab/participants.txt"));
        int duplikat = 0;

        while (sc.hasNextLine()) {
            String nama = sc.nextLine();

            if (peserta.contains(nama)) {
                duplikat++;

            } else {
                peserta.add(nama);
            }
        }
        sc.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + peserta.size());
        int nomor = 1;

        for(String nama : peserta){
            System.out.println(nomor + ": " + nama);
            nomor++;
        }

        System.out.println("Duplicate participants: " + duplikat);
    }

    public static void problem3() throws FileNotFoundException {
        Map<String, Integer> stok = new LinkedHashMap<>();
        int gagal = 0;
        Scanner sc = new Scanner(new File("src/lw03/prelab/inventory.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] kata = line.split(" ");
            String tipe = kata[0];
            String produk = kata[1];
            int jumlah = Integer.parseInt(kata[2]);

            if (tipe.equals("ADD")) {

                if (stok.containsKey(produk)) {
                    stok.put(produk, stok.get(produk) + jumlah);
                } else {
                    stok.put(produk, jumlah);
                }

            } else if (tipe.equals("SELL")) {
                if (stok.containsKey(produk) && stok.get(produk) >= jumlah) {
                    stok.put(produk, stok.get(produk) - jumlah);
                } else {
                    gagal++;
                }
            } 
        }
        sc.close();

       System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : stok.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + gagal);
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }
}
