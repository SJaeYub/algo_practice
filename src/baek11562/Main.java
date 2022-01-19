package baek11562;

import java.util.Scanner;

public class Main {

    static int n, m;
    static int[][] w, d, p;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        w = new int[n][n];
        d = new int[n][n];
        p = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    w[i][j] = 300;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int l = sc.nextInt();
            int j = sc.nextInt();
            int c = sc.nextInt();
            w[l - 1][j - 1] = 0;

            if (c == 0) {
                w[j - 1][l - 1] = 1;
            } else {
                w[j - 1][l - 1] = 0;
            }

        }

        floyd2();

        int cnt = sc.nextInt();

        for (int i = 0; i < cnt; i++) {
            System.out.println(d[sc.nextInt() - 1][sc.nextInt() - 1]);
        }
    }

    static void floyd2() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = 0;
            }
        }

        d = w;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        p[i][j] = k;
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
    }

    static void path(int q, int r) {
        if (p[q][r] != 0) {
            path(q, p[q][r]);
            System.out.println("v" + p[q][r]);
            path(p[q][r], r);
        }
    }
}
