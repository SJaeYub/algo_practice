package baek16118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Practice {
    private static int N, M; //나무 그루터기 N, 오솔길의 개수 M
    private final static int INF = Integer.MAX_VALUE;
    private static ArrayList<ArrayList<Node>> graph;
    private static int[] dis;
    private static int[][] disWolf;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dis = new int[N + 1];
        disWolf = new int[2][N + 1];
        parent = new int[N + 1];
        resetGraph();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(h).add(new Node(t, 2 * w, 1));
            graph.get(t).add(new Node(h, 2 * w, 1));
        }
        Arrays.fill(dis, INF);
        for (int[] a : disWolf) Arrays.fill(a, INF);
        dijkstraForWolf();
        dijkstraForFox();
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (dis[i] < Math.min(disWolf[0][i], disWolf[1][i])) count++;
        }
        System.out.println(count);
    }

    private static void dijkstraForWolf() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int start = 1;
        pq.add(new Node(start, 0, 1));
        disWolf[1][start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.index;
            int nowDistance = node.distance;
            int nowFastMode = node.fastMode;
            if (disWolf[nowFastMode][now] < nowDistance) continue;
            for (int i = 0; i < graph.get(now).size(); i++) {
                Node there = graph.get(now).get(i);
                int thereIndex = there.getIndex();
                int thereFastMode = 1 - nowFastMode; //0 : fast, 1 : slow
                int cost = disWolf[nowFastMode][now] + (thereFastMode == 0 ? there.getDistance() / 2 : there.getDistance() * 2); //there까지 빠르게 도착하는 비용을 계산하였으므로 비교할 때에도 distWolf[0][there] 와 비교해준다
                if (cost < disWolf[thereFastMode][thereIndex]) {
                    disWolf[thereFastMode][thereIndex] = cost;
                    pq.add(new Node(thereIndex, cost, thereFastMode));
                }
            }
        }
    }

    private static void dijkstraForFox() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int start = 1;
        pq.add(new Node(start, 0, 1));
        dis[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.index;
            int nowDistance = node.distance;
            if (dis[now] < nowDistance) continue;
            for (int i = 0; i < graph.get(now).size(); i++) {
                Node there = graph.get(now).get(i);
                int thereIndex = there.getIndex();
                int cost = dis[now] + there.getDistance();
                if (cost < dis[thereIndex]) {
                    dis[thereIndex] = cost;
                    pq.add(new Node(thereIndex, cost, 1));
                }
            }
        }
    }

    static void resetGraph() {
        if (graph != null) graph.clear();
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int distance;
        int fastMode;

        Node(int index, int distance, int fastMode) {
            this.index = index;
            this.distance = distance;
            this.fastMode = fastMode;
        }

        public int getIndex() {
            return this.index;
        }

        public int getDistance() {
            return this.distance;
        }

        public int getFastMode() {
            return this.fastMode;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance < o.distance) {//distance가 작은 순
                return -1;
            }
            return 1;
        }
    }
}

