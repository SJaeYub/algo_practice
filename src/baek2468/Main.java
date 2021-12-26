package baek2468;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Position {
        int row;
        int col;

        Position(int a, int b) {
            this.row = a;
            this.col = b;
        }
    }

    static int[] x = {-1, 1, 0, 0};
    static int[] y = {0, 0, -1, 1};
    static int n;
    static int[][] map;
    static int max_height = Integer.MIN_VALUE;
    static int ans = Integer.MIN_VALUE;
    static boolean[][] visited;
    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        map = new int[n][n];
        int[][] temp_map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                max_height = Math.max(max_height, map[i][j]);
            }
        }

        for (int i = 0; i <= max_height; i++) {
            visited = new boolean[n][n];
            temp_map = map;

            comp(i, temp_map);

            int safe_cnt = 0;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!visited[j][k] && temp_map[j][k] != 0) {
                        visited[j][k] = true;
                        safe_cnt++;
                        bfs(j, k, temp_map);
                    }
                }
            }

            ans = Math.max(ans, safe_cnt);
        }

        System.out.println(ans);
    }

    private static void comp(int lmt, int[][] temp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j] <= lmt) {
                    temp[i][j] = 0;
                }
            }
        }
    }

    private static void bfs(int temp_row, int temp_col, int[][] temp_map) {
        Position start = new Position(temp_row, temp_col);
        q.add(start);

        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.row][curr.col] = true;

            for (int i = 0; i < 4; i++) {
                int dr = curr.row + y[i];
                int dc = curr.col + x[i];

                if (dr >= 0 && dr < n && dc >= 0 && dc < n) {
                    if (!visited[dr][dc]) {
                        if (temp_map[dr][dc] != 0) {
                            visited[dr][dc] = true;
                            Position temp = new Position(dr, dc);
                            q.add(temp);
                        }
                    }
                }
            }
        }
    }
}
