package baek5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Position {
        int row;
        int col;
        int cnt;
        int status;

        Position(int a, int b, int c, int d) {
            this.row = a;
            this.col = b;
            this.cnt = c;
            this.status = d;
        }
    }

    static int w, h;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][] visited;
    static String temp;
    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int test_case = 0; test_case < t; test_case++) {
            q.clear();

            w = sc.nextInt();
            h = sc.nextInt();

            map = new char[h][w];
            visited = new boolean[h][w];

            int temp_start_row = 0, temp_start_col = 0;
            for (int i = 0; i < h; i++) {
                temp = sc.next();
                for (int j = 0; j < temp.length(); j++) {
                    map[i][j] = temp.charAt(j);

                    if (map[i][j] == '#') {
                        visited[i][j] = true;
                    }

                    if (map[i][j] == '@') {
                        temp_start_row = i;
                        temp_start_col = j;
                    }
                }
            }
            check_fire();
            visited[temp_start_row][temp_start_col] = true;
            q.add(new Position(temp_start_row, temp_start_col, 0, 2));      //사람이라면

            bfs();

        }
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.row][curr.col] = true;

            if (curr.status == 1) {
                map[curr.row][curr.col] = '*';
            }

            if (curr.status == 2) {
                if (curr.row == h - 1 || curr.col == w - 1 || curr.row == 0 || curr.col == 0) {
                    System.out.println(curr.cnt + 1);
                    return;
                }
            }

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < h && mc >= 0 && mc < w) {
                    if (map[mr][mc] == '#') {
                        continue;
                    }

                    if (curr.status == 1) {                 //불이라면
                        if (!visited[mr][mc] && map[mr][mc] != '*') {
                            map[mr][mc] = 0;
                            visited[mr][mc] = true;
                            q.add(new Position(mr, mc, 0, curr.status));
                        }
                    }
                    if (curr.status == 2) {
                        if (!visited[mr][mc] && map[mr][mc] == '.') {
                            visited[mr][mc] = true;
                            q.add(new Position(mr, mc, curr.cnt + 1, curr.status));
                        }
                    }

                }
            }
        }

        System.out.println("IMPOSSIBLE");
        return;
    }

    private static void check_fire() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == '*') {
                    visited[i][j] = true;
                    q.add(new Position(i, j, 0, 1));
                }
            }
        }
    }

}
