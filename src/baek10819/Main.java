package baek10819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int[] result_arr;
    static int[] arr;
    static int n, max = Integer.MIN_VALUE;
    static StringTokenizer st;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        result_arr = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(max);
    }

    static void dfs(int idx) {
        if (idx == n) {
            int result = compute(result_arr);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result_arr[idx] = arr[i];
                dfs(idx + 1);
                visited[i] = false;
            }
        }
    }

    static int compute(int[] arr_tmp) {
        int result = 0;
        for (int i = 0; i < arr_tmp.length - 1; i++) {
            result += Math.abs(arr_tmp[i] - arr_tmp[i + 1]);
        }

        return result;
    }
}
