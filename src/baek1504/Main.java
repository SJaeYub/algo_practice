package baek1504;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

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

    static int n, e;
    static ArrayList<Node>[] arr_list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        e = sc.nextInt();

        arr_list = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            arr_list[from].add(new Node(to, weight));
            arr_list[to].add(new Node(from, weight));
        }

        int first = sc.nextInt();
        int second = sc.nextInt();

        int res1 = 0;
        res1 += dijkstra(1, first);
        res1 += dijkstra(first, second);
        res1 += dijkstra(second, n);

        int res2 = 0;
        res2 += dijkstra(1, second);
        res2 += dijkstra(second, first);
        res2 += dijkstra(first, n);

        int ans = (res1 >= 200000000 && res2 >= 200000000) ? -1 : Math.min(res1, res2);
        System.out.println(ans);
    }

    static int dijkstra(int f, int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        Node[] d = new Node[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i == f) {
                d[i] = new Node(i, 0);
            } else {
                d[i] = new Node(i, 200000000);
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

        return d[s].weight;
    }
}
