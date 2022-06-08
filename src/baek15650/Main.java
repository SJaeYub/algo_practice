package baek15650;

import java.util.Scanner;

public class Main {

    static int n, m;
    static int[] ans;
    static boolean[] visited;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        ans = new int[m];
        visited = new boolean[n + 1];

        dfs(0);
    }

    static void dfs(int cnt) {
        if (cnt == m) {

            for (int an : ans) {
                System.out.print(an + " ");
            }
            System.out.println();

            return;
        }

        for (int i = 1; i <= n; i++) {
            if(!visited[i]) {
                if (cnt > 0) {
                    if (i > ans[cnt - 1]) {
                        visited[i] = true;
                        ans[cnt] = i;
                        dfs(cnt + 1);
                        visited[i] = false;
                    } else {
                        continue;
                    }
                } else {
                    visited[i] = true;
                    ans[cnt] = i;
                    dfs(cnt + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
