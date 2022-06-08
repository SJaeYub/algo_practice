package baek15649;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static boolean[] visited;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        ans = new int[m];

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            ans[0] = i;
            dfs(i, ans, 1);
        }
    }

    static void dfs(int input, int[] ans, int cnt) {
        visited[input] = true;
        if (cnt == m) {
            for (int i = 0; i < ans.length; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                ans[cnt] = i;
                dfs(i, ans, cnt + 1);
                visited[i] = false;
            }
        }

        return;
    }
}
