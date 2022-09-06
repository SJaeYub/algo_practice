package swea1249;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {

    static class Position {
        int row;
        int col;

        Position(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    static int n;
    static int[][] map;
    static int[][] dist;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea1249/input.txt"));


        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();

            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                String temp = sc.next();
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(temp.substring(j, j + 1));
                }
            }

            dijkstra(0, 0);

            System.out.println("#" + test_case + " " + dist[n - 1][n - 1]);
        }
    }

    static void dijkstra(int start_r, int start_c) {
        dist = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[start_r][start_c] = 0;
        visited[start_r][start_c] = true;

        for (int i = 0; i < 4; i++) {
            int mr = start_r + dr[i];
            int mc = start_c + dc[i];

            if (mr >= 0 && mr < n && mc >= 0 && mc < n) {
                if (!visited[mr][mc]) {
                    dist[mr][mc] = map[mr][mc];
                }
            }
        }

        for (int a = 0; a < n * n - 2; a++) {
            int min = Integer.MAX_VALUE;
            int minR = -1;
            int minC = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && dist[i][j] != Integer.MAX_VALUE) {
                        if (dist[i][j] < min) {
                            min = dist[i][j];
                            minR = i;
                            minC = j;
                        }
                    }
                }
            }
//            System.out.println(minR + " " + minC);

            visited[minR][minC] = true;

            for (int i = 0; i < 4; i++) {
                int mr = minR + dr[i];
                int mc = minC + dc[i];

                if (mr >= 0 && mr < n && mc >= 0 && mc < n) {
                    if (dist[mr][mc] > dist[minR][minC] + map[mr][mc]) {
                        dist[mr][mc] = dist[minR][minC] + map[mr][mc];
                    }
                }
            }
        }
    }
}