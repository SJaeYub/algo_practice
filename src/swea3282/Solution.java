package swea3282;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution {

    static class Knapsack {
        int num;
        int volume;
        int price;
//        int mix_price;

        Knapsack(int n, int v, int p) {
            this.num = n;
            this.volume = v;
            this.price = p;
//            this.mix_price = (p/v) + (p%v);
        }

    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea3282/sample_input.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] knapsack = new int[n+1][2];
            int[][] dp = new int[n + 1][k + 1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int volume = Integer.parseInt(st.nextToken());
                int price = Integer.parseInt(st.nextToken());

                knapsack[i][0] = volume;
                knapsack[i][1] = price;
            }
            int result = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= k; j++) {
                    if (knapsack[i][0] > j) {
                        dp[i][j] = dp[i - 1][j];
                        result = Math.max(result, dp[i][j]);
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], knapsack[i][1] + dp[i - 1][j - knapsack[i][0]]);
                        result = Math.max(result, dp[i][j]);
                    }
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }
}