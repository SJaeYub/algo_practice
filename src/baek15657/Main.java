package baek15657;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        dfs(new int[m], arr, 0, n);
    }

    static void dfs(int[] ans, int[] arr, int cnt, int n) {
        if (cnt == ans.length) {
            for (int i : ans) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (cnt == 0) {
                ans[cnt] = arr[i];
                dfs(ans, arr, cnt + 1, n);
            }
            if (cnt > 0 && arr[i] >= ans[cnt - 1]) {
                ans[cnt] = arr[i];
                dfs(ans, arr, cnt + 1, n);
            }

        }

    }
}
