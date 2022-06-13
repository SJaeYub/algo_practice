package baek15652;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Answer {

    static int n, m;
    static int[] ans;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        ans = new int[m];
        dfs(0);

        bw.flush();
    }

    private static void dfs (int idx) throws IOException {
        if (idx == m) {
            for (int i = 0; i < m; i++) {
                bw.write(Integer.toString(ans[i]) + " ");
            }
            bw.newLine();

            return;
        }

        for (int i = 0; i < n; i++) {
            if (idx > 0) {
                if (ans[idx - 1] <= i + 1) {
                    ans[idx] = i + 1;
                    dfs(idx + 1);
                }
            } else {
                ans[idx] = i + 1;
                dfs(idx + 1);
            }
        }
    }
}
