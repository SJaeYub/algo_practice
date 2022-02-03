package baek6497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Edges implements Comparable<Edges> {
        int from;
        int to;
        int weight;

        Edges(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edges e1) {
            return Integer.compare(this.weight, e1.weight);
        }
    }

    static ArrayList<Edges> arr_list = new ArrayList<>();
    static int m, n;
    static int[] parent;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {


            st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                break;
            }
            arr_list.clear();
            int init_cost = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                init_cost += cost;

                arr_list.add(new Edges(from, to, cost));
                arr_list.add(new Edges(to, from, cost));
            }

            int after_cost = kruskal();
            System.out.println(init_cost - after_cost);

        }

    }

    static int kruskal() {
        int result = 0;
        Collections.sort(arr_list);

        parent = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < arr_list.size(); i++) {
            Edges curr = arr_list.get(i);

            if (!equal(curr.from, curr.to)) {
                merge(curr.from, curr.to);
                result += curr.weight;
            }
        }
        return result;
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
