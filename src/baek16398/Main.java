package baek16398;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        Edge(int f, int t, int c) {
            this.from = f;
            this.to = t;
            this.cost = c;
        }

        @Override
        public int compareTo(Edge e1) {
            return Integer.compare(this.cost, e1.cost);
        }
    }

    static int[] parents;
    static int n;
    static ArrayList<Edge> arr_list;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        parents = new int[n + 1];
        arr_list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            makeSet(i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    arr_list.add(new Edge(i + 1, j + 1, sc.nextInt()));
                } else {
                    sc.nextInt();
                    continue;
                }
            }
        }

        long min_cost = kruskal();
        System.out.println(min_cost);
    }

    static long kruskal() {
        long result = 0;

        Collections.sort(arr_list);

        for (Edge curr : arr_list) {
            if (!equal(curr.from, curr.to)) {
                unionSet(curr.from, curr.to);
                result += curr.cost;
            }
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
        if( v== parents[v]) {
            return parents[v];
        } else {
            return parents[v] = findSet(parents[v]);
        }
    }
}
