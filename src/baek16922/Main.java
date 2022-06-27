package baek16922;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    //    static char[] alphabet = {'I', 'V', 'X', 'L'};
    static int n, ans = 0;
    static HashSet<Integer> hSet = new HashSet<>();
    static boolean[] visited = new boolean[1001];
    static int[] arr = {1, 5, 10, 50};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int i = 0; i < 4; i++) {
            dfs(i, 0, 0);
        }

        System.out.println(ans);
    }

    private static void dfs(int alp, int cnt, int sum) {
        if (cnt == n) {
            if (!visited[sum]) {                  //들어있지 않으면
                visited[sum] = true;
                ans++;
                return;
            } else {        //들어있으면
                return;
            }
        }

        for (int i = alp; i < 4; i++) {
            dfs(i, cnt + 1, sum + arr[alp]);
        }

    }
}
