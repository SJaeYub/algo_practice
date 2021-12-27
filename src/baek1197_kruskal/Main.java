package baek1197_kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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

    static int v, e;
    static ArrayList<Edges> arr_list = new ArrayList<>();
    static int ans;
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        for (int i = 0; i < e; i++) {
            arr_list.add(new Edges(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        kruskal(v, e, arr_list);
    }

    private static void kruskal(int numOfNode, int numOfEdge, ArrayList<Edges> arr) {
        Collections.sort(arr, (o1, o2) -> {
            if (o1.cost > o2.cost) {
                return 1;
            } else if (o1.cost == o2.cost) {
                return 0;
            } else {
                return -1;
            }
        });

        ans = 0;
        parent = new int[v + 1];

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < arr.size(); i++) {
            Edges inAns = arr.get(i);

            if (!equal(inAns.from, inAns.to)) {
                merge(inAns.from, inAns.to);
                ans += inAns.cost;
            }
        }

        System.out.println(ans);
    }

    private static boolean equal(int p, int q) {
        p = find(p);
        q = find(q);

        if (p == q) {
            return true;
        } else {
            return false;
        }
    }

    private static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    private static void merge(int p, int q) {
        p = find(p);
        q = find(q);
        if (p != q) {
            parent[q] = p;
        }
    }

}
