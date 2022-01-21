package baek1753;

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

    static int v, e, k;
    static ArrayList<Edges>[] arr_list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());

        arr_list = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr_list[from].add(new Edges(to, weight));
        }

        int[] ans = dijkstra(k);

        for (int i = 1; i <= v; i++) {
            int ans_tmp = ans[i];
            if (ans_tmp != Integer.MAX_VALUE) {
                System.out.println(ans_tmp);
            } else {
                System.out.println("INF");
            }
        }
    }

    static int[] dijkstra(int from) {
        PriorityQueue<Edges> pq = new PriorityQueue();
        boolean[] visited = new boolean[v + 1];
        int[] d = new int[v + 1];

        for (int i = 0; i <= v; i++) {
            if (i == from) {
                d[i] = 0;
            } else {
                d[i] = Integer.MAX_VALUE;
            }
        }

        pq.add(new Edges(from, 0));

        while (!pq.isEmpty()) {
            Edges curr = pq.poll();

            if (visited[curr.to]) {
                continue;
            }

            for (Edges next : arr_list[curr.to]) {
                if (d[next.to] > d[curr.to] + next.weight) {
                    d[next.to] = d[curr.to] + next.weight;
                    pq.add(new Edges(next.to, d[next.to]));
                }
            }

            visited[curr.to] = true;
        }

        return d;
    }
}
