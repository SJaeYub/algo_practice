package baek20007;

import javax.swing.text.EditorKit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, x, y, inf = Integer.MAX_VALUE;
    static int totalDist[];
    static ArrayList<Edge>[] adj;
    static class Edge implements Comparable<Edge> {
        int to, dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        totalDist = new int[n];
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[a].add(new Edge(b, w));
            adj[b].add(new Edge(a, w));
        }

        System.out.println(process());

    }

    private static int process() {
        dijkstra();

        Arrays.sort(totalDist);

        if(totalDist[n - 1] * 2 > x) {
            return -1;
        }

        int day = 0, idx = 0, tmp = 0;
        while (idx < n) {
            while (idx < n && tmp + totalDist[idx] * 2 <= x) {
                tmp += totalDist[idx++] * 2;
            }
            tmp = 0;
            day++;
        }

        return day;
    }

    private static void dijkstra() {
        Arrays.fill(totalDist, inf);

        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        totalDist[y] = 0;
        pq.add(new Edge(y, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (visited[now.to]) {
                continue;
            }

            for (Edge next : adj[now.to]) {
                if (!visited[next.to]) {
                    if (totalDist[next.to] > totalDist[now.to] + next.dist) {
                        totalDist[next.to] = totalDist[now.to] + next.dist;
                        pq.add(new Edge(next.to, totalDist[next.to]));
                    }
                }

                visited[now.to] = true;
            }
        }
    }
}
