package baek14500;

import java.util.Scanner;

public class Main {

    static int n, m, ans = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
//    baek14500 hard 테트로미노는 dfs를 이용하면 모든 경우의 수로 탐색 가능
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
                cross(i, j, map[i][j]);
            }
        }

        System.out.println(ans);
    }

    private static void cross(int row, int col, int sum) {
        int temp = Integer.MAX_VALUE;
        int cnt = 1;

        for (int i = 0; i < 4; i++) {
            int mr = row + dr[i];
            int mc = col + dc[i];

            if (mr >= 0 && mr < n && mc >= 0 && mc < m) {
                sum += map[mr][mc];
                temp = Math.min(temp, map[mr][mc]);
                cnt++;
            }
        }

        if (cnt >= 5) {
            sum -= temp;
        }

        ans = Math.max(ans, sum);
    }

    private static void dfs(int row, int col, int cnt, int sum) {
        if (cnt == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int mr = dr[i] + row;
            int mc = dc[i] + col;

            if (mr >= 0 && mr < n && mc >= 0 && mc < m) {
                if (!visited[mr][mc]) {
                    visited[mr][mc] = true;
                    dfs(mr, mc, cnt + 1, sum + map[mr][mc]);
                    visited[mr][mc] = false;
                }
            }
        }

    }
}
