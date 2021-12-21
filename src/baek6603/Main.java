package baek6603;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int k = 1;
    static int[] s;
    static int[] ans;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            k = sc.nextInt();
            if (k == 0) {
                break;
            }
            s = new int[k];

            for (int i = 0; i < k; i++) {
                s[i] = sc.nextInt();
            }
            ans = new int[6];
            visited = new boolean[k];
            dfs(0);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        return;
    }

    private static void dfs(int idx) throws IOException {
        if (idx == 6) {
            int i = 0;
            while(i < ans.length) {
                bw.write(Integer.toString(ans[i]) + " ");
                i++;
            }
            bw.newLine();

            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                if (idx > 0 && ans[idx - 1] < s[i]) {
                    ans[idx] = s[i];
                    visited[i] = true;
                    dfs(idx + 1);
                    visited[i] = false;
                }
                if (idx == 0) {
                    ans[idx] = s[i];
                    visited[i] = true;
                    dfs(idx + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
