package baek16918;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Bomb {
        int row;
        int col;
        int cnt;

        Bomb(int r, int c, int n) {
            this.row = r;
            this.col = c;
            this.cnt = n;
        }
    }

    static int r, c, n;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][] visited;
    static ArrayList<Bomb> bomb_list = new ArrayList<>();
    static Queue<Bomb> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        n = sc.nextInt();

        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String temp = sc.next();
            for (int j = 0; j < c; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'O') {
                    bomb_list.add(new Bomb(i, j, 1));
                    visited[i][j] = true;
                } else {
                    visited[i][j] = false;
                }
            }
        }

        int timer = 1;
        timer++;
        while(timer < n) {

            if (timer % 2 == 0) {
                install_bomb();
            } else {
                explosion();
            }
            timer++;
            if (timer >= n) {
                for (int k = 0; k < r; k++) {
                    for (int j = 0; j < c; j++) {
                        System.out.print(map[k][j]);
                    }
                    System.out.println();
                }
            }
        }

    }

    private static void install_bomb() {
        q.clear();
        boolean flag = false;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    q.add(new Bomb(i, j, 1));
                    flag = true;
                    break;
                }
                if (flag) {
                    break;
                }
            }
        }

        while (!q.isEmpty()) {
            Bomb curr = q.poll();
            visited[curr.row][curr.col] = true;
            map[curr.row][curr.col] = 'O';

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < r && mc >= 0 && mc < c) {
                    if (!visited[mr][mc]) {
                        visited[mr][mc] = true;
                        map[curr.row][curr.col] = 'O';
                        q.add(new Bomb(mr, mc, 1));
                    }
                }
            }
        }

    }

    private static void search_bomb() {

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visited[i][j]) {
                    bomb_list.add(new Bomb(i, j, 1));
                }
            }
        }
    }

    private static void explosion() {

        for (Bomb curr : bomb_list) {
            map[curr.row][curr.col] = '.';
            visited[curr.row][curr.col] = false;

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < r && mc >= 0 && mc < c) {
                    map[mr][mc] = '.';
                    visited[mr][mc] = false;
                }
            }
        }
        bomb_list.clear();
    }


}
