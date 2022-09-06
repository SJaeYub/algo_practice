package swea3316;

import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {
    static int[][] dp;
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea3316/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
//        sc.nextLine();

        for (int test_case = 1; test_case <= T; test_case++) {
            String keys = sc.next();

            dp = new int[keys.length()][16];
            first_day(keys, 4);

            for (int i = 1; i < keys.length(); i++) {
                other_day(keys, i);
            }

            int sol = sol();
            System.out.println("#" + test_case + " " + sol);
        }
    }

    static int sol() {
        int sum = 0;
        for (int i = 1; i < 16; i++) {
            sum += dp[dp.length - 1][i];
            sum %= 1000000007;
        }

        return sum;
    }

    static void other_day(String keys, int day) {
        int key = 1 << (keys.charAt(day) - 'A');

        for (int i = 1; i < 16; i++) {
            if (dp[day - 1][i] != 0) {
                for (int j = 1; j < 16; j++) {
                    if ((j & i) != 0 && (j & key) != 0) {
                        dp[day][j] += dp[day - 1][i];
                        dp[day][j] %= 1000000007;
                    }
                }
            }
        }
    }

    static void first_day(String keys, int size) {
        int key = 1 << (keys.charAt(0) - 'A');
        for (int i = 1; i < 16; i++) {
            if ((i & key) != 0 && (i & 1) != 0) {
                dp[0][i] = 1;
            }
        }
    }
}