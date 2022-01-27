package programmers1829;

import java.util.*;

public class Solution {

    static class Position {
        int row;
        int col;
        int val;

        Position(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int max_row;
    static int max_col;

    public static void main(String[] args) {
        int m = 6;
        int n = 4;

        int[][] pictures = {{1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}};

        System.out.println(Arrays.toString(solution(m, n, pictures)));
    }

    static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        visited = new boolean[m][n];
        max_row = m;
        max_col = n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    answer[0]++;
                    int size = bfs(i, j, picture[i][j], picture);
                    answer[1] = Math.max(answer[1], size);
                }
            }
        }

        return answer;
    }

    static int bfs(int start_row, int start_col, int start_val, int[][] map) {
        Queue<Position> q = new LinkedList<>();

        q.add(new Position(start_row, start_col, start_val));

        int cnt = 0;
        while (!q.isEmpty()) {
            Position curr = q.poll();
            cnt++;
            visited[curr.row][curr.col] = true;

            for (int i = 0; i < 4; i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];

                if (mr < 0 || mr >= max_row || mc < 0 || mc >= max_col) {
                    continue;
                }

                if (!visited[mr][mc] && map[mr][mc] == curr.val) {
                    visited[mr][mc] = true;
                    q.add(new Position(mr, mc, map[mr][mc]));
                }
            }
        }

        return cnt;
    }
}
