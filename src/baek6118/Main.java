package baek6118;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Position {
        int node;
        int dist;

        Position(int a, int b) {
            this.node = a;
            this.dist = b;
        }
    }

    static int n, m;
    static ArrayList<Integer>[] arr_list;
    static Queue<Position> q = new LinkedList<>();
    static int[] list;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr_list = new ArrayList[m + 2];
        visited = new boolean[m + 2];
        list = new int[m + 2];

        for (int i = 1; i < m + 2; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            arr_list[from].add(to);
            arr_list[to].add(from);
        }

        bfs(1);

        int ans_num = 0, ans_dist = max, numOfAns = 0;
        for (int i = 1; i <= m + 1; i++) {
            if (list[i] == max) {
                ans_num = i;
                break;
            }
        }

        for (int i = 1; i <= m + 1; i++) {
            if (list[i] == max) {
                numOfAns++;
            }
        }

        System.out.println(ans_num + " " + ans_dist + " " + numOfAns);
    }

    private static void bfs(int start) {
        Position start_node = new Position(start, 0);
        q.add(start_node);

        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.node] = true;
            list[curr.node] = curr.dist;
            max = Math.max(curr.dist, max);

            for (int i = 0; i < arr_list[curr.node].size(); i++) {
                int num = arr_list[curr.node].get(i);
                if (!visited[num]) {
                    Position temp = new Position(num, curr.dist + 1);
                    visited[num] = true;
                    q.add(temp);
                }
            }
        }
    }
}
