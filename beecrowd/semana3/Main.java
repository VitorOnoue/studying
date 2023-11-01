import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j;

        while (s.hasNext()) {
            int n = s.nextInt();
            if (n >= 3 && n < 70) {
                for (i = 1; i <= n; i++) {
                    for (j = 1; j <= n; j++) {
                        if (j + i - 1 == n) {
                            System.out.printf("2");
                        }

                        else if (j == i) {
                            System.out.printf("1");
                        }

                        else {
                            System.out.printf("3");
                        }

                        if (j >= n) {
                            System.out.printf("\n");
                        }
                    }
                }
            }

        }
        s.close();
    }
}