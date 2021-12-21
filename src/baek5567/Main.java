package baek5567;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static boolean[] check;
    static int n, m, answer;
    static int[][] relations;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        relations = new int[n][n];
        check = new boolean[n];

        for (int i = 0; i < m; i++) {
            int temp = sc.nextInt() - 1;
            int temp2 = sc.nextInt() - 1;
            relations[temp][temp2] = 1;
            relations[temp2][temp] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (relations[0][i] == 1) {
                dfs(i, 0);
            }
            if (relations[i][0] == 1) {
                dfs(i, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            if (check[i]) {
                answer++;
            }
        }

        if(answer != 0) {
            System.out.println(answer - 1);

        } else {
            System.out.println(0);

        }

    }

    private static void dfs(int frnd, int index) {
        if (index == 1) {
            check[frnd] = true;
            return;
        }

        check[frnd] = true;
        for (int i = 0; i < n; i++) {
            if (relations[frnd][i] == 1) {
                dfs(i, index + 1);
            }
        }

    }
}
