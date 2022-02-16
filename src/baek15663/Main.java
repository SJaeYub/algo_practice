package baek15663;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static int n, m;
    static int[] arr;
    static boolean[] visited;
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        dfs(0, new int[m]);
    }

    static void dfs(int cnt, int[] ans) {
        if (cnt == m) {
            String answer = Arrays.toString(ans);
            if (!set.contains(answer)) {
                set.add(answer);
                StringBuilder sb = new StringBuilder();
                for (int an : ans) {
                    sb.append(String.valueOf(an) + " ");
                }
                System.out.println(sb);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans[cnt] = arr[i];
                dfs(cnt + 1, ans);
                visited[i] = false;
            }
        }
    }
}
