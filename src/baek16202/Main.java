package baek16202;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static class Node {
        int to;
        int cost;

        Node(int t, int c) {
            this.to = t;
            this.cost = c;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;
        boolean flag;

        Edge(int f, int t, int w) {
            this.from = f;
            this.to = t;
            this.weight = w;
            this.flag = false;
        }

        @Override
        public int compareTo(Edge e1) {
            return Integer.compare(this.weight, e1.weight);
        }
    }

    static int n, m, k, min_cost;
    static PriorityQueue<Edge> arr_list;
    static int[] parents;
    static Edge min_edge;
    static boolean ans_flag = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        arr_list = new PriorityQueue<>();
        parents = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = i;

            arr_list.add(new Edge(from, to, weight));
        }

        for (int i = 0; i < k; i++) {
            PriorityQueue<Edge> result_pq = kruskal(arr_list);
            PriorityQueue<Edge> temp = new PriorityQueue<>();

            if (ans_flag) {
                System.out.print(0 + " ");
                continue;
            }

            System.out.print(min_cost + " ");

            int cnt = 0;
            while (!result_pq.isEmpty()) {
                Edge curr = result_pq.poll();

                if (cnt < 1 && curr.flag) {
                    cnt++;
                    continue;
                }

                curr.flag = false;
                temp.add(curr);
            }

            arr_list = temp;
        }


    }

    static PriorityQueue<Edge> kruskal(PriorityQueue<Edge> arr_temp) {
        min_cost = 0;
        min_edge = new Edge(0, 0, 499501);
        PriorityQueue<Edge> result = new PriorityQueue();

        for (int i = 1; i <= n; i++) {
            makeSet(i);
        }

        int edge_cnt = 0;
        while (!arr_temp.isEmpty()) {
            Edge curr = arr_temp.poll();

            if (!equal(curr.from, curr.to)) {
                min_cost += curr.weight;
                edge_cnt++;
                unionSet(curr.from, curr.to);
                curr.flag = true;

                if (curr.weight < min_edge.weight) {
                    min_edge = new Edge(curr.from, curr.to, curr.weight);
                }
            }

            result.add(curr);
        }

        if (edge_cnt != n - 1) {
            ans_flag = true;
        }
        return result;
    }

    static boolean equal(int u, int v) {
        u = findSet(u);
        v = findSet(v);

        if (u == v) {
            return true;
        } else {
            return false;
        }
    }

    static void makeSet(int v) {
        parents[v] = v;
    }

    static void unionSet(int u, int v) {
        parents[findSet(u)] = findSet(v);
    }

    static int findSet(int v) {
        if (v == parents[v]) {
            return v;
        } else {
            return parents[v] = findSet(parents[v]);
        }
    }
}
