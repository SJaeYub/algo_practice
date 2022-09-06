package baek17396;

import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        long time;
        boolean flag;

        Node(int t, long time) {
            this.to = t;
            this.time = time;
            this.flag = false;
        }

        @Override
        public int compareTo(Node n1) {
            return Long.compare(this.time, n1.time);
        }
    }

    static int n, m;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        HashSet<Integer> temp_arr = new HashSet<>();
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int see = sc.nextInt();
            if (see == 1 && i != n - 1) {
                temp_arr.add(i);
            }
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int time = sc.nextInt();

            if (temp_arr.contains(from) || temp_arr.contains(to)) {
                continue;
            }

            graph[from].add(new Node(to, time));
            graph[to].add(new Node(from, time));
        }

        long[] ans = dijkstra(0, temp_arr);

//        for (int i : ans) {
//            System.out.print(i + " ");
//        }
//        System.out.println();

        if (ans[n - 1] == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans[n - 1]);
        }
        return;
    }

    static long[] dijkstra(int start, HashSet<Integer> hashSet) {
        long[] result = new long[n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];

        Arrays.fill(result, Long.MAX_VALUE);
        result[start] = 0;

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visited[curr.to]) {
                continue;
            }

            for (Node next : graph[curr.to]) {
                if (result[next.to] > result[curr.to] + next.time) {
                    result[next.to] = result[curr.to] + next.time;
                    pq.add(new Node(next.to, result[next.to]));
                }
            }

            visited[curr.to] = true;
        }

        return result;
    }
}
