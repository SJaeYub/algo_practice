package baek3980;

import java.util.Scanner;

public class Main {

    static int c;
    static int[][] s;
    static int ans;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        c = sc.nextInt();
        for (int test_case = 0; test_case < c; test_case++) {
            ans = Integer.MIN_VALUE;
            s = new int[11][11];
            arr = new int[11];
            visited = new boolean[11];

            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    s[i][j] = sc.nextInt();
                }
            }

            dfs(0);

            System.out.println(ans);
        }
    }

    private static void dfs(int idx) {
        if (idx == 11) {
            int result = 0;
            for (int i = 0; i < 11; i++) {
                result += arr[i];
            }
            ans = Math.max(ans, result);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (!visited[i] && s[i][idx] != 0) {
                arr[idx] = s[i][idx];
                visited[i] = true;
                dfs(idx + 1);
                visited[i] = false;
            }
        }
    }
}
