package swea8935;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {

    static int MAXN = 3000;
    static int MAXM = 100;

    static int N,M;
    static int[] A = new int[MAXN];
    static int[] B = new int[MAXM];
    static int[][][][] dp = new int[MAXN + 1][MAXM + 1][MAXM + 1][2];

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea8935/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            for (int n = 0; n < N; n++) {
                A[n] = sc.nextInt();
            }
            M = sc.nextInt();
            for (int m = 0; m < M; m++) {
                B[m] = sc.nextInt();
            }

            for (int n = 0; n <= N; n++) {
                for (int m = 0; m <= M; m++) {
                    for (int m2 = 0; m2 <= M; m2++) {
                        dp[n][m][m2][0] = -1;
                        dp[n][m][m2][1] = -1;
                    }
                }
            }

            Arrays.sort(B);

            int max = 0;
            for (int l = 0; l <= M; l++) {
                int r = M - l;
                if(find(N,l,r,0) > max) {
                    max = dp[N][l][r][0];
                }
                if(find(N,l,r,1) > max) {
                    max = dp[N][l][r][1];
                }
            }

            System.out.println("#" + test_case + " " + max);
        }
    }

    static int find(int idx, int l, int r, int take) {
        if (dp[idx][l][r][take] != -1) {
            return dp[idx][l][r][take];
        }
        if (idx == 0 && l == 0) {
            return dp[idx][l][r][take] = 0;
        }
        if (l + r > M) {
            return dp[idx][l][r][take] = 0;
        }

        int val;
        if (take == 1) {
            int f1 = 0, f2 = 0;
            if (idx > 0) {
                f1 = find(idx - 1, l, r, 0) + A[idx - 1];
            }
            if (l > 0) {
                f2 = find(idx, l - 1, r, 0) + B[M - 1];
            }
            val = (f1 > f2) ? f1 : f2;
        } else {
            int f1 = 0, f2 = 0, f3 = 0, f4 = 0;
            if (idx > 0) {
                f1 = find(idx - 1, l, r, 0);
                f2 = find(idx - 1, l, r, 1);
            }
            if (r > 0) {
                f3 = find(idx, l, r - 1, 0);
                f4 = find(idx, l, r - 1, 1);
            }
            val = (f1 > f2) ? f1 : f2;
            if(f3 > val) val = f3;
            if(f4 > val) val = f4;
        }
        return dp[idx][l][r][take] = val;
    }
}