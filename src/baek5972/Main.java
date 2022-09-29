package baek5972;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int t, int c) {
            this.to = t;
            this.cost = c;
        }

        @Override
        public int compareTo(Node n1) {
            return Integer.compare(this.cost, n1.cost);
        }
    }

    static ArrayList<Node>[] arr_list;
    static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        int target = N;
        arr_list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            arr_list[from].add(new Node(to, cost));
            arr_list[to].add(new Node(from, cost));
        }

        System.out.println(dijkstra(1, target));
    }

    static int dijkstra(int start, int target) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node[] d = new Node[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if(i == start) {
                d[i] = new Node(i, 0);
            } else {
                d[i] = new Node(i, 50000001);
            }
            pq.add(d[i]);
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            for (Node next : arr_list[curr.to]) {
                if (!visited[next.to]) {
                    if (d[next.to].cost > d[curr.to].cost + next.cost) {
                        d[next.to].cost = d[curr.to].cost + next.cost;
                        pq.remove(d[next.to]);
                        pq.add(d[next.to]);
                    }
                }
            }

            visited[curr.to] = true;
        }

        return d[target].cost;
    }
}
