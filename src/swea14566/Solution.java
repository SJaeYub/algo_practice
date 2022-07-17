package swea14566;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution {

    static class Position {
        int row;
        int col;

        Position(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    static int n;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited_g;


    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();           //n입력
            map = new int[n][n];        //배열 생성
            visited_g = new boolean[n][n];

            for (int i = 0; i < n; i++) {       //배열 채우고
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int year = 0;
            int ans = 1;

            while (true) {      //반복문 실행
                year++;         //해수면 증가

                transform_bfs(year);        //맵이 해수면 높이만큼 변함

                int cnt = 0;
                boolean[][] visited_temp = new boolean[n][n];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!visited_temp[i][j] && !visited_g[i][j]) {
                            boolean[][] visited_result = check_bfs(i, j, visited_temp);
                            cnt++;
                            visited_temp = visited_result;
                        }
                    }
                }

                if (cnt == 0) {
                    break;
                }
                ans = Math.max(ans, cnt);
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

    static boolean[][] check_bfs(int start_r, int start_c, boolean[][] visit_temp) {
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited_l = visit_temp;
        Position start = new Position(start_r, start_c);

        q.add(start);

        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited_l[curr.row][curr.col] = true;

            for (int i = 0; i < 4; i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];

                if (mr >= 0 && mr < n && mc >= 0 && mc < n) {
                    if (!visited_l[mr][mc] && !visited_g[mr][mc]) {
                        Position next = new Position(mr, mc);
                        visited_l[mr][mc] = true;
                        q.add(next);
                    }
                }
            }
        }

        return visited_l;
    }

    static void transform_bfs(int year) {       //해수면 변화에 따른 땅 변화
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited_l = new boolean[n][n];
        Position start = new Position(0, 0);

        q.add(start);

        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited_l[curr.row][curr.col] = true;
            int curr_val = map[curr.row][curr.col];

            if (curr_val <= year) {
                visited_g[curr.row][curr.col] = true;
            }

            for (int i = 0; i < 4; i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];

                if (mr >= 0 && mr < n && mc >= 0 && mc < n) {
                    if (!visited_l[mr][mc]) {
                        Position next = new Position(mr, mc);
                        visited_l[mr][mc] = true;
                        q.add(next);
                    }
                }
            }
        }

        return;
    }
}