package swea1288;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {

    static boolean[] arr;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea1288/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int cnt = 1;
            arr = new boolean[10];

            while(true) {
                String number = String.valueOf(cnt * n);


            }
        }
    }
}