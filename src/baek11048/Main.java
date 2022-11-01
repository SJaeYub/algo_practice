package baek11048;

import java.util.Scanner;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {1, 0, 1};
    static int[] dc = {0, 1, 1};
    static int N, M, ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        dfs(1, 1, map[1][1]);

        System.out.println(ans - 1);
    }

    static void dfs(int row, int col, int candySum) {

        if (row == N && col == M) {
            candySum += map[row][col];

            ans = Math.max(ans, candySum);
            return;
        }

        candySum += map[row][col];

        for (int i = 0; i < 3; i++) {
            int mr = row + dr[i];
            int mc = col + dc[i];

            if (mr >= 1 && mr <= N && mc >= 1 && mc <= M) {
                if (!visited[mr][mc]) {
                    visited[mr][mc] = true;
                    dfs(mr, mc, candySum);
                    visited[mr][mc] = false;
                }
            }
        }
        return;
    }
}
