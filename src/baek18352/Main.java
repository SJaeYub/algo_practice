package baek18352;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edges implements Comparable<Edges> {
        int to;
        int weight;

        Edges(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edges e1) {
            return Integer.compare(this.weight, e1.weight);
        }
    }

    static int n, m, k, x;
    static ArrayList<Edges>[] arr_list;
    static ArrayList<Integer> ans_list;
    static StringTokenizer st;
    static Edges[] d;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        ans_list = new ArrayList<>();
        arr_list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr_list[from].add(new Edges(to, 1));
        }

        Edges[] ans = dijkstra(x);

        for (Edges edges : ans) {
            if (edges.weight == k) {
                System.out.println(edges.to);
                flag = true;
            }
        }

        if (!flag) {
            System.out.println(-1);
        }
    }

    static Edges[] dijkstra(int from) {
        PriorityQueue<Edges> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        d = new Edges[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i == from) {
                d[i] = new Edges(i, 0);
            } else {
                d[i] = new Edges(i, Integer.MAX_VALUE);
            }
        }

        pq.add(d[from] = new Edges(from, 0));

        while (!pq.isEmpty()) {
            Edges curr = pq.poll();

            for (Edges next : arr_list[curr.to]) {
                if (!visited[next.to]) {
                    if (d[next.to].weight > d[curr.to].weight + next.weight) {
                        d[next.to].weight = d[curr.to].weight + next.weight;
                        pq.add(d[next.to]);
                    }
                }
            }

            visited[curr.to] = true;
        }

        return d;
    }
}
