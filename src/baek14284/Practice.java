package baek14284;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Practice {

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int t, int w) {
            this.to = t;
            this.weight = w;
        }

        @Override
        public int compareTo(Node v1) {
            return Integer.compare(this.weight, v1.weight);
        }
    }

    static int n, m;
    static ArrayList<Node>[] arr_list;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr_list = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            arr_list[from].add(new Node(to, weight));
            arr_list[to].add(new Node(from, weight));
        }

        int start = sc.nextInt();
        int objective = sc.nextInt();

        Node[] ans = dijkstra(start);

        System.out.println(ans[objective].weight);
    }

    static Node[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        Node[] d = new Node[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i == start) {
                d[i] = new Node(i, 0);
            } else {
                d[i] = new Node(i, Integer.MAX_VALUE);
            }
            pq.add(d[i]);
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            for (Node next : arr_list[curr.to]) {
                if (!visited[next.to]) {
                    if (d[next.to].weight > d[curr.to].weight + next.weight) {
                        d[next.to].weight = d[curr.to].weight + next.weight;
                        pq.remove(d[next.to]);
                        pq.add(d[next.to]);
                    }
                }
            }

            visited[curr.to] = true;
        }

        return d;
    }
}
