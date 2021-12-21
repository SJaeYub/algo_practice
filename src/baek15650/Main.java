package baek15650;

import java.util.Scanner;

public class Main {

    static boolean[] visited;
    static int[] answer;
    static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        visited = new boolean[n];
        answer = new int[m];
        dfs(0);
    }

    private static void dfs(int idx) {
        if (idx == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(answer[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (idx > 0) {
                    if (i + 1 > answer[idx - 1]) {
                        visited[i] = true;
                        answer[idx] = i + 1;
                        dfs(idx + 1);
                        visited[i] = false;
                    }
                } else {
                    visited[i] = true;
                    answer[idx] = i + 1;
                    dfs(idx + 1);
                    visited[i] = false;
                }
            }
        }
    }

}
