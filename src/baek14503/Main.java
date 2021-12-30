package baek14503;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Position {
        int row;
        int col;
        int dir;
        int cnt;

        Position(int a, int b, int c, int d) {
            this.row = a;
            this.col = b;
            this.dir = c;
            this.cnt = d;
        }
    }

    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        visited = new boolean[n][m];
        int start_row = sc.nextInt();
        int start_col = sc.nextInt();
        int start_dir = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    visited[i][j] = true;
                }
            }
        }

        bfs(start_row, start_col, start_dir);


    }

    private static void bfs(int r, int c, int d) {
        visited[r][c] = true;
        q.add(new Position(r, c, d, 1));
        int result = 0;

        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.row][curr.col] = true;
            result = curr.cnt;

            boolean flag = false;

            int next_dir = 0;
            if (curr.dir == 0) {
                next_dir = 3;
            } else if (curr.dir == 1) {
                next_dir = 0;
            } else if (curr.dir == 2) {
                next_dir = 1;
            } else {
                next_dir = 2;
            }

            for (int i = 0; i < 4; i++) {
                int mr, mc;
                mr = curr.row + dr[next_dir];
                mc = curr.col + dc[next_dir];

                if (mr >= 0 && mr < n && mc >= 0 && mc < m) {

                    if (!visited[mr][mc]) {
                        visited[mr][mc] = true;
                        q.add(new Position(mr, mc, next_dir, curr.cnt + 1));
                        flag = true;
                        break;
                    } else {
                        if (next_dir == 0) {
                            next_dir = 3;
                        } else if (next_dir == 1) {
                            next_dir = 0;
                        } else if (next_dir == 2) {
                            next_dir = 1;
                        } else {
                            next_dir = 2;
                        }
                    }

                }
            }

            if (!flag) {            //4방향 다 방문했다면
                next_dir = curr.dir;
                int mr, mc;
                if (next_dir == 0) {
                    mr = curr.row + 1;
                    mc = curr.col;
                } else if (next_dir == 1) {
                    mr = curr.row;
                    mc = curr.col - 1;
                } else if (next_dir == 2) {
                    mr = curr.row - 1;
                    mc = curr.col;
                } else {
                    mr = curr.row;
                    mc = curr.col + 1;
                }

                if (map[mr][mc] != 1) {
                    visited[mr][mc] = true;
                    q.add(new Position(mr, mc, next_dir, curr.cnt));
                }
            }
        }
        System.out.println(result);
    }
}
