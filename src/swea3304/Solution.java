package swea3304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea3304/sample_input.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            String front = st.nextToken();
            String rear = st.nextToken();

            int[][] dp = new int[front.length() + 1][rear.length() + 1];
            int result = -1;

            for (int i = 1; i <= front.length(); i++) {
                for (int j = 1; j <= rear.length(); j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 0;
                    }
                    if (front.charAt(i-1) != rear.charAt(j-1)) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                        result = Math.max(result, dp[i][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        result = Math.max(result, dp[i][j]);
                    }
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }
}