package swea1244;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;


public class Solution {

    static int n, ans, size;
    static int[] arr;

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea1244/input.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            ans = 0;
            String num = st.nextToken();
            n = Integer.parseInt(st.nextToken());
            size = num.length();
            arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(num.substring(i, i + 1));
            }

            int[] temp = arr;
            dfs(0, 0);

            System.out.println("#" + test_case + " " + ans);
        }
    }

    static void dfs(int depth, int idx) {
        if ((n - depth) % 2 == 0) {
            int result = 0;

            for (int i = 0; i < size; i++) {
                int temp = size - (1 + i);
                if (temp != 0) {
                    result += arr[i] * (Math.pow(10, temp));
                } else {
                    result += arr[i];
                }
            }
            ans = Math.max(ans, result);
        }

        if (depth == n) {

            int result = 0;

            for (int i = 0; i < size; i++) {
                int temp = size - (1 + i);
                if (temp != 0) {
                    result += arr[i] * (Math.pow(10, temp));
                } else {
                    result += arr[i];
                }
            }
            ans = Math.max(ans, result);

            return;
        }

        for (int i = idx; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (arr[j] >= arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;

                    dfs(depth + 1, i);

                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
}