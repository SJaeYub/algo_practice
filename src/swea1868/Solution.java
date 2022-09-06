package swea1868;

import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {

    static char[][] map;
    static int n;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea1868/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            map = new char[n][n];

            for (int i = 0; i < n; i++) {
                String input = sc.next();
                for (int j = 0; j < n; j++) {
                    map[i][j] = input.charAt(j);
                }
            }


        }
    }

    static void dfs(int row, int col, int cnt) {
        
    }
}