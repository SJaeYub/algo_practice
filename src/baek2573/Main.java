package baek2573;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class IceBerg {
        int row;
        int col;
        int cnt;

        IceBerg(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }

    static StringTokenizer st;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m;
    static int[][] grid;
    static Queue<IceBerg> q = new LinkedList<>();
    static boolean[][] visited;
    static ArrayList<IceBerg> arr_list = new ArrayList<>();
    static int numOfIce, answer = 0;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (!flag) {

            search_berg();
            if (flag) {
                return;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] != 0) {
                        visited = new boolean[n][m];
                        arr_list.clear();
                        q.add(new IceBerg(i, j, 0));
                        answer++;
                        bfs();
                        fusion();               //한번의 얼음 융해 후

                        search_berg();

                        if (flag) {
                            return;
                        }
                        if (numOfIce == 0) {
                            System.out.println(0);
                            return;
                        }

                    }
                }
            }
        }

    }

    static void search_berg() {
        visited = new boolean[n][m];
        numOfIce = 0;
        for (int l = 0; l < n; l++) {
            for (int k = 0; k < m; k++) {
                if (grid[l][k] != 0) {
                    if (!visited[l][k]) {
                        numOfIce++;
                        if (numOfIce >= 2) {
                            System.out.println(answer);
                            flag = true;
                            return;
                        }
                        visited[l][k] = true;
                        dfs(l, k);                      //빙산이 몇개인지 카운트
                    }
                }
            }
        }
    }

    static void dfs(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int mr = dr[i] + r;
            int mc = dc[i] + c;

            if (mr >= 0 && mr < n && mc >= 0 && mc < m) {
                if (grid[mr][mc] != 0) {
                    if (!visited[mr][mc]) {
                        visited[mr][mc] = true;
                        dfs(mr, mc);
                    }
                }
            }
        }
    }

    static void bfs() {

        while (!q.isEmpty()) {
            IceBerg curr = q.poll();
            visited[curr.row][curr.col] = true;

            for (int i = 0; i < 4; i++) {                   //주변에 바다가 몇개인지 탐색
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (grid[mr][mc] == 0) {
                    curr.cnt++;
                }
            }

            arr_list.add(curr);     //주변의 바다 개수를 세서 배열리스트에 저장

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < n && mc >= 0 && mc < m) {
                    if (grid[mr][mc] != 0) {
                        if (!visited[mr][mc]) {
                            visited[mr][mc] = true;
                            q.add(new IceBerg(mr, mc, 0));
                        }
                    }

                }
            }
        }
    }

    static void fusion() {
        for (IceBerg curr : arr_list) {
            if (grid[curr.row][curr.col] > curr.cnt) {               //주변의 0 개수만큼 얼음 녹여줌
                grid[curr.row][curr.col] -= curr.cnt;
            } else {
                grid[curr.row][curr.col] = 0;
            }
        }
    }

    static void printMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
