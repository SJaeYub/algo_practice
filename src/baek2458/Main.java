package baek2458;

import java.util.Scanner;

public class Main {

    static int n, m;
    static boolean[][] d, p;
    static int INF = 987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        d = new boolean[n][n];
        p = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = 1;

            d[a][b] = true;
            p[b][a] = true;
        }

        floyd(d);
        floyd(p);

        int ans = 0;
        outer : for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                if (!(d[i][j] |= p[i][j])) {
                    continue outer;
                }
            }
            ans++;
        }

        System.out.println(ans);
    }

    static void floyd(boolean[][] d) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ( d[i][k] && d[k][j]) {
                        d[i][j] = true;
                    }
                }
            }
        }

    }
}
