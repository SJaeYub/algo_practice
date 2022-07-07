package swea2001;

import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            int ans = 0;
            String s = sc.nextLine();

            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] arr = new int[20][20], sum = new int[20][20];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = sc.nextInt();

                    sum[i][j] = arr[i][j];

                    if (i > 0) {
                        sum[i][j] += sum[i - 1][j];
                    }
                    if (j > 0) {
                        sum[i][j] += sum[i][j - 1];
                    }
                    if (i > 0 && j > 0) {
                        sum[i][j] -= sum[i - 1][j - 1];
                    }
                }
            }

            for (int i = 0; i <= n - m; i++) {
                for (int j = 0; j <= n - m; j++) {
                    int ss = sum[i + m][j + m] - sum[i][j + m] - sum[i + m][j] + sum[i][j];
                    if (ss > ans) {
                        ans = ss;
                    }
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }
}