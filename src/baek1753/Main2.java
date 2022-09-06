package baek1753;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int t, int w) {
            this.to = t;
            this.weight = w;
        }

        @Override
        public int compareTo(Node n1) {
            return Integer.compare(this.weight, n1.weight);
        }
    }

    static int v, e, k;
    static ArrayList<Node>[] arr_list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();
        k = sc.nextInt();

        arr_list = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            arr_list[from].add(new Node(to, cost));
        }

        int[] targets = dijkstra(k);

        for (int i = 1; i <= v; i++) {
            int ans_tmp = targets[i];
            if (ans_tmp != Integer.MAX_VALUE) {
                System.out.println(ans_tmp);
            } else {
                System.out.println("INF");
            }
        }
    }

    static int[] dijkstra(int start) {
        int[] result = new int[v + 1];
        boolean[] visited = new boolean[v + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visited[curr.to]) {
                continue;
            }

            for (Node next : arr_list[curr.to]) {
                if (result[next.to] > result[curr.to] + next.weight) {
                    result[next.to] = result[curr.to] + next.weight;
                    pq.add(new Node(next.to, result[next.to]));
                }
            }

            visited[curr.to] = true;
        }

        return result;
    }
}
