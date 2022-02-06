package baek18290;

import java.util.Scanner;

public class Main {

    static int n, m, k;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int result = dfs(0, 0, 0, 0);

        System.out.println(result);
    }

    static int dfs(int row, int col, int sum, int cnt) {
        if (cnt > k) {
            return Integer.MIN_VALUE;
        } else if (cnt == k) {
            return sum;
        } else {
            int max = Integer.MIN_VALUE;
            for (int i = row; i < n; i++) {
                for (int j = (i == row)? col : 0; j < m; j++) {
                    if (check_near(i, j)) {
                        visited[i][j] = true;
                        int result = dfs(i, j, sum + map[i][j], cnt + 1);
                        visited[i][j] = false;

                        max = Math.max(max, result);
                    }
                }
            }
            return max;
        }
    }

    static boolean check_near(int row, int col) {
        if (visited[row][col]) {
            return false;
        } else {
            for (int i = 0; i < 4; i++) {
                int mr = row + dr[i];
                int mc = col + dc[i];

                if (mr >= 0 && mr < n && mc >= 0 && mc < m) {
                    if (visited[mr][mc]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
