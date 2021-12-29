package baek5427;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Fire {
        int row;
        int col;
        int status;

        Fire(int a, int b, int c) {
            this.row = a;
            this.col = b;
            this.status = c;
        }
    }

    static class Position {
        int row;
        int col;
        int cnt;
        char[][] temp_map;
        boolean[][] visited;

        Position(int a, int b, int c, char[][] d, boolean[][] e) {
            this.row = a;
            this.col = b;
            this.cnt = c;
            this.temp_map = d;
            this.visited = e;
        }
    }

    static int w, h;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static Queue<Position> q = new LinkedList<>();
//    static ArrayList<Fire> arr_list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int test_case = 0; test_case < t; test_case++) {
            q.clear();
//            arr_list.clear();

            w = sc.nextInt();
            h = sc.nextInt();

            char[][] map = new char[h][w];
            visited = new boolean[h][w];

            int temp_start_row = 0, temp_start_col = 0;

            for (int i = 0; i < h; i++) {
                String temp = sc.next();
                for (int j = 0; j < temp.length(); j++) {
                    map[i][j] = temp.charAt(j);

                    if (map[i][j] == '@') {
                        temp_start_row = i;
                        temp_start_col = j;
                    }
                }
            }

            q.add(new Position(temp_start_row, temp_start_col, 0, map, visited));

            bfs();

        }
    }

    private static void bfs() {

        while (!q.isEmpty()) {
            Position curr = q.poll();
            char[][] temp2 = curr.temp_map;
            firing(temp2);
            ArrayList<Fire> fire_list = check_fire(temp2);
            expect_fire(fire_list, temp2);
            curr.visited[curr.row][curr.col] = true;
            temp2[curr.row][curr.col] = '@';

            System.out.println("-----------------------------------");

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(temp2[i][j]);
                }
                System.out.println();
            }

            if (curr.row == w - 1 || curr.col == h - 1) {
                System.out.println(curr.cnt + 2);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < h && mc >= 0 && mc < w) {
                    if (temp2[mr][mc] == '.' || temp2[mr][mc] == 0) {
                        if (!curr.visited[mr][mc]) {
                            curr.visited[mr][mc] = true;

                            q.add(new Position(mr, mc, curr.cnt + 1, temp2, curr.visited));
                        }
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
        return;
    }

    private static void firing(char[][] temp_map) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (temp_map[i][j] == 0) {
                    temp_map[i][j] = '*';
                }
            }
        }
    }

    private static void expect_fire(ArrayList<Fire> fire_list, char[][] temp_map) {
        for (Fire fire : fire_list) {
            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + fire.row;
                int mc = dc[i] + fire.col;

                if (mr >= 0 && mr < h && mc >= 0 && mc < w) {
                    if (temp_map[mr][mc] == '.' || temp_map[mr][mc] == '@') {
                        temp_map[mr][mc] = 0;
                    }
                }
            }
        }
    }

    private static ArrayList<Fire> check_fire(char[][] map) {

        ArrayList<Fire> arr_list = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == '*') {
                    arr_list.add(new Fire(i, j, 1));
                }
            }
        }

        return arr_list;
    }


}
