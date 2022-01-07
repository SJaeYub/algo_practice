package baek11559;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Position {
        int row;
        int col;
        char color;

        Position(int row, int col, char color) {
            this.row = row;
            this.col = col;
            this.color = color;
        }
    }

    static char[][] map = new char[12][6];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Position> q = new LinkedList<>();
    static boolean[][] visited = new boolean[12][6];
    static ArrayList<Position> arr_list = new ArrayList<>();
    static boolean flag = true;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 12; i++) {
            String temp = sc.next();
            for (int j = 0; j < 6; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        while(flag) {
            flag = false;
            visited = new boolean[12][6];
            for (int j = 0; j < 12; j++) {
                for (int i = 0; i < 6; i++) {
                    if (map[j][i] != '.') {
                        arr_list.clear();
                        bfs(j, i, map[j][i]);

                        if (arr_list.size() >= 4) {
                            flag = true;
                            boom(arr_list);
                        }
                    }
                }
            }

            if (!flag) {
                break;
            }
            pull();
            ans++;
        }

        System.out.println(ans);

    }

    static void bfs(int start_r, int start_c, char color) {
        q.add(new Position(start_r, start_c, color));
        arr_list.clear();

        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.row][curr.col] = true;
            arr_list.add(curr);

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < 12 && mc >= 0 && mc < 6) {
                    if (!visited[mr][mc] && map[mr][mc] == curr.color) {
                        visited[mr][mc] = true;
                        q.add(new Position(mr, mc, map[mr][mc]));
                    }
                }
            }
        }
    }

    static void boom(ArrayList<Position> list) {
        for (Position curr : list) {
            map[curr.row][curr.col] = '.';
        }
    }

    static void pull() {
        for (int k = 0; k < 6; k++) {
            for (int i = 11; i > 0; i--) {
                if (map[i][k] == '.') {
                    for (int j = i - 1; j >= 0; j--) {
                        if (map[j][k] != '.') {
                            map[i][k] = map[j][k];
                            map[j][k] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }
}
