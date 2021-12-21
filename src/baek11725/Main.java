package baek11725;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static int n;
    static LinkedList<Integer>[] relations;
    static int[] ans;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        relations = new LinkedList[n];
        visited = new boolean[n];
        ans = new int[n];

        for (int i = 0; i < n; i++) {
            relations[i] = new LinkedList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int temp_y = sc.nextInt() - 1,
                    temp_x = sc.nextInt() - 1;

            relations[temp_y].add(temp_x);
            relations[temp_x].add(temp_y);
        }

        dfs(0, 0);

        for (int i = 1; i < n; i++) {
            System.out.println(ans[i]);
        }
    }

    private static void dfs(int idx, int node) {
        visited[node] = true;

        while (!relations[node].isEmpty()) {
            int temp = relations[node].poll();
            if(!visited[temp]) {
                ans[temp] = node + 1;
                visited[temp] = true;
                dfs(idx + 1, temp);
                visited[temp] = false;
            }

        }

        return;

    }

}
