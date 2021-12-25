package baek1389;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int n, m, temp_sum;
    static int[][] relations;
    static int[][] p;
    static int[][] d;
    static int ans = Integer.MAX_VALUE;
    static boolean[] visited;
    static ArrayList<Integer> ans_idx;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        relations = new int[n + 1][n + 1];
        visited = new boolean[n];
        ans_idx = new ArrayList<>();
        p = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    relations[i][j] = 0;
                } else {
                    relations[i][j] = 100;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int temp1 = sc.nextInt();
            int temp2 = sc.nextInt();

            relations[temp1][temp2] = 1;
            relations[temp2][temp1] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                p[i][j] = 0;
            }
        }

        floyd();

        for (int i = 1; i <= n; i++) {
            temp_sum = 0;
            for (int j = 1; j <= n; j++) {
                temp_sum += relations[i][j];
            }
            ans_idx.add(temp_sum);
            ans = Math.min(ans, temp_sum);
        }

        for (int i = 0; i < n; i++) {
            if (ans_idx.get(i) == ans) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    private static void floyd() {

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (relations[i][k] + relations[k][j] < relations[i][j]) {
                        p[i][j] = k;
                        relations[i][j] = relations[i][k] + relations[k][j];
                    }
                }
            }
        }
    }

    private static void path(int q, int r) {
        if (p[q][r] != 0) {
            path(q, p[q][r]);
            System.out.println("v" + p[q][r]);
            path(p[q][r], r);
        }
    }
}
