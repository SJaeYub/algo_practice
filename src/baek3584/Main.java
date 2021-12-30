package baek3584;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    /*
    트리인 경우엔 단방향으로 해서 child->parent 나 그 반대 순서로 탐색하자
     */
    static ArrayList<Integer>[] arr_list;
    static int n = 0;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int test_case = 0; test_case < t; test_case++) {
            n = sc.nextInt();
            arr_list = new ArrayList[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                arr_list[i] = new ArrayList<>();
            }

            for (int i = 0; i < n - 1; i++) {
                int parent = sc.nextInt();
                int child = sc.nextInt();

                arr_list[child].add(parent);
            }

            int target_node1 = sc.nextInt();
            int target_node2 = sc.nextInt();

            visited[target_node1] = true;
            bfs(target_node1);

            check(target_node2);
        }
    }

    private static void check(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (visited[curr]) {
                System.out.println(curr);
                return;
            }

            for (int next : arr_list[curr]) {
                q.add(next);
            }
        }
    }

    private static void bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            int curr = q.poll();
            visited[curr] = true;

            for (int next : arr_list[curr]) {
                visited[next] = true;
                q.add(next);
            }
        }
    }
}
