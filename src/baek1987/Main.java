package baek1987;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static int r, c, ans = -1;
    static char[][] map;
    static boolean[][] visited;
    static HashSet<Character> hSet = new HashSet<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            char[] temp = sc.next().toCharArray();
//            System.out.println(temp);
            for (int j = 0; j < c; j++) {
                map[i][j] = temp[j];
//                System.out.print(map[i][j]);
            }
        }

        dfs(0, 0, 1, hSet);

        System.out.println(ans);
        return;
    }

    static void dfs(int start_r, int start_c, int cnt, HashSet<Character> hSet_tmp) {
        visited[start_r][start_c] = true;
        hSet_tmp.add(map[start_r][start_c]);

//        System.out.println(cnt + " " + map[start_r][start_c]);

        for (int i = 0; i < 4; i++) {
            int mr = start_r + dr[i];
            int mc = start_c + dc[i];

            if (mr >= 0 && mr < r && mc >= 0 && mc < c) {
                if (!visited[mr][mc]) {
                    if (!hSet_tmp.contains(map[mr][mc])) {
                        visited[mr][mc] = true;
                        dfs(mr, mc, cnt + 1, hSet_tmp);
                        visited[mr][mc] = false;
                    }
                }
            }
        }

        hSet_tmp.remove(map[start_r][start_c]);
        visited[start_r][start_c] = false;
        ans = Math.max(ans, cnt);
        return;
    }
}
