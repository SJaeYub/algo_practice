package baek2206;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Position {
        int row;
        int col;
        int dist;
        boolean crash;

        Position(int row, int col, int dist, boolean crash) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.crash = crash;
        }
    }

    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        int answer = bfs(0, 0);
        System.out.println(answer);
    }

    static int bfs(int row, int col) {
        int result = 0;
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(row, col, 1, false));

        while (!q.isEmpty()) {
            Position curr = q.poll();

            if (curr.row == n - 1 && curr.col == m - 1) {
                return result = curr.dist;
            }

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < n && mc >= 0 && mc < m) {
                    if (map[mr][mc] == 0) {
                        if (!curr.crash && !visited[mr][mc][0]) {
                            visited[mr][mc][0] = true;
                            q.add(new Position(mr, mc, curr.dist + 1, curr.crash));
                        } else if(curr.crash && !visited[mr][mc][1]) {
                            visited[mr][mc][1] = true;
                            q.add(new Position(mr, mc, curr.dist + 1, curr.crash));
                        }
                    } else if (map[mr][mc] == 1) {
                        if (!curr.crash) {
                            visited[mr][mc][1] = true;
                            curr.crash = true;
                            q.add(new Position(mr, mc, curr.dist + 1, curr.crash));
                            curr.crash = false;
                        }
                    }

                }
            }
        }

        return -1;
    }
}
