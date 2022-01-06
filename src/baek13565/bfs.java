package baek13565;

import java.util.*;
import java.io.*;

public class bfs {

    static int m, n;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static StringTokenizer st;
    static boolean flag = false;
    static boolean[][] visited;

    static class Position {
        int row;
        int col;

        Position(int a, int b) {
            this.row = a;
            this.col = b;
        }
    }

    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            String temp = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(temp.substring(j, j+1));
            }
        }

        for (int i = 0; i < n; i++) {
            if (map[0][i] == 0) {
                visited = new boolean[m][n];
                q.clear();
                q.add(new Position(0, i));
                bfs();
                if (flag) {
                    break;
                }
            }
        }

        if (!flag) {
            System.out.println("NO");
            return;
        } else {
            System.out.println("YES");
            return;
        }


    }

    static void bfs() {

        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.row][curr.col] = true;

            if (curr.row == m - 1) {
                flag = true;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < m && mc >= 0 && mc < n) {
                    if(map[mr][mc] == 0) {
                        if (!visited[mr][mc]) {
                            visited[mr][mc] = true;
                            q.add(new Position(mr, mc));
                        }
                    }

                }
            }
        }
    }
}
