package baek16202;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {

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
    static ArrayList<Edge> arr_list;
    static int[] parents;
    static Edge min_edge;
    static boolean ans_flag = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        arr_list = new ArrayList<>();
        parents = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = i;

            arr_list.add(new Edge(from, to, weight));
        }

        for (int i = 0; i < k; i++) {
            kruskal(arr_list);

            if (ans_flag) {
                System.out.print(0 + " ");
                continue;
            }

            System.out.print(min_cost + " ");

        }


    }

    static void kruskal(ArrayList<Edge> arr_temp) {
        min_cost = 0;
        min_edge = new Edge(0, 0, 499501);

        Collections.sort(arr_temp);

        for (int i = 1; i <= n; i++) {
            makeSet(i);
        }

        int edge_cnt = 0;

        for (Edge curr : arr_temp) {
            if (!equal(curr.from, curr.to)) {
                if(curr.flag) {
                    continue;
                } else {
                    edge_cnt++;
                    curr.flag = true;
                    min_cost += curr.weight;
                }
            }
        }

        if (edge_cnt != n - 1) {
            ans_flag = true;
        }
        return;
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
