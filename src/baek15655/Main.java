package baek15655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;
    static StringTokenizer st;
    static boolean[] visited;
    static int[] ans_arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        arr = new int[n];
        ans_arr = new int[m];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0);
    }

    static void dfs(int idx) {
        if (idx == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(ans_arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = idx; i < n; i++) {

            if (!visited[i]) {
                if (idx != 0) {
                    if (ans_arr[idx - 1] < arr[i] && ans_arr[idx - 1] != arr[i]) {
                        ans_arr[idx] = arr[i];
                    } else {
                        continue;
                    }
                } else {
                    ans_arr[idx] = arr[i];
                }
                visited[i] = true;
                dfs(idx + 1);
                visited[i] = false;
            }
        }
    }
}
