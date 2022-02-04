package baek1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edges implements Comparable<Edges> {
        int from;
        int to;
        int cost;

        Edges(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edges e1) {
            return Integer.compare(this.cost, e1.cost);
        }
    }

    static int[] parent;
    static ArrayList<Edges> arr_list = new ArrayList<>();
    static int n, m;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int biggest_cost = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arr_list.add(new Edges(from, to, cost));
            arr_list.add(new Edges(to, from, cost));

            biggest_cost = Math.max(cost, biggest_cost);
        }

        Collections.sort(arr_list);

        int answer = 0;
        answer = kruskal(arr_list);

        System.out.println(answer);
    }

    static int kruskal(List<Edges> arr_list) {
        int result = 0;

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int big_cost = 0;
        for (int i = 0; i < arr_list.size(); i++) {
            Edges curr = arr_list.get(i);

            if (!equal(curr.from, curr.to)) {
                merge(curr.from, curr.to);
                result += curr.cost;
                big_cost = curr.cost;
            }
        }

        return result - big_cost;
    }

    static boolean equal(int p, int q) {
        p = find(p);
        q = find(q);

        if (p == q) {
            return true;
        } else {
            return false;
        }
    }

    static int find(int node) {
        if (parent[node] == node) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

    static void merge(int p, int q) {
        p = find(p);
        q = find(q);

        if (p != q) {
            parent[q] = p;
        }
    }
}
