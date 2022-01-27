package baek22868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edges implements Comparable<Edges> {
        int to;
        int cnt;

        Edges(int to, int cnt) {
            this.to = to;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Edges e1) {
            return Integer.compare(this.to, e1.to);
        }
    }

    static StringTokenizer st;
    static int n, m, start, objective, ans;
    static ArrayList<Edges>[] arr_list;
    static boolean[] visited;
    static int[] mid_station;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        mid_station = new int[n + 1];
        visited = new boolean[n + 1];
        arr_list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr_list[from].add(new Edges(to, 0));
            arr_list[to].add(new Edges(from, 0));
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(arr_list[i]);
        }

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        objective = Integer.parseInt(st.nextToken());

        bfs(start, objective);

        visited = new boolean[n + 1];           //초기화
        for (int i = objective; i > 0;) {
            visited[mid_station[i]] = true;
            i = mid_station[i];
        }
        visited[objective] = false;
        visited[start] = false;

        bfs(objective, start);

        System.out.println(ans);
    }

    static Edges bfs(int from, int to) {
        Queue<Edges> q = new LinkedList<>();
        q.add(new Edges(from, 0));

        while (!q.isEmpty()) {
            Edges curr = q.poll();
            visited[curr.to] = true;

            if (curr.to == to) {
                ans += curr.cnt;
                return curr;
            }

            for (Edges next : arr_list[curr.to]) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    mid_station[next.to] = curr.to;
                    Edges tmp = new Edges(next.to, curr.cnt + 1);
                    q.add(tmp);
                }
            }
        }

        return new Edges(-1, 0);
    }
}
