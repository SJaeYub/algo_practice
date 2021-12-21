package baek1010;

import java.util.Scanner;

public class Main {

    static int[][] dp = new int[30][30];

    public static void main(String[] args) {
        int n, m;
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int test_case = 0; test_case < t; test_case++) {
            n = sc.nextInt();
            m = sc.nextInt();

            System.out.println(combi(m, n));
        }
    }

    private static int combi(int n, int r) {
        if (dp[n][r] > 0) {
            return dp[n][r];
        }

        if (n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
    }
}
