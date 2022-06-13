package baek15651;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] ans;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        ans = new int[m];

        dfs(0);
        bw.flush();
    }

    static void dfs(int cnt) throws IOException {
        if (cnt == m) {
            for (int an : ans) {
//                System.out.print(an + " ");
                bw.write(an + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 1; i <= n; i++) {
//            if (!visited[i]) {
                visited[i] = true;
                ans[cnt] = i;
                dfs(cnt + 1);
                visited[i] = false;
//            }
        }
    }
}
