package baek1932;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    static int n;
    static int[][] triangle;
    static int[][][] dp;
    static int ans = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = sc.nextInt();
        triangle = new int[n][n];
        dp = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int temp = sc.nextInt();
                triangle[i][j] = temp;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                triangle[i - 1][j] += Math.max(triangle[i][j], triangle[i][j + 1]);
            }
        }

            System.out.println(triangle[0][0]);
    }

}
