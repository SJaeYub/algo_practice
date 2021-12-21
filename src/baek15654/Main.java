package baek15654;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int n, m;
    private static int[] arr, ans;
    private static boolean[] visited;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];
        ans = new int[m];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        dfs(0);

        bw.flush();
        bw.close();
    }

    private static void dfs(int idx) throws IOException {
        if (idx == m) {
            for (int an : ans) {
                bw.write(an + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans[idx] = arr[i];
                dfs(idx + 1);
                visited[i] = false;
            }
        }
    }
}
