package baek1956;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        int[][] d = new int[v + 1][v + 1];

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i != j) {
                    d[i][j] = 987654321;
                }
            }
        }

        for (int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            d[from][to] = weight;
        }

        int[][] results = floyd(d, v, e);

        int ans = 987654321;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) {
                    continue;
                }
                if (results[i][j] != 987654321 && results[j][i] != 987654321) {
                    ans = Math.min(ans, results[i][j] + results[j][i]);
                }
            }
        }
        if (ans == 987654321) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static int[][] floyd(int[][] arr, int v, int e) {
        int[][] result = arr;
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (result[i][j] > result[i][k] + result[k][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }

        return result;
    }
}
