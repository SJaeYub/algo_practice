package baek14284;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int w) {
            this.to = to;
            this.weight = w;
        }

        @Override
        public int compareTo(Node v1) {
            return Integer.compare(this.weight, v1.weight);
        }
    }

    static int n, m;
    static ArrayList<Node>[] arr_list;
    static int target1;
    static int target2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr_list = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            arr_list[start].add(new Node(to, weight));
            arr_list[to].add(new Node(start, weight));
        }

        target1 = sc.nextInt();
        target2 = sc.nextInt();

        Node[] arr = dijkstra();

        System.out.println(arr[target2].weight);
    }

    static Node[] dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        Node[] d = new Node[n + 1];


        for (int i = 0; i <= n; i++) {
            if (i == target1) {
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
