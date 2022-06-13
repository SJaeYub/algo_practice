package baek15651;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Answer {

    static int[] answer;
    static int n, m;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        answer = new int[m];
        dfs(0);
        bw.flush();
    }

    private static void dfs(int idx) throws IOException {
        if (idx == m) {
            for (int i = 0; i < m; i++) {
                bw.write(Integer.toString(answer[i]) + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 0; i < n; i++) {
            answer[idx] = i + 1;
            dfs(idx + 1);
        }
    }

}
