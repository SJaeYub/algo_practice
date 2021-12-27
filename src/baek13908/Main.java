package baek13908;

import java.util.Scanner;

public class Main {

    static int n, m, cnt = 0;
    static int[] arr;
    static boolean[] visited = new boolean[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];

        for (int i = 0; i < m; i++) {
            visited[sc.nextInt()] = true;
        }

        dfs(0, arr);

        System.out.println(cnt);
    }

    private static void dfs(int idx, int[] str) {
        if (idx == n) {
            boolean flag = false;
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    flag = false;
                    for (int j = 0; j < str.length; j++) {
                        if (i == str[j]) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        return;
                    }
                }
            }
            cnt++;
            return;

        }

        for (int i = 0; i <= 9; i++) {
            str[idx] = i;
            dfs(idx + 1, str);
        }
    }
}
