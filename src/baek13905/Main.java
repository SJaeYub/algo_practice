package baek13905;

import java.util.ArrayList;
import java.util.Collections;
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

        Edge(int f, int t, int w) {
            this.from = f;
            this.to = t;
            this.weight = w;
        }

        @Override
        public int compareTo(Edge e1) {
            return Integer.compare(this.weight, e1.weight);
        }
    }

    static int[] parents;
    static ArrayList<Edge> arr_list;
    static boolean[] visited;
    static ArrayList<Node>[] arr_list2;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt();
        int target = sc.nextInt();

        arr_list = new ArrayList<>();
        parents = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            arr_list.add(new Edge(from, to, weight));
            arr_list.add(new Edge(to, from, weight));
        }

        kruskal(n);

        if(1 != dfs(start, target, 1)) {
            System.out.println(ans);
        } else {
            System.out.println(0);
        }

    }

    static int dfs(int start, int target, int cnt) {
        visited[start] = true;

        if (start == target) {
            return ans = cnt;
        }

        for(Node curr : arr_list2[start]) {
            if(!visited[curr.to]) {
                visited[curr.to] = true;
                dfs(curr.to, target, cnt + 1);
                visited[curr.to] = false;
            }
        }
        return ans;
    }

    static void kruskal(int n) {
        Collections.sort(arr_list);

        arr_list2 = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            arr_list2[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            makeSet(i);
        }

        for (Edge curr : arr_list) {
            if (!equal(curr.from, curr.to)) {
                unionSet(curr.from, curr.to);
                arr_list2[curr.from].add(new Node(curr.to, curr.weight));
                arr_list2[curr.to].add(new Node(curr.from, curr.weight));
            }
        }
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
