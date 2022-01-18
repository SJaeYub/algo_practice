package baek21924;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Edges {
        int from;
        int to;
        int cost;

        Edges(int a, int b, int c) {
            this.from = a;
            this.to = b;
            this.cost = c;
        }
    }

    static int n, m;
    static StringTokenizer st;
    static long ans;
    static boolean flag;
    static ArrayList<Edges> arr_list = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        long sumOfCost = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            sumOfCost+=cost;
            arr_list.add(new Edges(from, to, cost));
        }
        kruskal(arr_list);

        if (flag) {
            System.out.println(sumOfCost - ans);
        } else {
            System.out.println(-1);
        }
    }

    static void kruskal(ArrayList<Edges> list) {
        Collections.sort(list, (e1, e2) -> {
            if (e1.cost > e2.cost) {
                return 1;
            } else if (e1.cost == e2.cost) {
                return 0;
            } else {
                return -1;
            }
        });

        ans = 0;
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int cnt = 0;                //연결된 간선의 수 == 노드 - 1 개가 되야한다
        for (int i = 0; i < list.size(); i++) {
            Edges inAns = list.get(i);

            if (!equal(inAns.from, inAns.to)) {
                merge(inAns.from, inAns.to);
                ans += inAns.cost;
                cnt++;
            }

            if (cnt == n - 1) {
                flag = true;
            }
        }

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
