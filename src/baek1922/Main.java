package baek1922;

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

    static StringTokenizer st;
    static int n, m, ans;
    static ArrayList<Edges> arr_list = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr_list.add(new Edges(from, to, cost));
        }

        kruskal(arr_list);
        System.out.println(ans);
    }

    static void kruskal(ArrayList<Edges> arr_list) {
        Collections.sort(arr_list);

        ans = 0;
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < arr_list.size(); i++) {
            Edges node = arr_list.get(i);

            if (!equal(node.from, node.to)) {
                merge(node.from, node.to);
                ans += node.weight;
            }
        }
    }

    static boolean equal(int p, int q) {
        p = find(p);
        q = find(q);

        if(p == q) {
            return true;
        } else {
            return false;
        }
    }

    static int find(int node) {
        if(parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    static void merge(int p, int q) {
        p = find(p);
        q = find(q);
        if (p != q) {
            parent[q] = p;
        }
    }
}
