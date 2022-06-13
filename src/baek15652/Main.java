package baek15652;

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

//        for (int i = 1; i <= n; i++) {
            dfs(0, 1);
//        }

        bw.flush();
    }

    static void dfs(int cnt, int idx) throws IOException {
        if (cnt == m) {

            for (int an : ans) {
                bw.write(an + " ");
            }
            bw.newLine();

            return;
        }

        for (int i = idx; i <= n; i++) {
            ans[cnt] = i;
            dfs(cnt + 1, i);
        }
    }
}
