package baek14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static Queue<Position> q;

    static class Position {
        int row;
        int col;
        int dist;

        Position(int r, int c, int d) {
            this.row = r;
            this.col = c;
            this.dist = d;
        }
    }

    static boolean flag = false;

    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        n = sc.nextInt();
//        m = sc.nextInt();

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        int[][] dist = new int[n + 1][m + 1];
        Position start = null;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    start = new Position(i, j, 0);
                    flag = true;
                }
            }
        }

        if (flag) {
            dist = bfs(start.row, start.col);
        } else {
            dist = bfs(0, 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
//                int dist = bfs(i, j);
                sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);


    }

    static int[][] bfs(int start_r, int start_c) {
        int[][] result = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                result[i][j] = -1;
            }
        }

        if (!flag) {
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= m; j++) {
//                    result[i][j] = 0;
//                }
//            }
            return result;
        }

        q = new LinkedList<>();
        visited = new boolean[n + 1][m + 1];

        q.add(new Position(start_r, start_c, 0));
        visited[start_r][start_c] = true;

        while (!q.isEmpty()) {
            Position curr = q.poll();

            visited[curr.row][curr.col] = true;

            if (map[curr.row][curr.col] == 0) {
                result[curr.row][curr.col] = 0;
                continue;
            }

            result[curr.row][curr.col] = curr.dist;

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 1 && mr <= n && mc >= 1 && mc <= m) {
                    if (!visited[mr][mc]) {
                        visited[mr][mc] = true;
                        q.add(new Position(mr, mc, curr.dist + 1));
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 0) {
                    result[i][j] = 0;
                }
            }
        }

        return result;
    }
}
