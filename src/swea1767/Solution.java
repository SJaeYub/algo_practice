package swea1767;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    static class Core {
        int row;
        int col;

        Core(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    static int[][] map;
    static int n, min = Integer.MAX_VALUE, core_cnt = 0;
    static ArrayList<Core> arr_list;
    static boolean flag;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[] chk;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea1767/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();

            map = new int[n][n];
            arr_list = new ArrayList<>();
            min = Integer.MAX_VALUE;
            core_cnt = 0;
            flag = false;
            chk = new boolean[12];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] == 1) {
                        if (i != 0 && i != n - 1 && j != 0 && j != n - 1) {
                            arr_list.add(new Core(i, j));
                        }
                    }
                }
            }
            core_cnt = arr_list.size();

            for (int i = core_cnt; i >= 0; i--) {
                combination(0, 0, i);
                if (min < Integer.MAX_VALUE) {
                    break;
                }
            }

            System.out.println("#" + test_case + " " + min);
        }
    }

    static void combination(int idx, int cnt, int R) {
        if (R == cnt) {
            dfs(0, 0);
            return;
        }

        for (int i = idx; i < core_cnt; i++) {
            chk[i] = true;
            combination(i + 1, cnt + 1, R);
            chk[i] = false;
        }
    }

    static void dfs(int idx, int cnt) {
        if (idx == core_cnt) {
            flag = true;
            min = Math.min(min, cnt);
            return;
        }

        if (!chk[idx]) {
            dfs(idx + 1, cnt);
            return;
        }

        Core curr = arr_list.get(idx);
        for (int j = 0; j < 4; j++) {
            int curr_row = curr.row;
            int curr_col = curr.col;
            int temp = 0;
            boolean success = false;

            while (true) {
                curr_row += dr[j];
                curr_col += dc[j];

                if (curr_row < 0 || curr_row >= n || curr_col < 0 || curr_col >= n) {
                    success = true;
                    break;
                }

                if (map[curr_row][curr_col] != 0) {
                    break;
                }
                map[curr_row][curr_col] = 2;
                temp++;
            }

            if (success) {
                dfs(idx + 1, cnt + temp);
            }

            while (true) {
                curr_row -= dr[j];
                curr_col -= dc[j];

                if (curr_row == curr.row && curr_col == curr.col) {
                    break;
                }

                map[curr_row][curr_col] = 0;
            }
        }


    }

}