package baek14502;

import java.util.LinkedList;
import java.util.*;

public class Main {

    static int n, m, ans;
    static int[][] map, virus_map;
    static int numOfWall = 3;
    static int[] x = {-1, 1, 0, 0};
    static int[] y = {0, 0, -1, 1};
    static boolean[][] visited;
    static Queue<Position> q;
    static int max_ans = Integer.MIN_VALUE;

    static class Position {
        int y;
        int x;

        Position(int a, int b) {
            this.y = a;
            this.x = b;
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        visited = new boolean[n][m];

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        dfs(0);
        System.out.println(max_ans);

    }

    static void dfs(int cnt) {

        if (cnt == numOfWall) {

            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }


    }

    static void bfs() {
        virus_map = new int[n][m];
        q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                virus_map[i][j] = map[i][j];
                if (virus_map[i][j] == 2) {
                    Position temp = new Position(i, j);
                    q.add(temp);
                }
            }
        }
        while (!q.isEmpty()) {
            Position curr = q.poll();
            virus_map[curr.y][curr.x] = 2;

            for (int i = 0; i < 4; i++) {
                int mx = curr.x + x[i];
                int my = curr.y + y[i];

                if (mx >= 0 && mx < m && my >= 0 && my < n) {
                    if (virus_map[my][mx] == 0) {
                        Position temp = new Position(my, mx);
                        virus_map[my][mx] = 2;
                        q.add(temp);

                    }
                }
            }
        }

        max_ans = Math.max(count(virus_map), max_ans);
    }

    static int count(int[][] cpy) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cpy[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
