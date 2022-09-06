package swea1970;

import java.util.Scanner;
import java.io.FileInputStream;


public class Solution {

    static int[] unit = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea1970/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int money = sc.nextInt();

            int[] ans = greedy(money);

            System.out.println("#" + test_case);
            for (int an : ans) {
                System.out.print(an + " ");
            }
            System.out.println();
        }
    }

    static int[] greedy(int pay) {
        int[] result = new int[8];

        for (int i = 0; i < 8; i++) {
            result[i] += pay / unit[i];

            pay %= unit[i];
        }

        return result;
    }
}