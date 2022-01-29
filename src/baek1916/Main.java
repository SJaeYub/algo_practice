package baek1916;

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

    static int n, m;
    static ArrayList<Edges>[] arr_list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr_list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr_list[from].add(new Edges(to, weight));

        }

        st = new StringTokenizer(br.readLine(), " ");

        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] arr = dijkstra(start);
        System.out.println(arr[target]);
    }

    static int[] dijkstra(int from) {
        PriorityQueue<Edges> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[from] = 0;
        pq.add(new Edges(from, 0));

        while (!pq.isEmpty()) {
            Edges curr = pq.poll();

            if (!visited[curr.to]) {
                visited[curr.to] = true;

                for (Edges next : arr_list[curr.to]) {
                    if (!visited[next.to]) {
                        if (d[next.to] > d[curr.to] + next.weight) {
                            d[next.to] = d[curr.to] + next.weight;
                            pq.add(new Edges(next.to, d[next.to]));
                        }
                    }
                }
            }


        }

        return d;
    }
}
