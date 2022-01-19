package baek11404;

import java.util.Scanner;

public class Main {

    static int n, m;
    static int[][] d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        d = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    d[i][j] = 987654321;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;
            int cost = sc.nextInt();

            d[from][to] = Math.min(cost, d[from][to]);
        }

        floyd();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (d[i][j] == 987654321) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(d[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }
}
