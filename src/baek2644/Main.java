package baek2644;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer>[] arr_list;
    static boolean flag;
    static int ans = -1;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int from = sc.nextInt();
        int to = sc.nextInt();
        int m = sc.nextInt();

        flag = false;
        arr_list = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int person1 = sc.nextInt();
            int person2 = sc.nextInt();

            arr_list[person1].add(person2);
            arr_list[person2].add(person1);
        }

        dfs(from, to,0);

        System.out.println(ans);
    }

    static void dfs(int from, int target, int cnt) {

        visited[from] = true;

        if(from == target) {
            flag = true;
            ans = cnt;
            return;
        }

        for(int next : arr_list[from]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, target, cnt + 1);
                visited[next] = false;
                if(flag) {
                    return;
                }
            }
        }

        return;
    }
}
