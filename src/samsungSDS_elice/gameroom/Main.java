package samsungSDS_elice.gameroom;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int n;
    static int v;
    static ArrayList<Edge<Integer, Integer>> relations;

    static class Edge<W, N> {
        W weight;
        N node;

        public void setEdge(W weight, N node) {
            this.weight = weight;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 0; test_case < T; test_case++) {
            n = sc.nextInt();                   //노드 개수
            v = n - 1;                      //간선 개수
            relations = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                relations.add(new Edge<>());
            }

            for (int i = 0; i < v; i++) {
                int frd1 = sc.nextInt();
                int frd2 = sc.nextInt();
                int price = sc.nextInt();

                relations.get(frd1).setEdge(price, frd2);
                relations.get(frd2).setEdge(price, frd1);
            }


        }
    }

    private static void compute(int origin) {
        for (int i = 1; i <= n; i++) {
            if (i != origin) {

            }
        }
    }

    private static void dfs(int target, int num) {
        if (num == target) {
            return;
        }
        dfs(target, relations.get(num).node);
    }
}
