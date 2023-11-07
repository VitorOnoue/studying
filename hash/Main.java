/*
Bruno Gustavo Rocha
Pedro Nogueira Ribeiro
Vitor Kenzo Koga Onoue
*/

import java.util.LinkedList;
import java.util.Random;

class Main {
    public static void main(String[] args) {
        int size = 31, nums = 100;
        int cold = 0, colm = 0;

        LinkedList<Integer> hash_div[] = new LinkedList[size];
        LinkedList<Integer> hash_mult[] = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hash_div[i] = new LinkedList<Integer>();
            hash_mult[i] = new LinkedList<Integer>();
        }
        Random rand = new Random();

        for (int i = 0; i < nums; i++) {
            int rnumber = rand.nextInt(5001);
            int div = hashDiv(rnumber, size);
            int mult = hashMult(rnumber, size);
            if (!hash_div[div].isEmpty()) {
                cold++;
            }
            if (!hash_mult[mult].isEmpty()) {
                colm++;
            }
            hash_div[div].add(rnumber);
            hash_mult[mult].add(rnumber);
        }
        StringBuilder divsb = new StringBuilder();
        StringBuilder multsb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            divsb.append(i + ": ");
            multsb.append(i + ": ");
            for (int n : hash_div[i]) {
                divsb.append(n + " -> ");
            }
            for (int n : hash_mult[i]) {
                multsb.append(n + " -> ");
            }
            divsb.append("\n");
            multsb.append("\n");
        }
        System.out.print("Hash divisão:\n" + divsb);
        System.out.println("Colisões: " + cold + "\n");
        System.out.print("Hash multiplicação:\n" + multsb);
        System.out.println("Colisões: " + colm + "\n");
    }

    private static int hashDiv(int x, int m) {
        int fdex = x % m;
        return fdex;
    }

    private static int hashMult(int x, int m) {
        double A = 0.61803398875;
        double floor = Math.floor(m * (x * A % 1));
        int fdex = (int) floor;
        return fdex;
    }
}