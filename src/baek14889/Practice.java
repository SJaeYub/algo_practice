package baek14889;

import java.util.Scanner;

public class Practice {
    static int n, start, link, result = Integer.MAX_VALUE;
    static int[][] s;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = new int[n][n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = sc.nextInt();
            }
        }

        dfs(0, 0);
        System.out.println(result);

    }

    static void dfs(int start, int cnt) {
        if (cnt == n / 2) {
            cal();
            return;
        }

        for (int i = start; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i + 1, cnt + 1);
                visited[i] = false;
            }
        }
    }

    static void cal() {
        start = 0;
        link = 0;

        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if (visited[i] && visited[j]) {
                    start += s[i][j];
                    start += s[j][i];
                }
                if (!visited[i] && !visited[j]) {
                    link += s[i][j];
                    link += s[j][i];
                }
            }
        }

        int sub = Math.abs(start - link);

        if (sub == 0) {
            result = sub;
            return;
        }
        else {
            result = Math.min(result, sub);
            return;
        }
    }
}
