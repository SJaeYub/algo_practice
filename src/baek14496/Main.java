package baek14496;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    static int a, b, n, m;
    static ArrayList<Edges>[] arr_list;
    static StringTokenizer st;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr_list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr_list[from].add(new Edges(to, 1));
            arr_list[to].add(new Edges(from, 1));
        }

        int[] ans = dijkstra(a);

        if (ans[b] != Integer.MAX_VALUE) {
            flag = true;
        }

        if (!flag) {
            System.out.println(-1);
        } else {
            System.out.println(ans[b]);
        }
    }

    static int[] dijkstra(int from) {
        PriorityQueue<Edges> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        int[] d = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i == from) {
                d[i] = 0;
            } else {
                d[i] = Integer.MAX_VALUE;
            }
        }

        pq.add(new Edges(from, 0));

        while (!pq.isEmpty()) {
            Edges curr = pq.poll();

            for (Edges next : arr_list[curr.to]) {
                if (!visited[next.to]) {
                    if (d[next.to] > d[curr.to] + next.weight) {
                        d[next.to] = d[curr.to] + next.weight;
                        pq.add(new Edges(next.to, d[next.to]));
                    }
                }
            }
            visited[curr.to] = true;
        }

        return d;
    }
}
